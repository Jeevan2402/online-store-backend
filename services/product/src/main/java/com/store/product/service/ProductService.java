package com.store.product.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.product.dto.ProductPurchaseRequest;
import com.store.product.dto.ProductPurchaseResponse;
import com.store.product.dto.ProductRequset;
import com.store.product.dto.ProductResponse;
import com.store.product.entity.Product;
import com.store.product.exception.ProductPurchaseException;
import com.store.product.mapper.ProductMapper;
import com.store.product.reposiotry.ProductRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepo productRepo;
	
	private final ProductMapper productMapper;
	
	public Integer createProduct(ProductRequset producrReq) {
		Product product = productMapper.toProduct(producrReq);
		return productRepo.save(product).getId();
	}

	 @Transactional(rollbackFor = ProductPurchaseException.class)
	public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> purchaseReq) {
		 var productIds = purchaseReq
	                .stream()
	                .map(ProductPurchaseRequest::productId)
	                .toList();
	        var storedProducts = productRepo.findAllByIdInOrderById(productIds);
	        if (productIds.size() != storedProducts.size()) {
	            throw new ProductPurchaseException("One or more products does not exist");
	        }
	        var sortedRequest = purchaseReq
	                .stream()
	                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
	                .toList();
	        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();
	        for (int i = 0; i < storedProducts.size(); i++) {
	            var product = storedProducts.get(i);
	            var productRequest = sortedRequest.get(i);
	            if (product.getAvailableQuantity() < productRequest.quantity()) {
	                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
	            }
	            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
	            product.setAvailableQuantity(newAvailableQuantity);
	            productRepo.save(product);
	            purchasedProducts.add(productMapper.toproductPurchaseResponse(product, productRequest.quantity()));
	        }
	        return purchasedProducts;
	}

	public ProductResponse findById(Integer productId) {
		return productRepo.findById(productId).map(productMapper::toProductResponse)
				.orElseThrow(() -> new EntityNotFoundException("Product not found with the ID:: " + productId));
	}

	public List<ProductResponse> findAll() {
		return productRepo.findAll().stream().map(productMapper::toProductResponse).collect(Collectors.toList());
	}

}
