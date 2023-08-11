package com.team4.project;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MockCustomerService implements CustomerService {

	static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	static int idx;

	static {  
		Customer c1 = new Customer("Wayne","99@hotmail.com", "gr3at1");
		c1.setId(0);
		
		Customer c2 = new Customer("admin" ,"66@gmail.com", "admin");
		c2.setId(1);
		
		Customer c3 = new Customer("Mario","88@aol.com","p3ngu1ns");
		c3.setId(2);
		
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		
		idx = 3;
	}
	
	@Override
	public Iterable<Customer> getCustomers() {
		return customers;
	}

	@Override
	public Optional<Customer> getCustomerById(long id) {
		for (Customer customer : customers) {
			if (customer.getId() == id) {
				return Optional.of(customer);
			}
		}
		return Optional.empty();
	}

	@Override
	public void deleteCustomerById(long id) {
		for (Customer customer : customers) {
			if (customer.getId() == id) {
				customers.remove(customer);
			}
		}
		
	}

	@Override
	public Customer addCustomer(Customer c) {
		c.setId((long)(idx));
		idx++;
		customers.add(c);
		return c;
		
	}

	@Override
	public Customer getCustomerByName(String name) {
	    for (Customer c : customers) {
	    	if (name.equals(c.getName())){
	    		return c;
	    	}	        
	    }
	    return null;

	}

	@Override
	public void updateCustomer(Customer c) {
		
		for (int i = 0; i < customers.size(); i++) {
			Customer customer = customers.get(i);
			if (customer.getId() == c.getId()) {
				customers.set(i, c);
			}
		}
		
	}

}