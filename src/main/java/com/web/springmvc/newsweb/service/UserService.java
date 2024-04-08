package com.web.springmvc.newsweb.service;

import com.web.springmvc.newsweb.dto.UserDTO;
import com.web.springmvc.newsweb.exception.UserAlreadyExistsException;
import com.web.springmvc.newsweb.exception.UserNotFoundException;
import com.web.springmvc.newsweb.model.Role;
import com.web.springmvc.newsweb.model.User;
import com.web.springmvc.newsweb.model.UserDetailsImpl;
import com.web.springmvc.newsweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserDTO createUser(UserDTO userDTO) {
        if(userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username already exist");
        }
        userDTO.setStatus(1);
        User user = mapToEntity(userDTO);
        userRepository.save(user);
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

    public UserDTO register(UserDTO userDTO) {
        User user = new User();
        if(userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username already exist");
        }
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(1);
        userRepository.save(user);
        user.setPassword(null);
        return mapToDTO(user);
    }

    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("Not found user"));
        return mapToDTO(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    private User mapToEntity(UserDTO userDTO) {
        User user = new User();
        if(userDTO.getId() != null) {
            user.setId(userDTO.getId());
        }
        user.setUsername(userDTO.getUsername());
        if(userDTO.getRole().equals("USER")) {
            user.setRole(Role.USER);
        } else {
            user.setRole(Role.ADMIN);
        }
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
        userDTO.setRole(user.getRole().name());
        return userDTO;
    }
}
