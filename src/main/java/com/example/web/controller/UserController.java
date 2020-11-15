package com.example.web.controller;

import com.example.helper.ParsingHelper;
import com.example.model.User;
import com.example.service.UserService;
import com.example.web.dto.UserDto;
import com.example.web.secuity.AwUserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private ParsingHelper parsingHelper;

    public UserController(UserService userService,
                          ParsingHelper parsingHelper) {
        this.userService = userService;
        this.parsingHelper = parsingHelper;
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyAuthority('USER')")
    public UserDto getMyProfile(@AuthenticationPrincipal AwUserPrincipal principal) {
        User user = userService.getById(principal.getId());
        return parsingHelper.mapObject(user, UserDto.class);
    }

}
