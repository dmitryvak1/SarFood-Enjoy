package com.example.foodfamily.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.foodfamily.entity.User;
import com.example.foodfamily.service.UserService;
import com.example.foodfamily.utils.ControllerUtils;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userSevice;

	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}

	@PostMapping("/registration")
	public String addUser(@Valid User user, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

			model.mergeAttributes(errors);

			return "registration";
		}

		if (!userSevice.addUser(user)) {
			model.addAttribute("usernameError", "User with such login already exists!");
			return "registration";
		}

		return "redirect:/login";
	}

	@GetMapping("/activate/{code}")
	public String activate(Model model, @PathVariable String code) {
		boolean isActivated = userSevice.activateUser(code);

		if (isActivated) {
			model.addAttribute("message", "User successfully activated");
		} else {
			model.addAttribute("message", "Activation code is not found!");
		}
		return "login";
	}
}
