package cn.idealismus.mybatis_plus_mysql.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("image")
public class Image {
    @TableId
    private Integer id;
    //类型 1为书的封面 2可能为用户的默认头像之类的
    private Integer type;
    //对应的url路径 可能之后这些东西会上传到阿里云数据库或者七牛云
    private String url;
}
