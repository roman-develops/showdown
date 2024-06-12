package dev.showdown.controller;

import dev.showdown.dto.TableDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class TableController {

    // TODO Implement this
    @GetMapping("/tables")
    public Page<TableDto> getAllTables() {
        return Page.empty();
    }

    // TODO Implement this
    @GetMapping("/my-tables")
    public Page<TableDto> getMyTables() {
        return Page.empty();
    }

    // TODO Implement this
    @GetMapping("/tables/{tableId}")
    public TableDto getTable(@PathVariable Long tableId) {
        return new TableDto();
    }

    // TODO Implement this
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody TableDto tableDto) {
        return ResponseEntity
                .created(URI.create("/api/tables"))
                .build();
    }


}
