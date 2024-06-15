package dev.showdown.mapper;

import dev.showdown.db.entity.UserEntity;
import dev.showdown.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDto toUserViewDto(UserEntity userEntity);

}
