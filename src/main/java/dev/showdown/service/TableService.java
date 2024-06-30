package dev.showdown.service;

import dev.showdown.db.entity.TableEntity;
import dev.showdown.db.entity.UserEntity;
import dev.showdown.db.repository.TableRepository;
import dev.showdown.dto.TableCreateDto;
import dev.showdown.dto.TableViewDto;
import dev.showdown.dto.UserViewDto;
import dev.showdown.mapper.TableMapper;
import dev.showdown.mapper.UserMapper;
import dev.showdown.util.LinkIdGenerator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TableService {

    private final TableRepository tableRepository;
    private final TableMapper tableMapper;
    private final UserMapper userMapper;
    private final UserService userService;

    //TODO Reimplement this
    public static final String DEFAULT_VOTING_SYSTEM = "{1}{2}{3}{4}{5}{6}{7}";


    /**
     * Retrieves a table using its ID.
     * If a user accesses the table for the first time via this method,
     * the user is added to the table's participant list.
     *
     * @param tableId - unique table ID
     * @return TableViewDto - DTO representing the table view
     */
    public TableViewDto getTableAndAddUserToParticipantList(String tableId) {
        TableEntity table = getTable(tableId);
        UserEntity currentUser = userService.getCurrentUser();

        if (table.getOwner() != currentUser && !table.getParticipants().contains(currentUser)) {
            table.getParticipants().add(currentUser);
            table = tableRepository.save(table);
        }
        return tableMapper.toTableViewDto(table);
    }

    /**
     * Retrieves a table using its ID.
     *
     * @param tableId - unique table ID
     * @return TableEntity - entity object representing the table
     * @throws EntityNotFoundException if the table with the specified ID is not found
     */
    @Transactional(readOnly = true)
    public TableEntity getTable(String tableId) {
        return tableRepository.getTableEntityById(tableId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Table with id %s not found", tableId)));
    }

    /**
     * Creates a new table.
     *
     * @param tableCreateDto - DTO containing the details for creating a new table
     * @return TableViewDto - DTO representing the created table view
     */
    public TableViewDto createTable(TableCreateDto tableCreateDto) {
        TableEntity newTable = tableRepository.save(tableMapper.toTable(tableCreateDto).toBuilder()
                .id(LinkIdGenerator.generateLinkId())
                .votingSystem(DEFAULT_VOTING_SYSTEM)
                .owner(userService.getCurrentUser())
                .participants(new HashSet<>(Collections.singleton(userService.getCurrentUser())))
                .build());

        return tableMapper.toTableViewDto(newTable);
    }

    /**
     * Retrieves all tables owned by the current user.
     *
     * @return List<TableViewDto> - list of DTO representing the table views
     */
    @Transactional(readOnly = true)
    public List<TableViewDto> getTablesByUser() {
        UserEntity currentUser = userService.getCurrentUser();
        return tableRepository.findAllByOwner(currentUser)
                .stream()
                .map(tableMapper::toTableViewDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all tables.
     *
     * @return List<TableViewDto> - list of DTO representing all table views
     */
    @Transactional(readOnly = true)
    public List<TableViewDto> getAllTables() {
        return tableRepository.findAll()
                .stream()
                .map(tableMapper::toTableViewDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all tables.
     *
     * @return List<TableViewDto> - list of DTO representing all table views
     */
    @Transactional(readOnly = true)
    public Set<UserViewDto> getTableParticipants(String tableId) {

        TableEntity table = getTable(tableId);
        return userMapper.toUserViewDtos(getTable(tableId).getParticipants());
    }

    /**
     * Checks if the user is the owner of the specified table.
     *
     * @param username The username of the user.
     * @param tableId  The ID of the table.
     * @return A boolean indicating whether the user is the owner of the table.
     */
    @Transactional(readOnly = true)
    public boolean isUserTableOwner(String username, String tableId) {
        return tableRepository.isUserTableOwner(username, tableId);
    }

    /**
     * Checks if the user is the participant of the specified table.
     *
     * @param username The username of the user.
     * @param tableId  The ID of the table.
     * @return A boolean indicating whether the user is the participant of the table.
     */
    @Transactional(readOnly = true)
    public boolean isUserTableParticipant(String username, String tableId) {
        return tableRepository.isUserTableOwnerOrParticipant(username, tableId);
    }

    /**
     * Deletes a table using its ID.
     * Only the owner of the table can delete it.
     *
     * @param tableId - unique table ID
     */
    public void deleteTable(String tableId) {
        tableRepository.deleteById(tableId);
    }
}
