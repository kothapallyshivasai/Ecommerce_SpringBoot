package com.example.demo.controller.nonlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.Administrator;
import com.example.demo.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

@RestController
public class Login {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/customerlogin")
    public ModelAndView loginPage() {
        return new ModelAndView("/nonlogin/login", "customer", new Administrator());
    }

    @PostMapping("/validateCustomerLogin")
    public ModelAndView validateCustomerLogin(@ModelAttribute("customer") Administrator customer, RedirectAttributes redirectAttributes,HttpSession session) {
    	
        if (administratorService.validateAdministrator(customer)) {
            Administrator customer2 = administratorService.getUserById(customer.getUserName()).orElse(null);
            
            if (customer2 != null && customer2.getRole().equals("ROLE_USER")) {
            	session.setAttribute("email_id",customer2.getUserName());
                return new ModelAndView("redirect:/customer/userhome");
            }
            return new ModelAndView("redirect:/admin/adminhome");
        } else {
            redirectAttributes.addFlashAttribute("invalid", true);
            return new ModelAndView("redirect:/customerlogin");
        }
    }
}
