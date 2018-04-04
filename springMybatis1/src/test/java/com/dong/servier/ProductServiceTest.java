package com.dong.servier;

import com.dong.Application;
import com.dong.entity.Product;
import com.dong.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@ContextConfiguration(locations = "classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Test
    public void findById(){
        Product product = productService.findById(2177);
        System.out.println(product);
    }
}
