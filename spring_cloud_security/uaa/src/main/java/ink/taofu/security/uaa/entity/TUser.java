package ink.taofu.security.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class TUser {
    @TableId
    private String id;
    
    private String username;
    
    private String password;
    
    private String fullname;
    
    private String mobile;
}
