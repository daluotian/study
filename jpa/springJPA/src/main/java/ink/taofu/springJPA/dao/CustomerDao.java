package ink.taofu.springJPA.dao;

import ink.taofu.springJPA.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 符合 spring data jpa规范的 dao 接口
 *  JpaRepository<实体类, 主键类型>
 *  JpaSpecificationExecutor<实体类类型>
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    /**
     * 根据客户名获取客户信息
     *  jpql: from Customer where custName = ?
     */
    @Query(value = "from Customer where custName = ?1")
    //public List<Customer> findJqpl(String custName);
    Customer findJqpl(String custName);
    
    /**
     * 多条件查询
     *  jpql: from Customer where custName = ? and custId = ?
     *  
     *  多个占位符时，默认赋值占位符的位置和方法参数一致
     */
    @Query(value = "from Customer where custName = ?1 and custId = ?2")
    Customer findByCustNameAndId(String name, Long id);

    /**
     * 多条件查询
     *  制定占位符位置，使用 (? + 位置索引的方式）
     */
    @Query(value = "from Customer where custName = ?2 and custId = ?1")
    Customer findByCustNameAndId(Long id, String name);

    /**
     * 使用jqpl 根据ID更新
     *  更新id为5 的 custIndustry 为 myth
     *  jqpl: update Customer set cust
     *  
     * @Query 这是个查询注解 需要注明这是一个更新操作
     * @Modifying 表示当前执行的方法是一个更新操作
     */
    @Query(value = "update Customer set custIndustry = ?2 where custId = ?1")
    @Modifying
    void updateCustomer(long custId, String cust);

    /**
     * 使用sql语句查询
     * 查询所有用户
     * sql select * from cst_customer
     * nativeQuery: 是否使用本地查询 true-sql false-jpql
     */
    //@Query(value = " select * from cst_customer", nativeQuery = true)
    @Query(value = "select * from cst_customer where cust_name like ?1", nativeQuery = true)
    List<Object[] >findsql(String name);

    /**
     * 方法名约定
     * findBy开头表示这是个查询语句
     *      对象属性首字母大写: 查询条件 默认等于
     *      --findByCustName: 根据客户名称查询
     *          模糊查询
     *              findBy + 属性名 + Like
     *          多条件查询
     *              findByCustNameLike + and|or + 属性名 +查询方式
     *              由于没有占位符，所以参数列表与方法的属性名需要一致
     * 在 spring data jpa 的运行阶段
     *      会根据方法名进行解析 
     */
    List<Customer> findByCustName(String custName);
    
    List<Customer> findByCustNameLike(String custName);
    
    Customer findByCustNameLikeAndCustIndustry(String custName, String industry);
}
