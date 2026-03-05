package com.khnu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public class User {
    private UUID id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String login;

    private String password;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    private List<TradingAccount> tradingAccounts = new ArrayList<>();

    public User() {
        this.id = UUID.randomUUID();
    }

    public User(String login, String password) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
    }


    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    public List<TradingAccount> getTradingAccounts() {
        return tradingAccounts;
    }

    public void addTradingAccount(TradingAccount account){
        if(this.tradingAccounts == null){
            this.tradingAccounts = new ArrayList<>();
        }
        this.tradingAccounts.add(account);
    }

    public void disableTradingAccount(UUID accountId) {
        for (TradingAccount account : this.tradingAccounts) {
            if (account.getId().equals(accountId)) {
                account.setRunning(false);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", tradingAccounts=" + tradingAccounts +
                '}';
    }
}