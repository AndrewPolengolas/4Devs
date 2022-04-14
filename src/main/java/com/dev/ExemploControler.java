package com.dev;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exemplo")
public class ExemploControler {

	@GetMapping("/mensagem1")
	public String saudar1(Model modelo) {
		return "mensagemView";
	}

	@GetMapping("/exemplo-view-obj")
	public ModelAndView exemploViewObj() {
		ModelAndView mv = new ModelAndView("view-exemplo");
		mv.addObject("texto", "Texto gerado no Controller v2");
		mv.addObject("numero", 100);
		mv.addObject("dataHora", LocalDateTime.now());
		return mv;
	}
}