package com.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

	Optional<UserModel> findByLoginAndPassword(String login, String password);
	Optional<UserModel> findFirstByLogin(String login);

}
