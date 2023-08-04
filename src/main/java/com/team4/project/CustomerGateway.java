package com.team4.project;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;


@CrossOrigin
@RestController
@RequestMapping("/gateway/customers")
public class CustomerGateway {

	static final String JSON = "application/json";

	@Autowired
	CustomerService customerService;
	
	// GET all customers in the list
	@GetMapping
	public Iterable<Customer> getAllCustomers(HttpServletResponse response) {
		
		response.setStatus(HttpServletResponse.SC_OK);
		return customerService.getCustomers();
	}
	
	
	// GET a customer by their place in the list
	@GetMapping("/{id}")
	public Optional<Customer> getOneSingleCustomer(@PathVariable String id, HttpServletResponse response) {
		
		response.setStatus(HttpServletResponse.SC_OK);
		return customerService.getCustomerById(id);
		
	}
	
	
	// Not passing in any ID at the moment, will not work
	// POST a new customer to the list
	@PostMapping(consumes = JSON, produces = JSON)
	public ResponseEntity<?> addCustomer(@RequestBody Customer c, HttpServletResponse response, UriComponentsBuilder uri) {
		customerService.addCustomer(c);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId()).toUri();
		ResponseEntity<?> responseEntity = ResponseEntity.created(location).build();
		
		return responseEntity;
		
	}
	
	
	
	// GET a customer by their name
	@GetMapping("/byname/{username}")
	public Customer getCustomerByName(@PathVariable String username, HttpServletResponse response) {
			
		response.setStatus(HttpServletResponse.SC_OK);
		return customerService.getCustomerByName(username);
	
	}
	
	
	
	@PostMapping(path = "/byname")
	@ResponseBody
	public Customer getCustomerByNamePost(@RequestBody() String customerName, HttpServletResponse response){
	  
		return getCustomerByName(customerName, response);
	}
	
	
	
	@PutMapping("/{customerId}")
	public ResponseEntity<?> putCustomer(@RequestBody Customer newCustomer, @PathVariable String customerId) {
		if (!(newCustomer.getId().equals(customerId))) {
			return ResponseEntity.badRequest().build();
		}
		customerService.updateCustomer(newCustomer);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCustomer.getId()).toUri();
		ResponseEntity<?> responseEntity = ResponseEntity.created(location).build();
		return responseEntity;
	}
	

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable String id){
	    
		customerService.deleteCustomerById(id);
		return ResponseEntity.ok().build();
	}
	
	

}
