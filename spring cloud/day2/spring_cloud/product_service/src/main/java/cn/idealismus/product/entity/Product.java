package cn.idealismus.product.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 商品实体类
 */

@Entity
@Table(name = "tb_product")
@Data
public class Product {
    //id
    @Id
    private Integer id;
    //商品名
    private String productName;
    //状态
    private Integer status;
    //价格
    private BigDecimal price;
    //商品描述
    private String productDocs;
    //标题
    private String caption;
    //库存
    private Integer inventory;
}
