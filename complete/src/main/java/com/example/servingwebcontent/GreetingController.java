package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDateTime; //changed from date to local date to make it more emcompassing for different time zones
import java.time.format.DateTimeFormatter;

@Controller
public class GreetingController{

	@GetMapping("/greeting")
	@ResponseBody
	public String greeting(@RequestParam(name="client_name", required=false, defaultValue="World") String client_name, Model model) {
		model.addAttribute("client_name", client_name);

		//handling datetime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime date = LocalDateTime.now();
		String date_time = formatter.format(date);
		model.addAttribute("date_time", date_time);

		//return "greeting"; //this is our view
		return "Hi " + client_name + ", it's " + date_time + " over here!";
	}

	//another way to return just a string as a http response!
	/*
	@GetMapping("/greeting")
	public ResponseEntity<String> greeting(@RequestParam(name="client_name", required=false, defaultValue="World") String client_name, Model model) {
		model.addAttribute("name", client_name);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String date_time = formatter.format(date);
		return new ResponseEntity<>("Hi " + client_name + ", it's " + date_time + " over here!", HttpStatus.OK);
	}*/

}
