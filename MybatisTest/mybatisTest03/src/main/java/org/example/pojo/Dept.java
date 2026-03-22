package org.example.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-21 13:16
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Dept implements Serializable {
    private Integer deptno;
    private String dname;
    private String loc;
    private List<Emp> empList;
}
