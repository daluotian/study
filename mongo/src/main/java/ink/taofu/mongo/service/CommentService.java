package ink.taofu.mongo.service;

import ink.taofu.mongo.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
    
    void saveComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(Comment comment);

    List<Comment> findCommentList();
    
    Comment findCommentById(String id);
    
    Page<Comment> findCommentByParentid(String parentid, int page, int size);

    void updateCommentLikenum (String id);
}
