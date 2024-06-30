package dev.showdown.service;

import dev.showdown.db.entity.UserEntity;
import dev.showdown.db.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    /**
     * Retrieves a UserEntity by its username.
     *
     * @param username - the username of the user
     * @return UserEntity - the user entity with the given username
     * @throws UsernameNotFoundException if a user with the given username is not found
     */
    public UserEntity getUser(String username) {
        return userEntityRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with username %s not found", username)));
    }

    /**
     * Retrieves a UserEntity by its username.
     *
     * @param userId - the id of the user
     * @return UserEntity - the user entity with the given username
     * @throws UsernameNotFoundException if a user with the given username is not found
     */
    public UserEntity getUser(Long userId) {
        return userEntityRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with id %s not found", userId)));
    }

    /**
     * Retrieves the username of the currently authenticated user.
     *
     * @return String - the username of the authenticated user, or null if no user is authenticated
     */
    private String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }

    /**
     * Retrieves the UserEntity of the currently authenticated user.
     *
     * @return UserEntity - the user entity of the authenticated user
     */
    public UserEntity getCurrentUser() {
        String username = getAuthenticatedUsername();
        return getUser(username);
    }
}
