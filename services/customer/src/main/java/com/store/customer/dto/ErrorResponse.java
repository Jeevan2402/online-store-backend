package com.store.customer.dto;

import java.util.Map;

public record ErrorResponse(
		Map<String, String> errors
		) {

}
