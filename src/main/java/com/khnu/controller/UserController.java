package com.khnu.controller;

import com.khnu.model.TradingAccount;
import com.khnu.model.User;
import com.khnu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user.getLogin(), user.getPassword());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<String> addAccount(@PathVariable UUID userId, @RequestBody TradingAccount account) {
        try {
            userService.addAccountToUser(userId, account);
            return ResponseEntity.ok("Trading account added successfully to user: " + userId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}