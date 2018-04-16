package com.dong.service;

import com.dong.entity.Product;
import com.dong.entity.ProductType;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ProductService {
    /**
     * ��������������Ʒ
     * @param id ����
     * @return
     */
    Product findById(Integer id);

    /**
     * ����ҳ��Ų�ѯ��ѯ��ǰҳ����Ʒ�б�
     *
     * @param pageNo ��ǰҳ��
     * @return  ��ǰҳ�����Ʒ�б�
     */
    PageInfo<Product> findAllProductByPageNo(Integer pageNo);

    /**
     * ��ѯ���е���Ʒ����
     * @return
     */
    List<ProductType> findAllProductType();
    /**
     * ������Ʒ�����ݿ���
     * @param product
     */
    void saveProduct(Product product);

    /**
     * �����޸���Ʒ
     * @param product
     */
    void updateProduct(Product product);

    /**
     * ɾ����Ʒ
     * @param id
     */
    void delProductById(Integer id);

    /**
     * ���ݵ�ǰҳ�źͲ�ѯ������ѯ��Ʒ����
     * @param pageNo ��ǰҳ��
     * @param queryParamMap ��ѯ������map
     * @return ��ǰҳ����
     */
    PageInfo<Product> findAllProductByPageNoAndQueryParam(Integer pageNo, Map<String, Object> queryParamMap);
}
