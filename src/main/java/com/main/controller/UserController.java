package com.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.main.entity.UserModel;
import com.main.service.UserService;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerRequest", new UserModel());
		return "register_page";
	}

	@GetMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("loginRequest", new UserModel());
		return "login_page";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute UserModel userModel) {
		System.out.println("register request : " + userModel);
		UserModel registerUser = userService.registerUser(userModel.getLogin(), userModel.getPassword(),
				userModel.getEmail());
		return registerUser == null ? "error_page" : "redirect:/login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute UserModel userModel, Model model) {
		System.out.println("register request : " + userModel);
		UserModel authentication = userService.authentication(userModel.getLogin(), userModel.getPassword());

		if (authentication != null) {
			model.addAttribute("userLogin", authentication.getLogin());
			return "personal_page";
		} else
			return "error_page";

	}
}
