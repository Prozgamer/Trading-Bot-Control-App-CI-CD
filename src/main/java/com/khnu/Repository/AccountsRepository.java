package com.khnu.Repository;

import com.khnu.model.TradingAccount;
import com.khnu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountsRepository extends JpaRepository<TradingAccount, UUID> {
}
