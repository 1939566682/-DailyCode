package org.example.hjycommunity.system.service.impl;

import org.example.hjycommunity.system.domain.dto.SysAreaDTO;
import org.example.hjycommunity.system.domain.pojo.SysArea;
import org.example.hjycommunity.system.mapper.SysAreaMapper;
import org.example.hjycommunity.system.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SysAreaServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 21:46
 */

@Service
public class SysAreaServiceImpl implements SysAreaService {

    @Autowired
    private SysAreaMapper sysAreaMapper;


    /**
     * 获取区域数据完整树
     * @return
     */
    @Override
    public List<SysAreaDTO> findAreaAsTree() {
        List<SysArea> list = sysAreaMapper.findAll();

        return list.stream()
                .filter(area -> area.getParentId().equals(0))
                .map(area -> {
                    SysAreaDTO sysAreaDTO = new SysAreaDTO();
                    sysAreaDTO.setCode(area.getCode());
                    sysAreaDTO.setName(area.getName());
                    sysAreaDTO.setChildren(getChildrenArea(sysAreaDTO, list));
                    return sysAreaDTO;
                })
                .collect(Collectors.toList());
    }

    /**
     * 递归设置区域信息
     * @param sysAreaDTO 上级区域信息
     * @param list       所有区域信息
     * @return
     */
    private List<SysAreaDTO> getChildrenArea(SysAreaDTO sysAreaDTO, List<SysArea> list) {

        List<SysArea> subAreaList = list.stream()
                .filter(area -> area.getParentId().equals(sysAreaDTO.getCode()))
                .collect(Collectors.toList());

        if (subAreaList.isEmpty()) {
            return List.of();
        }

        return subAreaList.stream()
                .map(area -> {
                    SysAreaDTO subAreaDTO = new SysAreaDTO();
                    subAreaDTO.setCode(area.getCode());
                    subAreaDTO.setName(area.getName());
                    subAreaDTO.setChildren(getChildrenArea(subAreaDTO, list));
                    return subAreaDTO;
                }).collect(Collectors.toList());
    }
}
