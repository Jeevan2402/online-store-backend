package com.store.product.mapper;

import org.springframework.stereotype.Service;

import com.store.product.dto.ProductPurchaseResponse;
import com.store.product.dto.ProductRequset;
import com.store.product.dto.ProductResponse;
import com.store.product.entity.Category;
import com.store.product.entity.Product;

@Service
public class ProductMapper {

	public Product toProduct(ProductRequset productReq) {
		return Product.builder()
				.id(productReq.id())
				.name(productReq.name())
				.description(productReq.description())
				.price(productReq.price())
				.availableQuantity(productReq.availableQuantity())
				.category(Category.builder().id(productReq.categoryId()).build())
				.build();
	}
	
	public ProductResponse toProductResponse(Product product) {
		return new ProductResponse(
				product.getId(),
				product.getName(),
				product.getDescription(),
				product.getAvailableQuantity(),
				product.getPrice(),
				product.getCategory().getId(),
				product.getCategory().getName(),
				product.getCategory().getDescription()
				);
	}

	public ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {

		return new ProductPurchaseResponse(product.getId(), product.getName(), product.getDescription(),
				product.getPrice(), quantity);
	}

}
