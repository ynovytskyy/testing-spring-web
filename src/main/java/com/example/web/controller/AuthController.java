package com.example.web.controller;

import com.example.helper.ParsingHelper;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.web.dto.TokenDto;
import com.example.web.dto.UserDto;
import com.example.web.secuity.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private ParsingHelper parsingHelper;
    private final PasswordEncoder passwordEncoder;

    public AuthController(PasswordEncoder passwordEncoder,
                          UserRepository userRepository,
                          JwtService jwtService,
                          ParsingHelper parsingHelper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.parsingHelper = parsingHelper;
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserDto userDto) {
        List<User> users = userRepository.findByEmail(userDto.getEmail());
        if (!users.isEmpty()) {
            User user = users.get(0);
            if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                String token = jwtService.createToken(user.getId(), user.getEmail(), user.getAuthority());
                userDto = parsingHelper.mapObject(user, UserDto.class);
                return new TokenDto(token, userDto);
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}
