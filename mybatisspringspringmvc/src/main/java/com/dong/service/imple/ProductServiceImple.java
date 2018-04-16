package com.dong.service.imple;

import com.dong.entity.Product;
import com.dong.entity.ProductType;
import com.dong.mapper.ProductMapper;
import com.dong.mapper.ProductTypeMapper;
import com.dong.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImple implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductTypeMapper productTypeMapper;
    /**
     * 根据主键查找商品
     *
     * @param id 主键
     * @return Product对象
     */
    @Override
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }
    /**
     * 根据页码号查询查询当前页的商品列表
     *
     * @param pageNo 当前页码
     * @return  当前页码的商品列表
     */
    @Override
    public PageInfo<Product> findAllProductByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,10);
        List<Product> productList = productMapper.findAllWithType();

        return new PageInfo<>(productList);

    }

    /**
     * 查询所有的商品类型
     * @return
     */
    @Override
    public List<ProductType> findAllProductType() {
        return productMapper.findAllProductType();
    }
    /**
     * 保存商品到数据库中
     *
     * @param product
     */
    @Override
    public void saveProduct(Product product) {
        product.setCommentNum(product.DEFAULT_COMMENT_NUM);
        productMapper.save(product);

    }
    /**
     * 更新修改商品
     * @param product
     */
    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    /**
     * 删除上pin
     * @param id
     */
    @Override
    public void delProductById(Integer id) {
      Product product = findById(id);
      if(product != null){
          productMapper.delProductById(id);
      }
    }

    /**
     * 根据当前页号和查询参数查询商品集合
     *
     * @param pageNo        当前页号
     * @param queryParamMap 查询参数的map
     * @return 当前页对象
     */
    @Override
    public PageInfo<Product> findAllProductByPageNoAndQueryParam(Integer pageNo, Map<String, Object> queryParamMap) {
        PageHelper.startPage(pageNo,8);
        List<Product> productList = productMapper.findAllWithTypeByQueryParam(queryParamMap);
        return new PageInfo<>(productList);
    }
}
