package ink.taofu.mongo.service.impl;

import ink.taofu.mongo.entity.Comment;
import ink.taofu.mongo.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;

    @Test
    public void testFindComment() {
        Comment comment = commentService.findCommentById("3");
        System.out.println(comment);
    }
    
    @Test
    public void testFindCommentByParentid() {
        Page<Comment> page = commentService.findCommentByParentid("3", 1, 2);
        System.out.println(page.getContent());
    }
    
    @Test
    public void testUpdateCommentLikenum() {
        commentService.updateCommentLikenum("1");
    }
}
