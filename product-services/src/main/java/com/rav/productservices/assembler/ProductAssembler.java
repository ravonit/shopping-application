package com.rav.productservices.assembler;

import com.rav.productservices.dto.ProductResponse;
import com.rav.productservices.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductAssembler {

    public ProductResponse assembleProductResponseDtoFromEntity(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }
}
