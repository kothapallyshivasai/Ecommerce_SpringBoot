package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Administrator;

@Service
public interface AdministratorService {
	boolean validateAdministrator(Administrator administrator);
	Optional<Administrator> getUserById(String username);
	void addUser(Administrator user);
}