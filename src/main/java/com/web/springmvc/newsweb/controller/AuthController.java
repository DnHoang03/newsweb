package com.web.springmvc.newsweb.controller;

import com.web.springmvc.newsweb.dto.AuthenticationRequest;
import com.web.springmvc.newsweb.dto.AuthenticationRespone;
import com.web.springmvc.newsweb.dto.RegisterRequest;
import com.web.springmvc.newsweb.dto.UserDTO;
import com.web.springmvc.newsweb.service.AuthenticationService;
import com.web.springmvc.newsweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationRespone> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticationRequest(request));
    }
}
