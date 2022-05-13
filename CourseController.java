package com.klu.sdp3;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CourseController 
{
		@Autowired
		private CourseService courseservice;
		
		@Autowired
		private CourseRepository courserepository;
		
		@RequestMapping("/courses")
		public String courses(Model map,HttpSession session,HttpServletRequest request) {
				List<CourseModel> items = courseservice.getAll();
				map.addAttribute("course", items);
				return "courses";
		}
		
		@RequestMapping("/savecourses")
		public String savecourses() {
			return "savecourses";
		}
		@RequestMapping("/enrollcourses")
		public String enrollcourses(HttpSession session,HttpServletRequest request) {
			  session=request.getSession();
		      String n=(String)session.getAttribute("email");
		      
			return "displaycourse";
		}
		
		@RequestMapping("/updatecourse")
		public String updatecourses() {
			return "updatecourses";
		}
		
		@RequestMapping("/removecourses")
		public String removecourses() {
			return "removecourses";
		}
		// title, writer, duration, language
		@RequestMapping("/savecourse")
		public String savebook(Model map, @RequestParam String title, @RequestParam String writer, @RequestParam int duration, @RequestParam String language) throws IOException {
			CourseModel updatebook = new CourseModel();
			updatebook.setTitle(title);
			updatebook.setWriter(writer);
			updatebook.setDuration(duration);
			updatebook.setLanguage(language);
	        
	        if(courserepository.save(updatebook) != null) {
	        	List<CourseModel> items = courseservice.getAll();
				map.addAttribute("course", items);
				return "courses";
	        }
	        else
	            return "error";
		}
		
		@RequestMapping("/updatecourses")
		public String updatebook(Model map, @RequestParam String title, @RequestParam String writer, @RequestParam int duration, @RequestParam String language) {
			    int i=courseservice.updateCourse(title, writer, duration, language);
			    
			    if(i==1) {
			    	List<CourseModel> items = courseservice.getAll();
					map.addAttribute("course", items);
			      return "courses";
			    }
			return "error";
		}
		
		@RequestMapping("/removecourse")
		public String removebook(@RequestParam String title) {
			CourseModel  updatebook = courserepository.findByTitle(title);
	        updatebook.setTitle(null);
	        if(courserepository.findByTitle(title) == null)
	            return "ebooks";
	        else
	            return "error";
		}
		
		
		
}