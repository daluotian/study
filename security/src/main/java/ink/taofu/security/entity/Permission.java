package ink.taofu.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_permission")
public class Permission {
    @TableId
    private String id;
    
    private String code;
    
    private String description;
    
    private String url;
}
