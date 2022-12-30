package com.rohit.fullstackbackend.repository;

import com.rohit.fullstackbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
