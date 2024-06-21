package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Schema(name = "Table view DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableViewDto {

    private String id;

    private String name;

    private String votingSystem;

    private UserViewDto owner;

    private Set<UserViewDto> participants;

}
