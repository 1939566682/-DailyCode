package org.example.hjycommunity.common.core.page;

import lombok.Data;

/**
 * PageDomain
 * 分页封装参数
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 19:27
 */

@Data
public class PageDomain {

    /**
     * 当前记录起始索引
     */
    private  int pageNum;
    /**
     * 每页显示的记录数
     */
    private int pageSize;
}
