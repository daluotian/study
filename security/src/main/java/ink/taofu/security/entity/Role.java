package ink.taofu.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_role")
public class Role {
    @TableId
    private String id;
    
    private String roleName;
    
    private String description;
    
    private Date createTime;
    
    private Date updateTime;
    
    private String status;
}
