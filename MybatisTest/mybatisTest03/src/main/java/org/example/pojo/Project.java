package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-22 18:24
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Project implements Serializable {
    private Integer pid;
    private String pname;
    private Integer money;

    // 组合一个ProjectRecord对象集合作为属性
    private List<ProjectRecord> projectRecords;

}