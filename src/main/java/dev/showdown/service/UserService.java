package dev.showdown.service;

import dev.showdown.db.entity.UserEntity;
import dev.showdown.db.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    public UserEntity getUserByUsername(String username) {
        return userEntityRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

}
