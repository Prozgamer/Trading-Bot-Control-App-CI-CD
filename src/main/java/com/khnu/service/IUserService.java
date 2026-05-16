package com.khnu.service;

import com.khnu.model.User;
import com.khnu.model.TradingAccount;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    User createUser(String login, String password);

    Optional<User> getUserById(UUID id);
    List<User> getAllUsers();

    boolean updateUser(UUID id, String login, String password);

    boolean deleteUser(UUID id);

    void addAccountToUser(UUID userId, TradingAccount account);

    boolean updateUserProfile(UUID userId, String email, String password);

    Optional<TradingAccount> getAccountById(UUID userId, UUID accountId);

    TradingAccount updateAccountConfig(UUID accountId, TradingAccount newConfig);

    boolean toggleBotStatus(UUID accountId, boolean run);

    List<User> findAll();
}