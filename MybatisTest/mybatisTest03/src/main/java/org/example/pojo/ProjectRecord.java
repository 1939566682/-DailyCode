package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-22 18:25
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRecord implements Serializable {
    private Integer empno;
    private Integer pid;

    // 组合一个Emp对象作为属性
    private Emp emp;
}