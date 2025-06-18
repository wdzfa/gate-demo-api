package com.gate.demo.repository;

import com.gate.demo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    boolean existsByEmail(String email);

}
