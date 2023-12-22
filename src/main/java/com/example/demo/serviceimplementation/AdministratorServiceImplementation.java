package com.example.demo.serviceimplementation;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Administrator;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.service.AdministratorService;

@Service
public class AdministratorServiceImplementation implements AdministratorService{

	@Autowired
	AdministratorRepository administratorRepository;
	
	@Override
	public boolean validateAdministrator(Administrator administrator) {
	    Optional<Administrator> admin = administratorRepository.findById(administrator.getUserName());
	    Administrator adminValidation = null;
	    if(admin.isPresent()){
	        adminValidation = admin.get();
	        if(adminValidation != null && administrator.getPassword().equals(adminValidation.getPassword())){
	            return true;
	        }
	    }
	    return false;
	}
	
	@Override
	public Optional<Administrator> getUserById(String username) {
		return administratorRepository.findById(username);
	}
	
	@Override
	public void addUser(Administrator user) {
		administratorRepository.save(user);
	}
}