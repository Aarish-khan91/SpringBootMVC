package com.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.UserModel;
@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	Optional<UserModel> findByLoginAndPassword(String login, String password);
	Optional<UserModel> findFirstByLogin(String login);

}
