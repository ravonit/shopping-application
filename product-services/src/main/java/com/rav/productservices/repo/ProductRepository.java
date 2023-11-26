package com.rav.productservices.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.rav.productservices.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
