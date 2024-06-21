package dev.showdown.mapper;

import dev.showdown.db.entity.Game;
import dev.showdown.dto.GameViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {

    GameViewDto toGameDto(Game game);

}
