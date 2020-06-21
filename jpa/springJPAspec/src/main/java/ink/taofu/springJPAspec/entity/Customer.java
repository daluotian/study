package ink.taofu.springJPAspec.entity;

import javax.persistence.*;

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
