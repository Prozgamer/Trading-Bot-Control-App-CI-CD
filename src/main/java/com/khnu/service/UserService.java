package com.khnu.service;

import java.util.*;

import com.khnu.Repository.AccountsRepository;
import com.khnu.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.khnu.model.User;
import com.khnu.model.TradingAccount;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountsRepository accountsRepository;

    @Override
    public User createUser(String login, String password) {
        User user = new User(login, password);
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public boolean updateUser(UUID id, String login, String password) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setLogin(login);
            user.setPassword(password);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    @Transactional
    public void addAccountToUser(UUID userId, TradingAccount account) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        if (account.getId() == null) {
            account.setId(UUID.randomUUID());
        }
        account.setUser(user);
        accountsRepository.save(account);
    }

    @Override
    public boolean updateUserProfile(UUID userId, String email, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
            user.setEmail(email); // Ты принимаешь email в аргументах, но не обновляешь его
            user.setPassword(password);
            userRepository.save(user);
            return true;
    }

    @Override
    public Optional<TradingAccount> getAccountById(UUID userId, UUID accountId) {
        return accountsRepository.findById(accountId);
    }

    @Override
    @Transactional
    public TradingAccount updateAccountConfig(UUID accountId, TradingAccount newConfig) {
        TradingAccount account = accountsRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));

        // Обновляем только изменяемые параметры настроек
        account.setHtimeframe(newConfig.getHtimeframe());
        account.setLtimeframe(newConfig.getLtimeframe());
        account.setLookbackCandles(newConfig.getLookbackCandles());
        account.setMaxDdPrc(newConfig.getMaxDdPrc());
        account.setMinFvgSize(newConfig.getMinFvgSize());
        account.setCandlesToLive(newConfig.getCandlesToLive());
        account.setStructureLookback(newConfig.getStructureLookback());
        account.setConfirmationWaitBars(newConfig.getConfirmationWaitBars());
        account.setMinStopDistancePoints(newConfig.getMinStopDistancePoints());
        account.setMaxStopDistancePoints(newConfig.getMaxStopDistancePoints());
        account.setMornStartTime(newConfig.getMornStartTime());
        account.setMornEndTime(newConfig.getMornEndTime());
        account.setEveStartTime(newConfig.getEveStartTime());
        account.setEveEndTime(newConfig.getEveEndTime());

        return accountsRepository.save(account);
    }

    @Override
    @Transactional
    public boolean toggleBotStatus(UUID accountId, boolean run) {
        Optional<TradingAccount> accountOpt = accountsRepository.findById(accountId);
        if (accountOpt.isPresent()) {
            TradingAccount account = accountOpt.get();
            account.setRunning(run);
            accountsRepository.save(account);
            return true;
        }
        return false;
    }
}