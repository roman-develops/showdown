package dev.showdown.mapper;

import dev.showdown.db.entity.Game;
import dev.showdown.db.entity.RefreshToken;
import dev.showdown.dto.GameViewDto;
import dev.showdown.dto.RefreshTokenDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RefreshTokenMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "token", target = "refreshToken")
    RefreshTokenDto toRefreshTokenDto(RefreshToken refreshToken);

}
