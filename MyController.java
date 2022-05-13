package com.klu.sdp3;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MyController {
	@Value("${upoadDir}")
	  private String uploadFolder;
	@Autowired
	private MyService myservice;
	
	@RequestMapping("/")
	public String index() {
	    return "index";
	}
	@RequestMapping("/home")
	public String home() {
	    return "index";
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/loginuser")
	public String loginUser(@RequestParam String email,Model model,@RequestParam String password, HttpSession session,HttpServletRequest request) {
	    if(email.equals("admin@gmail.com")&&password.equals("admin")) {
	      return "admin";
	    }
	    if(myservice.findByEmailAndPassword(email,password)!=null) {
	      MyModel mymodel=myservice.findByEmailAndPassword(email,password);
	      session=request.getSession();  
	      session.setAttribute("user",mymodel);
	      return "profile";
	    }
	    else{
	      return "login";
	    }
	  }
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin";	
	}
	
	@RequestMapping("/register")
	public String register(Model model) {
		MyModel mymodel = new MyModel();
		model.addAttribute("mymodel", mymodel);
		return "register";
	}
	@RequestMapping("/signup")
	public String signup(Model model) {
		MyModel mymodel = new MyModel();
		model.addAttribute("mymodel", mymodel);
		return "register";
	}
	
	@RequestMapping("/registeruser")
	public String registeruser(MyModel mymodel) {
		if(mymodel != null) {
			System.out.println(mymodel.getFirstname());
			myservice.save(mymodel);
			return "login";
		}
		return "error";
	}
	
	@RequestMapping("/articles")
	public String articles() {
		return "articles";
	}
	@RequestMapping("/rights")
	public String rights() {
		return "rights";
	}
	@RequestMapping("/duties")
	public String duties() {
		return "duties";
	}
	@RequestMapping("/amendments")
	public String amendments() {
		return "amendments";
	}
	@RequestMapping("/schedules")
	public String schedules() {
		return "schedules";
	}
	@RequestMapping("/updates")
	public String updates() {
		return "updates";
	}
	@RequestMapping("/contactus")
	public String contactus() {
		return "contactus";
	}
	@RequestMapping("/preamble")
	public String preamble() {
		return "preamble";
	}
	@RequestMapping("/elearning")
	public String elearning() {
		return "elearning";
	}
	@RequestMapping("/lms")
	public String lms() {
		return "lms";
	}
	@RequestMapping("/thankyou")
	public String thankyou() {
		return "thankyou";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session,HttpServletRequest request) {
		session=request.getSession();
		session.invalidate();
		return "login";
	}
	@RequestMapping("/savepdf")
	// title, author, cost, language, filesize
	public String savepdf(Model model, @RequestParam String title, @RequestParam String author, @RequestParam int cost, @RequestParam String language, @RequestParam int filesize, @RequestParam("pdf") MultipartFile multipartFile, HttpSession session, HttpServletRequest request) throws IOException {
			session=request.getSession();
	          String n=(String)session.getAttribute("title");  
	         model.addAttribute("title",n);
	         String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
	        String fileName = multipartFile.getOriginalFilename();
	        String filePath = Paths.get(uploadDirectory, fileName).toString();
	        if (fileName == null || fileName.contains("..")) {
	          model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
	          /*
	           * return new ResponseEntity<>("Sorry! Filename contains invalid path sequence "
	           * + fileName, HttpStatus.BAD_REQUEST);
	           */
	        }
	        try 
	        {
	          File dir = new File(uploadDirectory);
	          if (!dir.exists()) {
	            dir.mkdirs();
	          }
	          // Save the file locally
	          BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	          stream.write(multipartFile.getBytes());
	          stream.close();
	        } 
	        catch (Exception e) {
	          e.printStackTrace();
	        }
	        byte[] imageData = multipartFile.getBytes();
	        EBookModel  updatebook = new EBookModel();
			updatebook.setPdf(fileName);
			updatebook.setTitle(title);
	        updatebook.setAuthor(author);
	        updatebook.setCost(cost);
	        updatebook.setLanguage(language);
	        updatebook.setFilesize(filesize);
	        EBookService eBookService = new EBookService(null);
			eBookService.store(updatebook);
	        model.addAttribute("message","Image Uploaded Sucessfully!!!!");
	        return "pdf";
	    
	  }
	}
	
