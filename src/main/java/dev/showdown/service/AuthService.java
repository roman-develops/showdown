package dev.showdown.service;

import dev.showdown.db.entity.UserEntity;
import dev.showdown.db.repository.RefreshTokenRepository;
import dev.showdown.db.repository.UserEntityRepository;
import dev.showdown.dto.*;
import dev.showdown.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /**
     * Authenticates a user with their username and password, and generates a token for them.
     *
     * @param loginDto - DTO containing the user's login credentials
     * @return TokenDto - DTO containing the generated token
     */
    public TokenDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        return tokenService.generateTokenDto(authentication);
    }

    /**
     * Registers a new user with a username and password.
     *
     * @param registerDto - DTO containing the user's registration details
     * @return UserViewDto - DTO representing the view of the registered user
     * @throws ResponseStatusException if the username is already taken
     */
    @Transactional
    public UserViewDto register(RegisterDto registerDto) {
        if (userEntityRepository.existsByUsername(registerDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        UserEntity user = UserEntity.builder()
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();

        return userMapper.toUserViewDto(userEntityRepository.save(user));
    }

    /**
     * Refreshes a user's token.
     *
     * @param refreshTokenDto - DTO containing the refresh token
     * @return TokenDto - DTO containing the new token
     * @throws ResponseStatusException if the refresh token is invalid
     */
    @Transactional
    public TokenDto refreshToken(RefreshTokenDto refreshTokenDto) {
        if (!tokenService.isRefreshTokenValid(refreshTokenDto.getRefreshToken())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid refresh token!");
        }

        refreshTokenRepository.deleteByValue(refreshTokenDto.getRefreshToken());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                        tokenService.extractUsername(refreshTokenDto.getRefreshToken()).get(),
                        null);

        return tokenService.generateTokenDto(authentication);
    }

    /**
     * Logs out a user by deleting their refresh token.
     *
     * @param refreshTokenDto - DTO containing the refresh token
     */
    @Transactional
    public void logout(RefreshTokenDto refreshTokenDto) {
        refreshTokenRepository.deleteByValue(refreshTokenDto.getRefreshToken());
    }
}
