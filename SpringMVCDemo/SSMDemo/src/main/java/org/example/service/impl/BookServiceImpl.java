package org.example.service.impl;

import org.example.mapper.BookMapper;
import org.example.pojo.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-26 20:08
 */

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    public List<Book> findAll() {
        return bookMapper.selectAll();
    }
}
