package com.example.service;


import com.example.pojo.Book;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-31 20:17
 */

public interface BookService {
    List<Book> findAll();
    Book findById(Integer id);
    Book addBook(Book book);
    Book updateBook(Book book);
    void deleteBook(Integer id);
}