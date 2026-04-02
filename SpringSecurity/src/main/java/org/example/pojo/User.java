package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yang QingBo
 * {@code @date} 2026-04-01 20:01
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // 对应数据库 BIGINT
    private Long id;

    // 用户名 varchar(16) not null
    private String username;

    // 密码 varchar(64) not null
    private String password;

    // 年龄 int
    private Integer age;

    // 性别 0->女 1->男
    private Integer gender;
}