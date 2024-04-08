package com.web.springmvc.newsweb.controller;

import com.web.springmvc.newsweb.dto.UserDTO;
import com.web.springmvc.newsweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/change-password")
    public  ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@RequestBody Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
