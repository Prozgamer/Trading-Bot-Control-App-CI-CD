package com.khnu.controller;

import com.khnu.model.TradingAccount;
import com.khnu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final IUserService userService;

    @Autowired
    public AccountController(IUserService userService) {
        this.userService = userService;
    }

    // 1. Додати торговий рахунок (createbot)
    @PostMapping("/{userid}/createbot")
    public ResponseEntity<String> createBot(@PathVariable UUID userid, @RequestBody TradingAccount account) {
        try {
            userService.addAccountToUser(userid, account);
            return new ResponseEntity<>("Bot created successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 2. Змінити налаштування
    @PatchMapping("/{id}/config")
    public ResponseEntity<String> updateConfig(@PathVariable UUID id, @RequestBody TradingAccount config) {
        // Тут логіка оновлення timeframe або riskLevel
        return ResponseEntity.ok("Configuration updated");
    }

    // 3. Зупинити торгівлю
    @PostMapping("/{id}/stop")
    public ResponseEntity<String> stopBot(@PathVariable UUID id) {
        return ResponseEntity.ok("Bot stopped");
    }
}