package cn.idealismus.order.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品实体类
 */
@Data
public class Product {
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
