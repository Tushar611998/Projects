package com.smart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home() {
		return"home";
	}
	
	@GetMapping("/signup")
	public String signup(Model m) {
		m.addAttribute("user", new User());
		return "signup";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
//	User registrationUser controller
	@PostMapping ("/register")
	public String register(@Valid @ModelAttribute User user,BindingResult bs, Model model, @RequestParam(value="checked",defaultValue = "false") boolean checked, HttpSession session) {
		try {
			
			if(bs.hasErrors()) {
				System.out.println(bs);
				return "signup";
			}
			if(!checked) {
				System.out.println("the check box must be checked");
				throw new Exception("the check box must be checked");
				
			}
			
			user.setEnabled(true);
			user.setUserrole("ROLE_USER");
			user.setUserImage("default.png");
			user.setPassword(encoder.encode(user.getPassword()));
			
			List<User> users= repository.findByEmail(user.getEmail());
			
			if(users.size()> 0) {
				throw new Exception("The email Address is already exist..!!!");
			}
			
			repository.save(user);
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Registration Successfull..!!","alert-success"));
			return "signup";
			
		}
		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message("Something Went Wrong..!!" + e.getMessage(), "alert-danger"));
			return "signup";
			
		}
		
		
	}

}
