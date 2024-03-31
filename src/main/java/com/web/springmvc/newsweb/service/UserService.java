package com.web.springmvc.newsweb.service;

import com.web.springmvc.newsweb.dto.UserDTO;
import com.web.springmvc.newsweb.exception.RoleNotFoundException;
import com.web.springmvc.newsweb.exception.UserNotFoundException;
import com.web.springmvc.newsweb.model.User;
import com.web.springmvc.newsweb.model.UserDetailsImpl;
import com.web.springmvc.newsweb.repository.RoleRepository;
import com.web.springmvc.newsweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setStatus(1);
        User user = userRepository.save(mapToEntity(userDTO));
        return userDTO;
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow(()-> new UserNotFoundException("Not found user"));
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setStatus(userDTO.getStatus());
        userRepository.save(user);
        return userDTO;
    }

    public UserDTO deleteUser(UserDTO userDTO) {
        return null;
    }

    public String register(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(roleRepository.findByCode("USER"));
        user.setStatus(1);
        userRepository.save(user);
        return jwtService.generateToken(new UserDetailsImpl(user));
    }

    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("Not found user"));
        return mapToDTO(user);
    }

    private User mapToEntity(UserDTO userDTO) {
        User user = new User();
        if(userDTO.getId() != null) {
            user.setId(userDTO.getId());
        }
        user.setUsername(userDTO.getUsername());
        user.setRole(roleRepository.findById(userDTO.getRole()).orElseThrow(() -> new RoleNotFoundException("Not found role")));
        user.setStatus(userDTO.getStatus());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setStatus(user.getStatus());
        userDTO.setRole(user.getRole().getId());
        return userDTO;
    }
}
