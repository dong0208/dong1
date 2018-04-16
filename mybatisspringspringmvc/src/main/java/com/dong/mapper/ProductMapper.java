package com.dong.mapper;

import com.dong.entity.Product;
import com.dong.entity.ProductType;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    Product findById(Integer id);

    List<Product> findAllWithType();

    List<ProductType> findAllProductType();

    void save(Product product);

    void updateProduct(Product product);

    void delProductById(Integer id);


    List<Product> findAllWithTypeByQueryParam(Map<String, Object> queryParamMap);
}
