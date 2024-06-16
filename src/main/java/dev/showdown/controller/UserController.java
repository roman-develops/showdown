package dev.showdown.controller;

import dev.showdown.dto.UserViewDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // TODO Implement this
    @Operation(tags = { "User" }, summary = "Retrieve all users")
    @GetMapping
    public Page<UserViewDto> getAllUsers() {
        return Page.empty();
    }

    // TODO Implement this
    @Operation(tags = { "User" }, summary = "Retrieve a user by their ID")
    @GetMapping("/{userId}")
    public UserViewDto getUser(@PathVariable Long userId) {
        return new UserViewDto();
    }

}
