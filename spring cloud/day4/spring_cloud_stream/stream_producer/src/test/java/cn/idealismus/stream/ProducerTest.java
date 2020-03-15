package cn.idealismus.stream;

import cn.idealusmus.stream.ProducerApplication;
import cn.idealusmus.stream.producer.MessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class ProducerTest {
    @Autowired
    private MessageSender messageSender;
    
    @Test
    public void testSend () {
        messageSender.Send("hello 自定义通道");
    }
}
