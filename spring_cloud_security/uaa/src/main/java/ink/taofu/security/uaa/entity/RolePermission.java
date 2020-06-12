package ink.taofu.security.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_role_permission")
public class RolePermission {
    private String roleId;
    
    private String PermissionId;
}
