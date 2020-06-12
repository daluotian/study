package ink.taofu.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

//可以省略，如果省略，则默认映射到类名小写的集合
@Document(collection = "comment")
@Data
//符合索引
//@CompoundIndex(def = "{userid:1,nickname:-1}")
public class Comment implements Serializable {
    //主键
    @Id
    private String id;
    /**
     * 评论内容
     */
    //如果mongodb中属性字段与entity中字段不一致，使用该注解映射
    @Field("content")
    private String content;
    /**
     * 发布时间
     */
    private Date publishtime;
    /**
     * 添加了一个单字段的索引
     */
    @Indexed
    private String userid;
    /**
     * 昵称
     */
    private String nickname;
    /**
     *评论发布时间
     */
    private LocalDateTime createtime;
    /**
     *点赞数
     */
    private Integer likenum;
    /**
     *回复数
     */
    private Integer replynum;
    /**
     *状态
     */
    private String state;
    /**
     *上级ID
     */
    private String parentid;
    /**
     *对应的文章ID
     */
    private String articleid;
}
