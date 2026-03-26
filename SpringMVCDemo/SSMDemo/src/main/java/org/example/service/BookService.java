package org.example.service;

import org.example.pojo.Book;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-26 20:08
 */

public interface BookService {
    public abstract List<Book> findAll();
}
