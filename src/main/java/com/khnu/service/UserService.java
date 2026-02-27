package com.khnu.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.khnu.model.User;
import com.khnu.model.TradingAccount;

@Service
public class UserService implements IUserService {
    private final Map<UUID, User> users = new HashMap<>();

    @Override
    public User createUser(String login, String password) {
        User user = new User(login, password);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public boolean updateUser(UUID id, String login, String password) {
        User user = users.get(id);
        if (user != null) {
            user.setLogin(login);
            user.setPassword(password);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(UUID id) {
        return users.remove(id) != null;
    }

    @Override
    public void addAccountToUser(UUID userId, TradingAccount account) {
        User user = users.get(userId);
        if (user != null) {
            // Генерируем ID для нового счета, если его нет
            if (account.getId() == null) {
                account.setId(UUID.randomUUID());
            }
            user.addTradingAccount(account); // Вызываем тот метод с буквой 't', который мы исправили
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }
}