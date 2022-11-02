package com.faisal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faisal.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
