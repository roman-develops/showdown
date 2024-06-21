package dev.showdown.controller;

import dev.showdown.dto.TableCreateDto;
import dev.showdown.dto.TableViewDto;
import dev.showdown.service.TableService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TableController {

    private final TableService tableService;

    @Operation(tags = {"Table"}, summary = "Retrieve all tables")
    @GetMapping("/tables")
    public List<TableViewDto> getAllTables() {
        return tableService.getAllTables();
    }

    @Operation(tags = {"Table"}, summary = "Retrieve tables owned by the current user")
    @GetMapping("/my-tables")
    public List<TableViewDto> getMyTables() {
        return tableService.getTablesByUser();
    }

    @Operation(tags = {"Table"}, summary = "Get a table by its id")
    @GetMapping("/tables/{tableId}")
    public TableViewDto getTableAndAddUserIfNotExists(@PathVariable String tableId) {
        return tableService.getTableAndAddUserToParticipantList(tableId);
    }

    @Operation(tags = {"Table"}, summary = "Retrieve a table by its ID and add the user to the table's participant list")
    @PostMapping("/tables")
    public ResponseEntity<TableViewDto> createTable(@RequestBody TableCreateDto tableCreateDto) {
        TableViewDto newTable = tableService.createTable(tableCreateDto);
        return ResponseEntity
                .created(URI.create("/api/tables/" + newTable.getId()))
                .body(newTable);
    }

    @Operation(tags = {"Table"}, summary = "Delete a table by its ID")
    @PreAuthorize("@tableService.getTable(#tableId).owner.username == authentication.name")
    @DeleteMapping("/tables/{tableId}")
    public ResponseEntity<Void> deleteTable(@PathVariable String tableId) {
        tableService.deleteTable(tableId);
        return ResponseEntity.noContent().build();
    }

}
