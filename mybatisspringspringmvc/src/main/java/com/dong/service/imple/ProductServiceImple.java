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
     * ��������������Ʒ
     *
     * @param id ����
     * @return Product����
     */
    @Override
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }
    /**
     * ����ҳ��Ų�ѯ��ѯ��ǰҳ����Ʒ�б�
     *
     * @param pageNo ��ǰҳ��
     * @return  ��ǰҳ�����Ʒ�б�
     */
    @Override
    public PageInfo<Product> findAllProductByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,10);
        List<Product> productList = productMapper.findAllWithType();

        return new PageInfo<>(productList);

    }

    /**
     * ��ѯ���е���Ʒ����
     * @return
     */
    @Override
    public List<ProductType> findAllProductType() {
        return productMapper.findAllProductType();
    }
    /**
     * ������Ʒ�����ݿ���
     *
     * @param product
     */
    @Override
    public void saveProduct(Product product) {
        product.setCommentNum(product.DEFAULT_COMMENT_NUM);
        productMapper.save(product);

    }
    /**
     * �����޸���Ʒ
     * @param product
     */
    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    /**
     * ɾ����pin
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
     * ���ݵ�ǰҳ�źͲ�ѯ������ѯ��Ʒ����
     *
     * @param pageNo        ��ǰҳ��
     * @param queryParamMap ��ѯ������map
     * @return ��ǰҳ����
     */
    @Override
    public PageInfo<Product> findAllProductByPageNoAndQueryParam(Integer pageNo, Map<String, Object> queryParamMap) {
        PageHelper.startPage(pageNo,8);
        List<Product> productList = productMapper.findAllWithTypeByQueryParam(queryParamMap);
        return new PageInfo<>(productList);
    }
}
