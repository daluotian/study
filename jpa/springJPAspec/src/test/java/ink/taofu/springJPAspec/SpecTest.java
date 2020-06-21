package ink.taofu.springJPAspec;

import ink.taofu.springJPAspec.dao.CustomerDao;
import ink.taofu.springJPAspec.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据条件查询单个对象
     */
    @Test
    public void testSpec() {
        /**
         * 自定义查询条件
         *  实现 Specification 接口
         *  实现接口中 toPredicate 方法
         *  借助方法中 Root: 获取需要查询的对象属性
         *            CriteriaBuilder: 构造查询条件
         * 根据客户名查询，查询客户名为 taofu1 的
         *      查询条件: 
         *          1. 查询方式--CriteriaBuilder对象
         *          2. 比较的属性名称--root对象
         */
        /*Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取比较属性
                Path<Object> custName = root.get("custName");
                //构造查询条件 equal(比较的属性,比较的属性取值)
                Predicate predicate = criteriaBuilder.equal(custName, "taofu1");
                return predicate;
            }
        };*/
        Specification<Customer> spec = (root, criteriaQuery, criteriaBuilder) -> {
            //获取比较属性
            Path<Object> custName = root.get("custName");
            //构造查询条件 equal(比较的属性,比较的属性取值)
            Predicate predicate = criteriaBuilder.equal(custName, "taofu1");
            return predicate;
        };
        List<Customer> customers = customerDao.findAll(spec);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * 多条件查询
     */
    @Test
    public void specTest() {
        Specification<Customer> spec = (root, criteriaQuery, criteriaBuilder) -> {
            //获取比较属性
            Path<Object> custName = root.get("custName");
            Path<Object> custIndustry = root.get("custIndustry");
            //构造查询条件 equal(比较的属性,比较的属性取值)
            Predicate p1 = criteriaBuilder.equal(custName, "taofu1");
            Predicate p2 = criteriaBuilder.equal(custIndustry, "myths");
            //将多个查询条件合并组合: 与 或
            Predicate p3 = criteriaBuilder.and(p1, p2);
            //Predicate p4 = criteriaBuilder.or(p1, p2);
            return p3;
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 模糊查询
     * equal: 得到 path 方法能直接比较
     * gt,lt,ge,le,like: 得到 path 对象，制定比较数据类型，再进行比较
     *  path.as(属性类型.class);
     */
    @Test
    public void testSpec1() {
        Specification<Customer> spec = (root, criteriaQuery, criteriaBuilder) -> {
            Path<Object> custName = root.get("custName");
            Expression<String> as = custName.as(String.class);
            Predicate predicate = criteriaBuilder.like(as, "taofu%");
            return predicate;
        };
        List<Customer> customers = customerDao.findAll(spec);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * 测试排序
     */
    @Test
    public void testSpec2() {
        Specification<Customer> spec = (root, criteriaQuery, criteriaBuilder) -> {
            Path<Object> custName = root.get("custName");
            Expression<String> as = custName.as(String.class);
            Predicate predicate = criteriaBuilder.like(as, "taofu%");
            return predicate;
        };
        /**
         * 添加排序
         *  构建排序对象
         *      参数一 排序的顺序(顺序 倒序)
         *      参数二 排序的属性名称
         */
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> customers = customerDao.findAll(spec, sort);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * 分页查询
     * 
     *  findAll(Specification, Pageable): 根据条件分页
     *  findAll(Pageable): 无条件分页
     *  返回: Page对象 (spring data jpa封装好的对象，包含数据列表，总条数)
     */
    @Test
    public void testSpec3(){
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        Pageable pageable = new PageRequest(0, 2, sort);
        //Pageable pageable = new PageRequest(0, 2);
        Page<Customer> page = customerDao.findAll(null, pageable);
        System.out.println("数据总条数:" + page.getTotalElements());
        System.out.println("数据总页数:" + page.getTotalPages());
        //遍历数据集合
        for (Customer customer : page.getContent()) {
            System.out.println(customer);
        }
    }
}
