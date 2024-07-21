package com.banking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.banking.dtos.UserDTO;
import com.banking.exception.DuplicateRecordException;
import com.banking.form.LoginForm;
import com.banking.form.UserRegistrationForm;
import com.banking.service.UserServiceInt;
import com.banking.util.DataUtility;



@RestController
public class LoginCtl extends BaseCtl {

	private Logger log = Logger.getLogger(LoginCtl.class.getName());

	protected static final String OP_SIGNIN = "SignIn";
	protected static final String OP_REGISTER = "Register";
	protected static final String OP_SIGNUP = "SignUp";
	protected static final String OP_LOGOUT = "Logout";
	protected static final String OP_CONFIRM = "Confirm";

	@Autowired
	private UserServiceInt userServiceInt;

	@ModelAttribute
	public void preload(Model model) {

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Male", "Male");
		map2.put("Female", "Female");
		model.addAttribute("gender", map2);

	}

	@GetMapping("/home/login")
	public ModelAndView display(@ModelAttribute("form") LoginForm form, HttpSession session, Model model) {
		log.info("LoginCtl login display method start");
		ModelAndView mv =new ModelAndView();
		mv.setViewName("login");
		if (session.getAttribute("user") != null) {
			session.invalidate();
			model.addAttribute("success", "You have logout Successfully!!!");
		}

		log.info("LoginCtl login display method End");
		return mv;
	}

	@PostMapping("/home/login")
	public ModelAndView submit(@RequestParam String operation, HttpSession session,
			@Valid @ModelAttribute("form") LoginForm form, BindingResult result, Model model) {
		log.info("LoginCtl login submit method start");
		System.out.println("In dopost  LoginCtl");
		ModelAndView mv =new ModelAndView();
		mv.setViewName("login");
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return new ModelAndView("redirect:/home/login");
		}

		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {
			return mv;
		}

		if (result.hasErrors()) {
			System.out.println(result);
			return mv;
		}

		UserDTO bean = userServiceInt.authentication((UserDTO) form.getDTO());

		if (bean != null) {
			
			System.out.println(bean.toString());
			session.setAttribute("user", bean);
			return new ModelAndView("redirect:/home");
		}

		if (bean == null) {

			model.addAttribute("error", "Login Id & Password Invalid");
		}
		log.info("LoginCtl login submit method End");
		return mv;
	}

	@GetMapping("/home/user")
	public ModelAndView display(@ModelAttribute("form") UserRegistrationForm form, Model model) {
		log.info("LoginCtl signUp display method start");
		log.info("LoginCtl signUp display method End");
		ModelAndView mv =new ModelAndView();
		mv.setViewName("userRegister");
		return mv;
	}

	@PostMapping("/home/user")
	public ModelAndView submit(@RequestParam String operation, @Valid @ModelAttribute("form") UserRegistrationForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		log.info("LoginCtl signUp submit method start");
		ModelAndView mv =new ModelAndView();
		mv.setViewName("userRegister");
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return new ModelAndView("redirect:/home/user");
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return mv;
		}

		try {
			if (OP_REGISTER.equalsIgnoreCase(form.getOperation())) {
				UserDTO entity = (UserDTO) populateDTO(form.getDTO(), request);
				entity.setRoleId(2L);
				entity.setRoleName("USER");
				userServiceInt.add(entity);
				model.addAttribute("success", "User Registered Successfully!!!!");
				return mv;
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return mv;
		}

		log.info("LoginCtl signUp submit method end");
		return mv;
	}

	
}
