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

	//return full html text to client
	/*
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="client_name", required=false, defaultValue="World") String client_name, Model model) {
		model.addAttribute("client_name", client_name);

		//handling datetime
		SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String date_time = formatter.format(date);
		model.addAttribute("date_time", date_time);

		return "greeting";
	} */

	//return only text to client
	@GetMapping("/greeting")
	public ResponseEntity<String> greeting(@RequestParam(name="client_name", required=false, defaultValue="World") String client_name, Model model) {
		model.addAttribute("name", client_name);
		SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String date_time = formatter.format(date);
		return new ResponseEntity<>("Hi " + client_name + ", it's " + date_time + " over here!", HttpStatus.OK);
	}

}
