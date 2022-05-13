package com.klu.sdp3;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EBookController 
{
		@Autowired
		private EBookService ebookservice;
		
		@Autowired
		private EBookRepository ebookrepository;
		
		@RequestMapping("/ebooks")
		public String ebooks(Model map,HttpSession session,HttpServletRequest request) {
				List<EBookModel> items = ebookservice.getAll();
				map.addAttribute("ebook", items);
				return "ebooks";
		}
		
		@RequestMapping("/savebooks")
		public String savebooks() {
			return "savebooks";
		}
		
		@RequestMapping("/updatebook")
		public String updatebooks() {
			return "updatebooks";
		}
		
		@RequestMapping("/removebooks")
		public String removebooks() {
			return "removebooks";
		}
		
		@RequestMapping("/savebook")
		public String savebook(Model map, @RequestParam String title, @RequestParam String author, @RequestParam int cost, @RequestParam String language, @RequestParam int filesize, @RequestParam("pdf") MultipartFile multipartFile) throws IOException {
			EBookModel  updatebook = new EBookModel();
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			updatebook.setPdf(fileName);
			updatebook.setTitle(title);
	        updatebook.setAuthor(author);
	        updatebook.setCost(cost);
	        updatebook.setLanguage(language);
	        updatebook.setFilesize(filesize);
	        
	        String uploadDir = "src/static/savedpdfs/" + updatebook.getTitle();
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	        
	        if(ebookrepository.save(updatebook) != null) {
	        	List<EBookModel> items = ebookservice.getAll();
				map.addAttribute("ebook", items);
				return "ebooks";
	        }
	        else
	            return "error";
		}
		
		@RequestMapping("/displaypdf")
		public String displaypdf(Model map) {
			List<EBookModel> items = ebookservice.getAll();
			map.addAttribute("ebook", items);
			return "pdf";
		}
		
		@RequestMapping("/updatebooks")
		public String updatebook(@RequestParam String title, @RequestParam String author, @RequestParam int cost, @RequestParam String language, @RequestParam int filesize) {
			    int i=ebookservice.updateEBook(title, author, cost, language, filesize);
			    if(i==1) {
			      return "ebooks";
			    }
			return "error";
		}
		
		@RequestMapping("/removebook")
		public String removebook(@RequestParam String title) {
			EBookModel  updatebook = ebookrepository.findByTitle(title);
	        updatebook.setAuthor(null);
	        updatebook.setCost(0);
	        updatebook.setLanguage(null);
	        updatebook.setFilesize(0);
	        if(ebookrepository.findByTitle(title) == null)
	            return "ebooks";
	        else
	            return "error";
		}
		
		
}
