package org.example.hjycommunity.system.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 区域表(SysArea)实体类
 *
 * @author Yang QingBo
 * @since 2026-04-06 21:27:47
 */
@Setter
@Getter
@TableName("sys_area")
public class SysArea implements Serializable {
    @Serial
    private static final long serialVersionUID = 264069274408151200L;
    /**
     * 城市编码
     */
    private Integer code;
    /**
     * 城市名称
     */
    private String name;
    /**
     * 城市父ID
     */
    private Integer parentId;


}

