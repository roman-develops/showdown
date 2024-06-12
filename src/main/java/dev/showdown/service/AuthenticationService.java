package dev.showdown.service;

import dev.showdown.db.entity.UserEntity;
import dev.showdown.db.repository.UserEntityRepository;
import dev.showdown.dto.LoginDto;
import dev.showdown.dto.RegisterDto;
import dev.showdown.dto.TokenDto;
import dev.showdown.dto.UserViewDto;
import dev.showdown.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public TokenDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new TokenDto(jwtService.generateToken(authentication.getName()));
    }

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
}
