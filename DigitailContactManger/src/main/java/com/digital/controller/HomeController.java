package com.digital.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.digital.dao.UserRepository;
import com.digital.entitys.User;
import com.digital.message.Message;



@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
 @RequestMapping("/") 	
  public String home(Model model) {
	 model.addAttribute("title","Home-Digital Contact Manager");
	  return "home";
  }
	
 @RequestMapping("/signup")	
 public String signUp(Model model) {
	 model.addAttribute("title","Register-Digital Contact Manager");
	 model.addAttribute("user:",new User());
	  return "signup";
 }
 
 
 @RequestMapping("/about")	
 public String about(Model model) {
	 model.addAttribute("title","About-Digital Contact Manager");
	  return "about";
 }
 
 // handler for register user
 
 @PostMapping(value = "/do_register")
 public String registerUser(@ModelAttribute("user")User user, @RequestParam(value = "agreement", defaultValue = "false")boolean agreement,Model model,HttpSession session)
 {
	 try {

		 if(!agreement) {
			 System.out.println("you have not agree terms and conditions");
			 throw new Exception("you have not agree terms and conditions");
		 }
		 user.setRole("ROLE_USER");
		 user.setEnable(true);
		 user.setImageUrl("default.png");
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		 
		 

		 
		 
		 System.out.println("Agreement "+agreement);
		 System.out.println("User "+user);
		 
		User result=  this.userRepository.save(user);
		model.addAttribute("user",new User());
		session.setAttribute("message", new Message("Succsessfully register","alert-Sucsess"));
		return "signup";
		 
		
	} catch (Exception e) {
		e.printStackTrace();
		model.addAttribute("user",user);
		session.setAttribute("message", new Message("Something went to wrong!!"+e.getMessage(),"alert-error"));
	
		 return "signup";
	}
	
 }

 // handler for custom login
 
 @RequestMapping("/signin")
 public String customeLogin(Model model) {
	 
	 model.addAttribute("title","LOGIN-Digital Contact Manager");
	 return "login";
 }
 
 }

	
	

