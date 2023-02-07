package com.techgen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techgen.entity.Customer;
import com.techgen.entity.Product;
import com.techgen.repository.CustomerRepository;
import com.techgen.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	private CustomerRepository customerRepository;

	public ProductService(ProductRepository productRepository, CustomerRepository customerRepository) {
		super();
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
	}

	public Product getProducts(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		return product.get();
	}

	public Customer getCustomer(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		return customer.get();
	}

}
