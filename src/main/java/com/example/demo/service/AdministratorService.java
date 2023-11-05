package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Administrator;

@Service
public interface AdministratorService {
	boolean validateAdministrator(Administrator administrator);
}