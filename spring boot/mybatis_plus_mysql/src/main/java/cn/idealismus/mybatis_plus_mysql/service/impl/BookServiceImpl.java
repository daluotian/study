package cn.idealismus.mybatis_plus_mysql.service.impl;

import cn.idealismus.mybatis_plus_mysql.dao.BookMapper;
import cn.idealismus.mybatis_plus_mysql.entity.Book;
import cn.idealismus.mybatis_plus_mysql.service.IBook;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBook {
    
    
}
