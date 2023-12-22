package com.example.demo.controller.nonlogin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.Administrator;
import com.example.demo.entity.Customers;
import com.example.demo.service.AdministratorService;
import com.example.demo.service.CustomersService;

@RestController
public class Register {

	@Autowired
	CustomersService customersService;
	
	@Autowired
	AdministratorService administratorService;
	
	@GetMapping("/register")
	public ModelAndView registerPage(Model model) {
		ModelAndView modelAndView = new ModelAndView("/nonlogin/register");
		if (model.containsAttribute("emailExists")) {
	        modelAndView.addObject("emailExists", model.getAttribute("emailExists"));
		}
		if (model.containsAttribute("imageNotValid")) {
	        modelAndView.addObject("imageNotValid", model.getAttribute("imageNotValid"));
		}
		return modelAndView.addObject("register", new Customers());
	}
	
	@PostMapping("/validateRegistration")
	public ModelAndView validateRegistration(@ModelAttribute("register") Customers register, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		
		List<Customers> customers = customersService.getCustomers();
		String emailToCheck = register.getEmailId();
	    boolean emailExists = customers.stream().anyMatch(customer -> customer.getEmailId().equals(emailToCheck));
	    if (emailExists) {
	    	redirectAttributes.addFlashAttribute("emailExists", true);
	        return new ModelAndView("redirect:/register");
	    } 
	    else { 
	    	if (isImageFile(file)) {
	    		try {
	                String originalFileName = file.getOriginalFilename();
	                String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
	                Path uploadPath = Paths.get("src/main/resources/static/userprofilepictures/");
	                Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
	                register.setProfilePic(fileName);
	                Date currentDate = new Date(new java.util.Date().getTime());
	                register.setRegisterDate(currentDate);
	                customersService.addCustomer(register);
	                Administrator user = new Administrator();
	                user.setUserName(register.getEmailId());
	                user.setPassword(register.getPassword());
	                user.setRole("ROLE_USER");
	                administratorService.addUser(user);
	                
		            return new ModelAndView("redirect:/customerlogin");
	            } 
	    		catch (Exception e) {
		            return new ModelAndView("redirect:/home"); 
	            }
	    	}
	    	else {
		    	redirectAttributes.addFlashAttribute("imageNotValid", true);
		    	return new ModelAndView("redirect:/register");
	    	}
	    }
	}
	
	private boolean isImageFile(MultipartFile file) {
	    List<String> allowedImageTypes = Arrays.asList("image/jpeg", "image/png", "image/gif");
	    return allowedImageTypes.contains(file.getContentType());
	}
}
