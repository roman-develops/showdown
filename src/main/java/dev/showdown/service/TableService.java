package dev.showdown.service;

import dev.showdown.db.entity.TableEntity;
import dev.showdown.db.entity.UserEntity;
import dev.showdown.db.repository.TableEntityRepository;
import dev.showdown.dto.NewTableDto;
import dev.showdown.dto.TableViewDto;
import dev.showdown.exceptions.NotFoundRuntimeException;
import dev.showdown.mapper.TableMapper;
import dev.showdown.util.LinkIdentifierGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableEntityRepository tableEntityRepository;
    private final TableMapper tableMapper;
    private final UserService userService;

    public static final String DEFOLIATE_VOTING_SYSTEM = "{1}{2}{3}{4}{5}{6}{7}";


    /**
     * The method returns the table by reference, if the user followed the link for the first time, adds it to the table participants.
     *
     * @param identifier - unique table identifier
     */
    public TableViewDto getTableByIdentifier(String identifier) {
        TableEntity table = getTableEntityByIdentifier(identifier);
        UserEntity currentUser = userService.getCurrentUser();
        Long currentUserId = currentUser.getId();

        if (table.getOwner() != currentUser && !table.getUsersIds().contains(currentUserId)) {
            table.getUsersIds().add(currentUserId);
            tableEntityRepository.save(table);
        }
        return tableMapper.toDto(table);
    }

    private TableEntity getTableEntityByIdentifier(String identifier) {
        return tableEntityRepository.getTableEntitiesByLinkIdentifier(identifier).orElseThrow(() ->
                new NotFoundRuntimeException(String.format("The table at this link: %s was not found", identifier)));
    }

    /**
     * The method creates a new table
     */
    public TableViewDto createTable(NewTableDto tableDto) {
        UserEntity currentUser = userService.getCurrentUser();

        TableEntity table = tableMapper.toTable(tableDto);
        table.setLinkIdentifier(LinkIdentifierGenerator.generateLinkId());
        table.setVotingSystem(DEFOLIATE_VOTING_SYSTEM);
        table.setOwner(currentUser);

        tableEntityRepository.save(table);

        return tableMapper.toDto(table);
    }

    public List<TableViewDto> getTablesByUser() {
        UserEntity currentUser = userService.getCurrentUser();
        return tableEntityRepository.findAllByOwner(currentUser)
                .stream()
                .map(tableMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TableViewDto> getAllTables() {
        return tableEntityRepository.findAll()
                .stream()
                .map(tableMapper::toDto)
                .collect(Collectors.toList());
    }

}
