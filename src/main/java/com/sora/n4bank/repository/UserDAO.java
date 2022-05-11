package com.sora.n4bank.repository;

import com.sora.n4bank.model.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDAO {
    User get(String username, String password);

    User get(UUID userId);
}
