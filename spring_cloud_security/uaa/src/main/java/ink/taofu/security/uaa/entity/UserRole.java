package ink.taofu.security.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user_role")
public class UserRole {
    private String userId;
    
    private String roleId;
    
    private Date createTime;
    
    private String creator;
}
