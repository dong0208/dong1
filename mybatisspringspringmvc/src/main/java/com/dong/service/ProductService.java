package com.dong.service;

import com.dong.entity.Product;
import com.dong.entity.ProductType;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ProductService {
    /**
     * 根据主键查找商品
     * @param id 主键
     * @return
     */
    Product findById(Integer id);

    /**
     * 根据页码号查询查询当前页的商品列表
     *
     * @param pageNo 当前页码
     * @return  当前页码的商品列表
     */
    PageInfo<Product> findAllProductByPageNo(Integer pageNo);

    /**
     * 查询所有的商品类型
     * @return
     */
    List<ProductType> findAllProductType();
    /**
     * 保存商品到数据库中
     * @param product
     */
    void saveProduct(Product product);

    /**
     * 更新修改商品
     * @param product
     */
    void updateProduct(Product product);

    /**
     * 删除商品
     * @param id
     */
    void delProductById(Integer id);

    /**
     * 根据当前页号和查询参数查询商品集合
     * @param pageNo 当前页号
     * @param queryParamMap 查询参数的map
     * @return 当前页对象
     */
    PageInfo<Product> findAllProductByPageNoAndQueryParam(Integer pageNo, Map<String, Object> queryParamMap);
}
