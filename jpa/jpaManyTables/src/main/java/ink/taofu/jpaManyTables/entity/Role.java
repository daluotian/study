package ink.taofu.jpaManyTables.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色与用户的多对多关系
     * 
     */
    /*
    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "sys_user_role",
            //当前对象在中间中的外键
            joinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")},
            //对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")}
    )
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
