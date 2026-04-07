package org.example.hjycommunity.common.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.hjycommunity.common.constant.HttpStatus;
import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.example.hjycommunity.common.core.page.PageDomain;
import org.example.hjycommunity.common.core.page.PageResult;
import org.example.hjycommunity.common.utils.ServletUtils;

import java.util.List;

/**
 * BaseController
 * 基础控制器类
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 20:16
 */

public class BaseController {

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";
    /**
     * 每页显示的记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 封装分页数据
     */
    private PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();

        // 安全获取 pageNum
        Integer pageNum = ServletUtils.getParameterToInt(PAGE_NUM);
        if (pageNum != null) {
            pageDomain.setPageNum(pageNum);
        }

        // 安全获取 pageSize
        Integer pageSize = ServletUtils.getParameterToInt(PAGE_SIZE);
        if (pageSize != null) {
            pageDomain.setPageSize(pageSize);
        }

        return pageDomain;
    }

    /**
     * 智能分页：只有传入 pageNum 和 pageSize 才开启分页
     * 不传参数 -> 导出全部数据
     * 传参数 -> 导出当前页
     */
    protected void startPage() {
        PageDomain pageDomain = getPageDomain();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();

        // 两个都不为 null 才分页，否则不分页（导出全部）
        if (pageNum != null && pageSize != null && pageNum > 0 && pageSize > 0) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }

    /**
     * 响应分页数据
     *
     * @param list
     * @return
     */
    protected PageResult getPageResult(List<?> list) {
        PageResult pageResult = new PageResult();
        pageResult.setCode(HttpStatus.SUCCESS);
        pageResult.setMsg("分页查询成功");
        pageResult.setRows(list);
        pageResult.setTotal(new PageInfo<>(list).getTotal());
        return pageResult;
    }

    /**
     * 响应返回结果 ( 针对增删改操作 )
     *
     * @param rows 受影响的行数
     * @return
     */
    protected <T> BaseResponse<T> toAjax(Integer rows) {
        return rows > 0 ? BaseResponse.success((T) rows) : BaseResponse.fail("操作失败");
    }
}
