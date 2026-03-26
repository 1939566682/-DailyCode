package org.example.mapper;

import org.example.pojo.Book;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-26 20:04
 */

public interface BookMapper {
    public abstract List<Book> selectAll();
}