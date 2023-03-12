package com.gonchaba.codeassighnment.controllers;

import com.gonchaba.codeassighnment.domain.CalcUser;
import com.gonchaba.codeassighnment.dto.UserDto;
import com.gonchaba.codeassighnment.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable String userName) {
        Optional<CalcUser> optionalUser = Optional.ofNullable(userService.findByUserName(userName));
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        CalcUser user = optionalUser.get();
        UserDto userDto = new UserDto(user.getId(), user.getUserName(), user.getBalance());
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        CalcUser user = new CalcUser(userDto.getUserName(), userDto.getPassword(), userDto.getBalance());
        userService.save(user);
        userDto.setId(user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userName, @RequestBody UserDto userDto) {
        Optional<CalcUser> optionalUser = Optional.ofNullable(userService.findByUserName(userName));
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        CalcUser user = optionalUser.get();
        user.setUserName(user.getUserName());
        user.setPassword(userDto.getPassword());
        user.setBalance(userDto.getBalance());
        userService.save(user);
        userDto.setId(user.getId());
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {
        Optional<CalcUser> optionalUser = Optional.ofNullable(userService.findByUserName(userName));
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CalcUser user = optionalUser.get();
        userService.delete(user);
        return ResponseEntity.noContent().build();
    }

}

