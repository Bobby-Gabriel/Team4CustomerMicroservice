package com.team4.project;

import java.util.Optional;

public interface CustomerService {

	Iterable<Customer> getCustomers();

	Optional<Customer> getCustomerById(long id);
	
	Customer addCustomer(Customer c);
	
	Customer getCustomerByName(String name);
	
	void updateCustomer(Customer c);

	void deleteCustomerById(long id);

}

