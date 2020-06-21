package ink.taofu.jpaManyTables.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 实体类与表的映射关系，
 */
@Entity
@Table(name = "cst_customer")
public class Customer {
    /**
     * 主键
     * @Id: 声明这是该表的主键
     * @GeneratedValue: 主键策略
     *  GenerationType.IDENTITY 自增 mysql
     *  GenerationType.SEQUENCE 序列，oracle
     *  GenerationType.TABLE jpa提供的一种机制，通过一张数据库表的方式控制主键自增
     *  GenerationType.AUTO 程序自主控制选择主键生成策略
     * @Column: 配置属性和字段的关系
     *  name: 数据库中表的字段名称
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;
    /**
     * 客户名称
     */
    @Column(name = "cust_name")
    private String custName;
    /**
     * 客户来源
     */
    @Column(name = "cust_resource")
    private String custResource;
    /**
     * 客户所属行业
     */
    @Column(name = "cust_industry")
    private String custIndustry;
    /**
     * 客户级别
     */
    @Column(name = "cust_level")
    private String custLevel;
    /**
     * 客户地址
     */
    @Column(name = "cust_address")
    private String custAddress;
    /**
     * 客户电话
     */
    @Column(name = "cust_phone")
    private String custPhone;

    /**
     * 配置客户和联系人之前的关系(一对多)
     * 使用注解的方式，配置多表关系
     *  1.声明关系
     *      @OneToMany 配置一对多关系 targetEntity 对方对象的字节码
     *  2.配置外键(中间表)
     *      @JoinColumn
     *          name: 外键名称
     *          referencedColumnName: 参照主表的主键名称
     *  在客户实体类上添加了外键的配置，所以对于客户而言，也具备了维护外键的作用
     *  
     */
    //@OneToMany(targetEntity = LinkMan.class)
    //@JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")
    /**
     * 放弃外键维护权
     *  mappedBy: 对方配置关系的属性名称
     *  cascade: 配置级联关系
     *      CascadeType.ALL     :全部
     *      CascadeType.MERGE   :更新
     *      CascadeType.PERSIST :保存
     *      CascadeType.REMOVE  :删除
     *  fetch: 配置关联对象的加载方式
     *      FetchType.EAGER: 立即加载
     *      FetchType.LAZY: 延迟加载
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LinkMan> linkManSet = new HashSet<LinkMan>();

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustResource() {
        return custResource;
    }

    public void setCustResource(String custResource) {
        this.custResource = custResource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public Set<LinkMan> getLinkManSet() {
        return linkManSet;
    }

    public void setLinkManSet(Set<LinkMan> linkManSet) {
        this.linkManSet = linkManSet;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custResource='" + custResource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }
}
