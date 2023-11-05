package com.example.demo.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Products;
import com.example.demo.service.ProductsService;

@RestController
public class AddProduct {
	
	@Autowired
	ProductsService productsService;
	
	@GetMapping("/admin/adminaddproduct")
	public ModelAndView addProduct(Model model) {
		model.addAttribute("product", new Products());
		return new ModelAndView("admin/addproduct");
	}
	
	@PostMapping("/admin/adminAddProduct")
	public ModelAndView addAdminProduct(@RequestParam("productPicture") MultipartFile file, @ModelAttribute("product") Products product, Model model) {
		try {
          if(!productsService.productIsExist(product.getProductCode())) {
	            String originalFileName = file.getOriginalFilename();
	            String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
	            Path uploadPath = Paths.get("src/main/resources/static/products/");
	            Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
	            product.setProductImage("products/" + fileName);
	            productsService.addProduct(product);
          }    
      } 
		catch (Exception e) {
		      return new ModelAndView("redirect:/admin/adminproducts");
      }
      return new ModelAndView("redirect:/admin/adminproducts");
	}
	
	@GetMapping("/admin/admindeleteproduct/{productCode}")
	public ModelAndView deleteProduct(Model model, @PathVariable("productCode") int productCode) {
        Products product = (Products) productsService.getProductById(productCode);

        if (product != null) {
            String productImage = product.getProductImage();
            if (productImage != null && !productImage.isEmpty()) {
                try {
                    Path imagePath = Paths.get("src/main/resources/static/" + productImage);
                    Files.deleteIfExists(imagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            productsService.deleteProduct(productCode);
            return new ModelAndView("redirect:/admin/adminproducts");
        } else {
            return new ModelAndView("redirect:/admin/adminproducts");
        }
    }
	
	@GetMapping("/admin/admineditproduct/{productCode}")
	public ModelAndView editProduct(Model model, @PathVariable("productCode") int productCode) {
		Products product = (Products) productsService.getProductById(productCode);
		
		model.addAttribute("product", product);
		return new ModelAndView("admin/editproduct");
	}
	
	@PostMapping("/admin/adminEditProduct")
	public ModelAndView editAdminProduct(@ModelAttribute("product") Products product, Model model) {
		try {

			productsService.updateProduct(product);
			return new ModelAndView("redirect:/admin/adminproducts");
		}
		catch(Exception e) {
			return new ModelAndView("redirect:/admin/adminproducts");
		}
	}
}

