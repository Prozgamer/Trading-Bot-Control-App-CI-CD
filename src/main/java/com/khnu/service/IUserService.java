package com.khnu.service;

import com.khnu.model.User;
import com.khnu.model.TradingAccount; // Импортируем новую модель

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    // Создание пользователя с логином и паролем
    User createUser(String login, String password);

    Optional<User> getUserById(UUID id);
    List<User> getAllUsers();

    // Обновление логина/пароля
    boolean updateUser(UUID id, String login, String password);

    boolean deleteUser(UUID id);

    // НОВЫЙ МЕТОД: Добавление торгового счета пользователю
    void addAccountToUser(UUID userId, TradingAccount account);
}