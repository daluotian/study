package cn.idealismus.mybatis_plus_mysql.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("book")
public class Book {
    
    @TableId
    private Integer id;
    
    //笔趣阁的ID
    private String biqugeId;
    //书名
    private String bookName;
    //书的封面图片对应IMAGE表的封面
    private Integer bookCover;
    //作者名
    private String authorName;
    //书的类型
    private String typeName;
    //是否完结
    private Boolean isFinish;
    //书的创建时间
    private Date createTime;
}
