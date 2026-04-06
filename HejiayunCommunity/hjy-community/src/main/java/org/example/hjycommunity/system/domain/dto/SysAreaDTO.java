package org.example.hjycommunity.system.domain.dto;

import lombok.Data;
import org.example.hjycommunity.system.domain.pojo.SysArea;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * SysArea
 * 区域数据传输对象
 * @author Yang QingBo
 * {@code @date} 2026-04-06 21:31
 */

@Data
public class SysAreaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -295033221878957752L;

    /**
     * 城市编码
     */
    private Integer code;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 子区域
     */
    private List<SysAreaDTO> children;
}
