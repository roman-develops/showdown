package dev.showdown.controller;

import dev.showdown.dto.TableDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class TableController {

    // TODO Implement this
    @Operation(tags = { "Table" }, summary = "Get all tables")
    @GetMapping("/tables")
    public Page<TableDto> getAllTables() {
        return Page.empty();
    }

    // TODO Implement this
    @Operation(tags = { "Table" }, summary = "Get current user's tables")
    @GetMapping("/my-tables")
    public Page<TableDto> getMyTables() {
        return Page.empty();
    }

    // TODO Implement this
    @Operation(tags = { "Table" }, summary = "Get a table by id")
    @GetMapping("/tables/{tableId}")
    public TableDto getTable(@PathVariable Long tableId) {
        return new TableDto();
    }

    // TODO Implement this
    @Operation(tags = { "Table" }, summary = "Create a new table")
    @PostMapping
    public ResponseEntity<Void> createTable(@RequestBody TableDto tableDto) {
        return ResponseEntity
                .created(URI.create("/api/tables"))
                .build();
    }


}
