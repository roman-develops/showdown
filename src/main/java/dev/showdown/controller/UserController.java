package dev.showdown.controller;

import dev.showdown.dto.UserCreateDto;
import dev.showdown.dto.UserViewDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // TODO Implement this
    @Operation(tags = { "User" }, summary = "Get all users")
    @GetMapping
    public Page<UserViewDto> getAllUsers() {
        return Page.empty();
    }

    // TODO Implement this
    @Operation(tags = { "User" }, summary = "Get a user by id")
    @GetMapping("/{userId}")
    public UserViewDto getUser(@PathVariable Long userId) {
        return new UserViewDto();
    }

    // TODO Implement this
    @Operation(tags = { "User" }, summary = "Create a new user")
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity
                .created(URI.create("/api/users"))
                .build();
    }

}
