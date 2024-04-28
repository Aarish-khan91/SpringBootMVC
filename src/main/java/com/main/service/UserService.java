package com.main.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.UserModel;
import com.main.repository.UserRepository;

@Service
public class UserService {

	private UserRepository repository;

	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}

	public UserModel registerUser(String login, String password, String email) {
		if (login == null && password == null) {
			return null;
		} else {
			if (repository.findFirstByLogin(login).isPresent()) {
				System.out.println("Duplicate login");
				return null;
			}
			UserModel userModel = new UserModel();
			userModel.setLogin(login);
			userModel.setEmail(email);
			userModel.setPassword(password);
			return repository.save(userModel);
		}
	}

	public UserModel authentication(String login, String password) {
		return repository.findByLoginAndPassword(login, password).orElse(null);
	}
}
