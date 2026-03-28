package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-28 18:56
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    // 字段名必须与数据库表完全一致
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private String email;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
}