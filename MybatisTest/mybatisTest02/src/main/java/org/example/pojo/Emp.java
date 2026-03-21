package org.example.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-21 15:28
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Emp {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;
}
