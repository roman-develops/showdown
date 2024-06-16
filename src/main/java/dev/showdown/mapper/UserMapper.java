package dev.showdown.mapper;

import dev.showdown.db.entity.UserEntity;
import dev.showdown.dto.UserViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserViewDto toUserViewDto(UserEntity userEntity);

    Set<UserViewDto> toUserViewDtos(Set<UserEntity> userEntity);

}
