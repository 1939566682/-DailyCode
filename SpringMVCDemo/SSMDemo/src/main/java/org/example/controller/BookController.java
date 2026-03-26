package org.example.controller;

import org.example.pojo.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-26 20:09
 */

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/findAllBooks")
    @ResponseBody
    public List<Book> findAll() {
        return bookService.findAll();
    }
}