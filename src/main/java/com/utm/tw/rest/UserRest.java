package com.utm.tw.rest;

import com.utm.tw.entity.dto.RegisterDTO;
import com.utm.tw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRest {

    private final UserService userService;

    @GetMapping("/randomUrls/{urlRandom}")
    public ResponseEntity<Object> activateUser(@PathVariable String urlRandom) {
        try {
            userService.activateUser(urlRandom);
            return new ResponseEntity<>("You activated your account", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ups", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDTO) {
        try {
            userService.register(registerDTO);
            return new ResponseEntity<>("All good, you have been registered", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Bad request, you could not be registered", HttpStatus.BAD_REQUEST);
        }
    }
}
