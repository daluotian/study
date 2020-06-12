package ink.taofu.mongo.dao;

import ink.taofu.mongo.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

//抽象参数一个是实体类，另一个是主键的数据类型
public interface CommentRepository extends MongoRepository<Comment,String> {
    Page<Comment> findByParentid(String parentid, Pageable pageable);
}
