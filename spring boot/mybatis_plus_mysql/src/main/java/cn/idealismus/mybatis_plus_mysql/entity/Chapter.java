package cn.idealismus.mybatis_plus_mysql.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("chapter")
public class Chapter {
    @TableId
    private Integer id;
    //在书ID
    private Integer bookId;
    //笔趣阁内的章节ID
    private String biqugeChapterId;
    //章节名
    private String chapterName;
    //创建时间
    private Date createTime;
}
