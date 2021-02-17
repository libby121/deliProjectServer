package com.example.login;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.example.exceptions.customerNotFoundException;
import com.example.exceptions.loginFailedException;
import com.example.facaeds.AdminFacade;
import com.example.facaeds.CustomerFacade;
import com.example.facaeds.Facade;

@Service
public class LoginManager {

	@Autowired
	ConfigurableApplicationContext ctx;

	public Facade login(String email, String password, ClientType client) throws loginFailedException, customerNotFoundException, BeansException {
		switch (client) {
		case Administrator:
			AdminFacade admin = ctx.getBean(AdminFacade.class);
			if (admin.login(email, password))
				return admin;
			break;
		case Customer:
			CustomerFacade customer = ctx.getBean(CustomerFacade.class);
			if (customer.login(email, password))
				return customer;
			break;

		}
		throw new loginFailedException();
	}

}
