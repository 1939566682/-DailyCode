package org.example.mapper;

import org.example.pojo.Project;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-22 18:26
 */

public interface ProjectMapper {

    /**
     * 根据项目编号查询一个项目信息及参与该项目的所有员工信息
     * @param pid 项目编号
     * @return 所有信息封装的Project对象
     */
    Project findProjectJoinEmpsByPid(int pid);

}
