package com.khnu.controller;

import com.khnu.model.User;
import com.khnu.service.IUserService;
import jakarta.validation.Valid;
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

    // 1. Реєстрація
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user.getLogin(), user.getPassword());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // 2. Отримання профілю за ID
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userid) {
        User user = userService.getUserById(userid)
                .orElseThrow(() -> new RuntimeException("User with id " + userid + " not found"));

        return ResponseEntity.ok(user);
    }

    // 3. Оновлення профілю
    @PutMapping("/{userid}")
    public ResponseEntity<User> updateProfile(@PathVariable UUID userid, @RequestBody User userUpdate) {
        // Тут має бути виклик сервісу для оновлення email/password
        // Поки що повернемо OK для тесту в Postman
        return ResponseEntity.ok(userUpdate);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}