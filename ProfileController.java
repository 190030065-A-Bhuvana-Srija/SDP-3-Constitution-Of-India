package com.klu.sdp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
	
	@Autowired
	private ProfileService profileservice;
	
	@RequestMapping("/profile")
	public String profile() {
		return "profile";
	}
	
	@RequestMapping("/saveprofile")
	public String saveprofile(ProfileModel profilemodel, Model model) {
		if(profilemodel != null) {
			profileservice.save(profilemodel);
			model.addAttribute("profile", profilemodel);
			return "profile";
		}
		return "error";
	}
}
