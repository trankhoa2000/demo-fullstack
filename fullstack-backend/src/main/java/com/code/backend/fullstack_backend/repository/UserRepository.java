package com.code.backend.fullstack_backend.repository;

import com.code.backend.fullstack_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
