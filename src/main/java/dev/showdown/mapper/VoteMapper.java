package dev.showdown.mapper;

import dev.showdown.db.entity.Vote;
import dev.showdown.dto.VotingResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VoteMapper {

    @Mapping(target = "userId", source = "userEntity.id")
    VotingResultDto toDto(Vote entity);

}
