package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.facaeds.CustomerFacade;

@RestController
@RequestMapping("customer")
public class CustomerController {

	
	@Autowired
	private CustomerFacade custFac;
	
	
	
}
