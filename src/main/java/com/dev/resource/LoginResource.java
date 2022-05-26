package com.dev.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dev.entidades.Login;
import com.dev.service.LoginService;

@CrossOrigin
@RestController
@RequestMapping(value="/login")
public class LoginResource {

	@Autowired
	LoginService loginService;
	
	
	@PostMapping(value = "/findLogin")
	public ModelAndView buscarLogin(@ModelAttribute("login") Login obj,
			RedirectAttributes redirAttr){
		
		Login login = loginService.buscarLogin(obj);
		
		ModelAndView mv = new ModelAndView("redirect:/exemplo-view-obj");

		redirAttr.addFlashAttribute("login", login);
		return mv;
	}
}
