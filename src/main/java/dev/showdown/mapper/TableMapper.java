package dev.showdown.mapper;

import dev.showdown.db.entity.TableEntity;
import dev.showdown.dto.NewTableDto;
import dev.showdown.dto.TableViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TableMapper {

    TableViewDto toDto(TableEntity entity);

    TableEntity toTable(NewTableDto dto);

}
