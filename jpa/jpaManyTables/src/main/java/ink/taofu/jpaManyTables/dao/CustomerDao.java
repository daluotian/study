package ink.taofu.jpaManyTables.dao;


import ink.taofu.jpaManyTables.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 符合 spring data jpa规范的 dao 接口
 *  JpaRepository<实体类, 主键类型>
 *  JpaSpecificationExecutor<实体类类型>
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    
}
