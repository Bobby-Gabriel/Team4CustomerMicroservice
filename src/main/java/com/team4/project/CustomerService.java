package com.team4.project;

import java.util.Optional;

public interface CustomerService {

	Iterable<Customer> getCustomers();

	Optional<Customer> getCustomerById(String id);
	
	void addCustomer(Customer c);
	
	Customer getCustomerByName(String name);
	
	void updateCustomer(Customer c);

	void deleteCustomerById(String id);

}

