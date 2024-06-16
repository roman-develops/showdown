package dev.showdown.controller;

import dev.showdown.dto.NewTableDto;
import dev.showdown.dto.TableViewDto;
import dev.showdown.service.TableService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TableController {

    private final TableService tableService;

    @Operation(tags = {"Table"}, summary = "Get all tables")
    @GetMapping("/tables")
    public List<TableViewDto> getAllTables() {
        return tableService.getAllTables();
    }

    @Operation(tags = {"Table"}, summary = "Get current user's tables")
    @GetMapping("/my-tables")
    public List<TableViewDto> getMyTables() {
        return tableService.getTablesByUser();
    }

    @Operation(tags = {"Table"}, summary = "Get a table by id")
    @GetMapping("/tables/{linkIdentifier}")
    public TableViewDto getTable(@PathVariable String linkIdentifier) {
        return tableService.getTableByIdentifier(linkIdentifier);
    }

    @Operation(tags = {"Table"}, summary = "Create a new table")
    @PostMapping
    public TableViewDto createTable(@RequestBody NewTableDto tableDto) {
        return tableService.createTable(tableDto);
    }


}
