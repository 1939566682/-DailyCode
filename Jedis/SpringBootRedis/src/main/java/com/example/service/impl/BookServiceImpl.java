package com.example.service.impl;

import com.example.mapper.BookMapper;
import com.example.pojo.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-31 20:17
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.selectAll();
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    public Book findById(Integer id) {
        return bookMapper.selectById(id);
    }

    @Override
    @CachePut(value = "books", key = "#result.id")
    public Book addBook(Book book) {
        bookMapper.insert(book);
        return book;  // 返回包含自增id的完整对象
    }

    @Override
    @CachePut(value = "books", key = "#book.id")
    public Book updateBook(Book book) {
        bookMapper.update(book);
        return book;
    }

    @Override
    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Integer id) {
        bookMapper.deleteById(id);
    }
}