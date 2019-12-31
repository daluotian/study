package cn.idealismus.mybatis_plus_mysql.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("content")
public class Content {
    @TableId
    private Integer id;
    //对应的章节ID
    private Integer chapter_id;
    //内容
    private String content;
    //创建时间
    private Date create_time;
}
