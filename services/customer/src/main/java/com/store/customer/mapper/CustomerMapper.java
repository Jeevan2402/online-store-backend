package com.store.customer.mapper;

import org.springframework.stereotype.Component;

import com.store.customer.dto.CustomerRequest;
import com.store.customer.dto.CustomerResponse;
import com.store.customer.entites.Customer;

@Component
public class CustomerMapper {

	public Customer toEntity(CustomerRequest customerRequest) {
		
		return Customer.builder()
				.id(customerRequest.id())
				.firstName(customerRequest.firstName())
				.lastName(customerRequest.lastName())
				.email(customerRequest.email())
				.address(customerRequest.address())
				.build();
	}

	public CustomerResponse toCustomerResponse(Customer customer) {
		return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(),
				customer.getEmail(), customer.getAddress());
	}

}
