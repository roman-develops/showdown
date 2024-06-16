package dev.showdown.mapper;

import dev.showdown.db.entity.TableEntity;
import dev.showdown.dto.TableCreateDto;
import dev.showdown.dto.TableViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = UserMapper.class)
public interface TableMapper {

    TableViewDto toTableViewDto(TableEntity entity);

    TableEntity toTable(TableCreateDto dto);

}
