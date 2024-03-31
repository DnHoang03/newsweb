package com.web.springmvc.newsweb.service;

import com.web.springmvc.newsweb.dto.AuthenticationRequest;
import com.web.springmvc.newsweb.dto.AuthenticationRespone;
import com.web.springmvc.newsweb.model.User;
import com.web.springmvc.newsweb.model.UserDetailsImpl;
import com.web.springmvc.newsweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthenticationRespone authenticationRequest(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );
        User user = userRepository.findByUsername(request.getUserName());
        String token = jwtService.generateToken(new UserDetailsImpl(user));
        return AuthenticationRespone.builder().token(token).build();
    }
}
