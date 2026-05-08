package com.lipari.bank.account;

import com.lipari.bank.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByIban(String iban);
    @Query("SELECT a FROM Account a WHERE (:min IS NULL OR a.balance >= :min) AND (:max IS NULL OR a.balance <= :max)")
    List<Account> findByBalanceRange(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
}
