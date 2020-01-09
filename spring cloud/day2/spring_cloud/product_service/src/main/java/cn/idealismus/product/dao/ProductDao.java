package cn.idealismus.product.dao;

import cn.idealismus.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 接口继承
 */

public interface ProductDao extends JpaRepository<Product,Integer> , JpaSpecificationExecutor<Product> {
    
    
}
