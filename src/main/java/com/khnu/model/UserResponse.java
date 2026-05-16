package com.khnu.model;

import java.util.UUID;
import java.util.List;

public class UserResponse {
    private UUID id;
    private String login;
    private String email;
    private List<AccountResponse> tradingAccounts;

    // Конструкторы
    public UserResponse() {}

    // Геттеры и Сеттеры
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<AccountResponse> getTradingAccounts() { return tradingAccounts; }
    public void setTradingAccounts(List<AccountResponse> tradingAccounts) { this.tradingAccounts = tradingAccounts; }

    // ВНУТРЕННИЙ КЛАСС ДЛЯ АККАУНТА (БЕЗ ПОЛЯ USER!)
    public static class AccountResponse {
        private UUID id;

        public AccountResponse() {}

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
    }
}