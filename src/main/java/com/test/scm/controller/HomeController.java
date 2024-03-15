package com.test.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.scm.dao.UserRepository;
import com.test.scm.entity.User;
import com.test.scm.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
//	public HomeController(UserRepository userRepository) {
//		// TODO Auto-generated constructor stub
//		this.userRepository=userRepository;
//	}
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("title","home-Smart Contact Manager");
		return "home";
	}
	
	@GetMapping("/")
	public String about(Model model) {
		model.addAttribute("title","about-Smart Contact Manager");
		return "about";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title","signup-Smart Contact Manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@PostMapping("/do_register")
	public String doRegiser(@ModelAttribute("user") User user,@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,HttpSession session) {


		try {
			if(!agreement) {
				System.out.println("You are not agree with terms and conditions");
				throw new Exception("exception");
			}
			
			user.setuEnable(true);
			user.setuRole("ROLE_USER");
			user.setuImageUrl("/image/difdj.png");
			user.setuPassword(passwordEncoder.encode(user.getuPassword()));
			
			model.addAttribute("user", new User());
			
			userRepository.save(user);
			session.setAttribute("message", new Message("successfully register","alert-success"));

			return "signup";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("some thing went wrong!!"+e.getMessage(),"alert-danger"));
			return "signup";
		}
		
	}
	
	
	@GetMapping("/signin")
	public String longin(Model model) {
		model.addAttribute("title","This is Login page");
		return "signin";
	}
}
