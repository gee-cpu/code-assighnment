package com.gonchaba.codeassighnment.controllers;

import com.gonchaba.codeassighnment.domain.CalcUser;
import com.gonchaba.codeassighnment.domain.Operation;
import com.gonchaba.codeassighnment.dto.LogInDto;
import com.gonchaba.codeassighnment.dto.UserDto;
import com.gonchaba.codeassighnment.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<CalcUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable String userName) {
        CalcUser calcUser = userService.findByUserName(userName);
        if (calcUser == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto userDto = new UserDto();
        userDto.setId(calcUser.getId());
        userDto.setUserName(calcUser.getUserName());
        userDto.setPassword(calcUser.getPassword());
        userDto.setBalance(calcUser.getBalance());
        userDto.setStatus(calcUser.getStatus());
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        CalcUser user = new CalcUser();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setBalance(userDto.getBalance());
        user.setStatus(userDto.getStatus());

        user = userService.createUser(user);

        UserDto createdUserDto = new UserDto();
        createdUserDto.setId(user.getId());
        createdUserDto.setUserName(user.getUserName());
        createdUserDto.setPassword(user.getPassword());
        createdUserDto.setBalance(user.getBalance());
        createdUserDto.setStatus(user.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }
    @PostMapping("/logIn")
    public ResponseEntity<Map<String, Object>> logIn(@RequestBody LogInDto loginDto) {
        try {
            if (!userService.userExists(loginDto.getUserName())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid username or password"));
            }

            String token = String.valueOf(userService.login(loginDto.getUserName(), loginDto.getPassword()));

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Cannot perform login"));
        }
    }


    @PutMapping("/{userName}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userName, @RequestBody UserDto userDto) {
        CalcUser calcUser = userService.findByUserName(userName);
        if (calcUser == null) {
            return ResponseEntity.notFound().build();
        }
        calcUser.setPassword(userDto.getPassword());
        CalcUser updatedUser = userService.updateUser(userName, calcUser);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setId(updatedUser.getId());
        updatedUserDto.setUserName(updatedUser.getUserName());
        updatedUserDto.setPassword(updatedUser.getPassword());
        updatedUserDto.setStatus(updatedUser.getStatus());
        return ResponseEntity.ok(updatedUserDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {
        CalcUser calcUser = userService.findByUserName(userName);
        if (calcUser == null) {
            return ResponseEntity.notFound().build();
        }
        CalcUser user = new CalcUser();
        userService.delete(user);
        return ResponseEntity.noContent().build();
    }

}

