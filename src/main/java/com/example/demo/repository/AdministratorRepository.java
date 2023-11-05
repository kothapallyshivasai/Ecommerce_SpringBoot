package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, String>{

}
