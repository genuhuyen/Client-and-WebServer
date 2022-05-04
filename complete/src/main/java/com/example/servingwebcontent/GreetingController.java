package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Date;
import java.text.SimpleDateFormat;

@Controller
public class GreetingController{
/*
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}*/

	@GetMapping("/greeting")
	public ResponseEntity<String> greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
		Date date = new Date();
		return new ResponseEntity<>("Hello, " + name + " it's " + formatter.format(date) + " over here!", HttpStatus.OK);
	}

}
