package com.example.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-31 20:25
 */

@Data
public class Book implements Serializable {
    private Integer id;
    private String name;
    private String author;
    private Double price;
}