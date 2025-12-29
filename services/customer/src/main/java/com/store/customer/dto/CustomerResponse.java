package com.store.customer.dto;

import com.store.customer.entites.Address;

public record CustomerResponse (
		String id,
		String firstName,
		String lastName,
		String email,
		Address address
		){

}
