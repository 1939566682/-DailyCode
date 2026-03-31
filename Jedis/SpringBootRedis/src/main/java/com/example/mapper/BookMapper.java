package com.example.mapper;

import com.example.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-31 20:17
 */

@Mapper
public interface BookMapper {

    @Select("SELECT * FROM book WHERE id = #{id}")
    Book selectById(Integer id);

    @Select("SELECT * FROM book")
    List<Book> selectAll();

    @Insert("INSERT INTO book(name, author, price) VALUES(#{name}, #{author}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Book book);

    @Update("UPDATE book SET name=#{name}, author=#{author}, price=#{price} WHERE id=#{id}")
    int update(Book book);

    @Delete("DELETE FROM book WHERE id = #{id}")
    int deleteById(Integer id);
}