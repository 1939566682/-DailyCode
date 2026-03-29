package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-29 18:27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String uname;
    private Integer age;
    private Clazz clazz;

    public Student(String uname, Integer age) {
        this.uname = uname;
        this.age = age;
    }
}
