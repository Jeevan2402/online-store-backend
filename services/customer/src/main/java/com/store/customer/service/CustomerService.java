package com.store.customer.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.customer.dto.CustomerRequest;
import com.store.customer.dto.CustomerResponse;
import com.store.customer.entites.Customer;
import com.store.customer.exception.CustomerNotFoundException;
import com.store.customer.mapper.CustomerMapper;
import com.store.customer.repo.CustomerRepo;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CustomerMapper customerMapper;

	public String createCustomer(CustomerRequest customerRequest) {
		Customer save = this.customerRepo.save(customerMapper.toEntity(customerRequest));
		return save.getId();
	}

	public void updateCustomer(String id, CustomerRequest customerRequest) {
		Customer CustomerDb = this.customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(
				String.format("Cannot update Customer:: No customer found for ID:: %s", id)));
		mergeCustomer(CustomerDb, customerRequest);
		this.customerRepo.save(CustomerDb);
	}

	private void mergeCustomer(Customer customerDb, CustomerRequest customerRequest) {
		customerDb.setFirstName(customerRequest.firstName());
		customerDb.setLastName(customerRequest.lastName());
		customerDb.setEmail(customerRequest.email());
		customerDb.setAddress(customerRequest.address());

	}

	public List<CustomerResponse> findAllCustomers() {
		return this.customerRepo.findAll().stream().map(customerMapper::toCustomerResponse)
				.collect(Collectors.toList());
	}

	public boolean customerExists(String id) {
		return this.customerRepo.findById(id).isPresent();
	}

	public CustomerResponse getCustomerById(String id) {
		Customer custumerDb = this.customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(
				String.format("Cannot update Customer:: No customer found for ID:: %s", id)));
		return this.customerMapper.toCustomerResponse(custumerDb);
	}

	public void deleteCustomer(String id) {
		
		this.customerRepo.deleteById(id);
	}

}
