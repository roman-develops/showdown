package dev.showdown.mapper;

import dev.showdown.db.entity.UserEntity;
import dev.showdown.dto.UserViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserViewDto toUserViewDto(UserEntity userEntity);

}
