package com.rav.productservices.service;

import com.rav.productservices.assembler.ProductAssembler;
import com.rav.productservices.dto.ProductRequestDto;
import com.rav.productservices.dto.ProductResponse;
import com.rav.productservices.entity.Product;
import com.rav.productservices.repo.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductAssembler productAssembler;

    public void createProduct(ProductRequestDto productRequestDto) {
        LOGGER.info("Entered createProduct-method of ProductService");
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        productRepository.save(product);
        LOGGER.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(product -> productAssembler.assembleProductResponseDtoFromEntity(product)).collect(Collectors.toList());
    }
}
