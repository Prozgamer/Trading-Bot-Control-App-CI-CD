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

    @PostMapping("/{userid}/createbot")
    public ResponseEntity<String> createBot(@PathVariable UUID userid, @RequestBody TradingAccount account) {
        try {
            userService.addAccountToUser(userid, account);
            return new ResponseEntity<>("Bot created successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 2. ПОЛУЧИТЬ НАСТРОЙКИ
    @GetMapping("/{id}/config")
    public ResponseEntity<TradingAccount> getConfig(@PathVariable UUID id) {
        return userService.getAccountById(UUID.randomUUID(), id) // первый аргумент заглушка, если метод ищет тупо по id аккаунта
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. ИЗМЕНИТЬ НАСТРОЙКИ (Для фронтенда / Postman)
    @PatchMapping("/{id}/config")
    public ResponseEntity<TradingAccount> updateConfig(@PathVariable UUID id, @RequestBody TradingAccount config) {
        TradingAccount updated = userService.updateAccountConfig(id, config);
        return ResponseEntity.ok(updated);
    }

    // 4. ЗУПИНИТИ/ЗАПУСТИТИ ТОРГІВЛЮ (Переключатель для "Panic Button")
    @PostMapping("/{id}/toggle")
    public ResponseEntity<String> toggleBot(@PathVariable UUID id, @RequestParam boolean run) {
        if (userService.toggleBotStatus(id, run)) {
            String status = run ? "started" : "stopped";
            return ResponseEntity.ok("Bot " + status + " successfully");
        }
        return ResponseEntity.notFound().build();
    }
}