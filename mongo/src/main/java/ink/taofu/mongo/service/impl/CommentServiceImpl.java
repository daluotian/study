package ink.taofu.mongo.service.impl;

import ink.taofu.mongo.dao.CommentRepository;
import ink.taofu.mongo.entity.Comment;
import ink.taofu.mongo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    
    /**
     * 保存一条
     * @param comment
     */
    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 更新一条
     * @param comment
     */
    @Override
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 删除一条
     * @param comment
     */
    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    /**
     * 获取所有
     * @return
     */
    @Override
    public List<Comment> findCommentList() {
        return commentRepository.findAll();
    }

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    @Override
    public Comment findCommentById(String id) {
        return commentRepository.findById(id).get();
    }

    /**
     * 根据非主键查询例子
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Comment> findCommentByParentid(String parentid, int page, int size) {
        return commentRepository.findByParentid(parentid, PageRequest.of(page,size));
    }

    /**
     * 更新点赞数，使用mongoTemplate
     */
    public void updateCommentLikenum (String id) {
        //查询条件
        Query query = Query.query(Criteria.where("_id").is(id));
        //更新条件
        Update likenum = new Update().inc("likenum", 1);
        mongoTemplate.updateFirst(query, likenum, Comment.class);
    }
}
