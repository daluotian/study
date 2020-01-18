package cn.idealismus.product.service;


import cn.idealismus.product.entity.Product;

public interface ProductService {
    /**
     * 根据ID查询
     */
    Product getProductById(Integer id);

    /**
     * 保存
     */
    void save(Product product);
    
    /**
     * 更新
     */
    void update(Product product);
    
    /**
     * 删除
     */
    void delete(Integer id);
}
