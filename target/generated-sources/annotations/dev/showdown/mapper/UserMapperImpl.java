package dev.showdown.mapper;

import dev.showdown.db.entity.UserEntity;
import dev.showdown.dto.UserViewDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-15T21:22:45+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserViewDto toUserViewDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserViewDto.UserViewDtoBuilder userViewDto = UserViewDto.builder();

        userViewDto.id( userEntity.getId() );
        userViewDto.username( userEntity.getUsername() );

        return userViewDto.build();
    }
}
