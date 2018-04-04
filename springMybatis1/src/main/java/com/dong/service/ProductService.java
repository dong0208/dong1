package com.dong.service;

import com.dong.entity.Product;
import com.dong.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public Product findById(Integer id){
        return productMapper.findById(id);
    }
}
