package com.khnu.controller;

import com.khnu.model.User;
import com.khnu.model.UserRegisterDto;
import com.khnu.model.UserResponse;
import com.khnu.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
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

    // 1. Реєстрація (Пока оставляем так, но на будущее лучше тоже перевести на DTO)
    @PostMapping(value = "/auth/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> register(@Valid @RequestBody UserRegisterDto dto) {
        // Вызываем сервис, передавая чистые строки из DTO
        User createdUser = userService.createUser(dto.getLogin(), dto.getPassword(), dto.getEmail());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // 2. Отримання профілю за ID (ТЕПЕРЬ БЕЗОПАСНО ЧЕРЕЗ DTO)
    @GetMapping("/{userid}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID userid) {
        User user = userService.getUserById(userid)
                .orElseThrow(() -> new RuntimeException("User with id " + userid + " not found"));

        return ResponseEntity.ok(convertToDto(user));
    }

    // 3. Оновлення профілю
    @PutMapping("/{userid}")
    public ResponseEntity<User> updateProfile(@PathVariable UUID userid, @RequestBody User userUpdate) {
        return ResponseEntity.ok(userUpdate);
    }

    // 4. Отримання всіх користувачів (ИСПРАВЛЕН МАППИНГ ПУТИ)
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Вспомогательный метод маппинга, чтобы не дублировать код
    private UserResponse convertToDto(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setEmail(user.getEmail());

        if (user.getTradingAccounts() != null) {
            List<UserResponse.AccountResponse> accountDtos = user.getTradingAccounts().stream().map(acc -> {
                UserResponse.AccountResponse accDto = new UserResponse.AccountResponse();
                accDto.setId(acc.getId());
                return accDto;
            }).collect(Collectors.toList());
            dto.setTradingAccounts(accountDtos);
        }
        return dto;
    }
}