package cn.idealismus.mybatis_plus_mysql.controller;

import cn.idealismus.novel.service.IBook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BookController {
    @Resource
    private IBook bookService;
    
    @RequestMapping("/start")
    @ResponseBody
    public void start () throws IOException {
        Connection connection = Jsoup.connect("https://www.bqg5200.com/xiaoshuo/50/50374/");
        Map<String,String> headers = new HashMap<>();
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        headers.put("Accept-Encoding","gzip, deflate, br");
        headers.put("Accept-Language","zh-CN,zh;q=0.9");
        headers.put("Connection","keep-alive");
        headers.put("Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        connection.headers(headers);
        Document doc = connection.get();
        
        Elements lis = doc.select("div#readerlist>ul>li");
        for (Element li : lis) {
            System.out.println(li.getElementsByTag("a").get(0).attr("href"));
            
        }
        lis.get(lis.size() - 1);
    }
}
