package com.khnu.model;

import jakarta.persistence.*; // Импортируем всё для работы с БД
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity // Говорим Spring, что это таблица
@Table(name = "users") // Имя таблицы в Postgres
public class User {

    @Id // Это первичный ключ
    private UUID id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Column(name = "name") // В твоем SQL колонка называется 'name', а в Java 'login'
    private String login;

    private String password;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(unique = true) // Соответствует UNIQUE в SQL
    private String email;

    // Настройка связи "Один-ко-Многим"
    // mappedBy = "user" — это имя поля в классе TradingAccount
    // CascadeType.ALL — если удалим юзера, удалятся и его аккаунты
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TradingAccount> tradingAccounts = new ArrayList<>();

    public User() {
        // Оставляем генерацию UUID, если не передаем его вручную
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    public User(String login, String password) {
        this(); // Вызов конструктора по умолчанию для генерации ID
        this.login = login;
        this.password = password;
    }

    // --- Геттеры и Сеттеры ---
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<TradingAccount> getTradingAccounts() { return tradingAccounts; }

    public void setTradingAccounts(List<TradingAccount> tradingAccounts) {
        this.tradingAccounts = tradingAccounts;
    }

    // Помощник для добавления аккаунта
    public void addTradingAccount(TradingAccount account) {
        tradingAccounts.add(account);
        account.setUser(this); // Устанавливаем обратную связь для Hibernate
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