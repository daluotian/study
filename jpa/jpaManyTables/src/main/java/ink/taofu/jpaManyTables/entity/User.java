package ink.taofu.jpaManyTables.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "age")
    private Integer age;

    /**
     * 配置用户与角色之间的多对多关系
     *  配置多对多关系
     *      1.声明表关系
     *          @ManyToMany 多对多
     *          targetEntity 对方实体类的字节码
     *      2.配置中间表(包含两个外键)
     *      @JoinTable name指的是中间表的名称
     *          joinColumns 当前对象在中间中的外键
     *              name: 外键名
     *              referencedColumnName: 参照主表的字段名
     *          inverseJoinColumns 对方对象在中间表的外键
     */
    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_user_role", 
            //当前对象在中间中的外键
            joinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")},
            //对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
