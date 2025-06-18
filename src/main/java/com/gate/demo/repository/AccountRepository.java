package com.gate.demo.repository;

import com.gate.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("SELECT a FROM Account a WHERE a.user.id = :user_id")
    List<Account> findByUserId(@Param("user_id") UUID user_id);

}
