package dev.showdown.controller;

import dev.showdown.dto.UserCreateDto;
import dev.showdown.dto.UserViewDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // TODO Implement this
    @GetMapping
    public Page<UserViewDto> getAllUsers() {
        return Page.empty();
    }

    // TODO Implement this
    @GetMapping("/{userId}")
    public UserViewDto getUser(@PathVariable Long userId) {
        return new UserViewDto();
    }

    // TODO Implement this
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity
                .created(URI.create("/api/users"))
                .build();
    }

}
