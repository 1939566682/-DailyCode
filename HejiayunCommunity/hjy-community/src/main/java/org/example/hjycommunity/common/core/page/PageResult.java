package org.example.hjycommunity.common.core.page;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * PageResult
 * 分页查询统一响应封装
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 19:28
 */

@Data
public class PageResult implements Serializable {
    @Serial
    private static final long serialVersionUID = -335731068290342148L;
    /**
     * 消息状态码
     */
    private Integer code;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 列表数据
     */
    private List<?> rows;

    public PageResult() {
    }

    /**
     * 分页
     * @param total 总记录数
     * @param rows 列表数据
     */
    public PageResult(Long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }
}
