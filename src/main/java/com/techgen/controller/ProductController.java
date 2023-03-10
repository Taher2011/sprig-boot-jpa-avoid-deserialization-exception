package com.techgen.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.techgen.entity.Customer;
import com.techgen.entity.Product;
import com.techgen.service.ProductService;

@RequestMapping("/product")
@RestController
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/{product-id}")
	public ResponseEntity<Product> getProductById(@PathVariable(name = "product-id") Long productId) {

		Product product = productService.getProducts(productId);

		try {
			ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);

			/*
			   To avoid deserializationException while returning Product entity for OneToMany relationship we may use 3 approaches
			   1) set Set<Customer> customers object to null at below line 45
			   2) we may use @JsonIgnore property at customers field in Product entity
			   3) we may use @JsonManagedReference at customers field in Product entity and @JsonBackReference property at product field in Customer entity
			 */

			// responseEntity.getBody().setCustomers(null);

			return responseEntity;

		} catch (Exception e) {
			return null;
		}

	}

	@GetMapping("/customer/{customer-id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(name = "customer-id") Long customerId) {

		Customer customer = productService.getCustomer(customerId);

		try {
			ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);

			/*
			  To avoid deserializationException while returning Customer entity for ManyToOne relationship we may use 3 approaches
              1) set Product product object to null at below line 70
			  2) we may use either @JsonIgnore property at product field in Customer entity
			  3) we may use @JsonManagedReference at customers field in Product entity and @JsonBackReference property at product field in Customer entity
			 */

			// responseEntity.getBody().setProduct(null);

			return responseEntity;

		} catch (Exception e) {
			return null;
		}

	}

}
