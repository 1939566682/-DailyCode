package org.example.service;

import org.example.util.PageResult;
import org.example.vo.SmsTempVO;

/**
 * SmsTempService - 短信模板Service
 * TODO: 需确认数据库 sms_temp 表是否已创建
 */
public interface SmsTempService {

    /**
     * 分页查询短信模板列表
     * @param offset 偏移量
     * @param limit 每页数量
     * @param search 搜索关键字
     * @return 分页结果
     */
    PageResult<SmsTempVO> list(int offset, int limit, String search);

    /**
     * 批量删除短信模板
     * @param ids 模板ID数组
     */
    void delete(Long[] ids);

    /**
     * 根据ID查询短信模板详情
     * @param id 模板ID
     * @return 模板VO
     */
    SmsTempVO findById(Long id);

    /**
     * 新增短信模板
     * @param smsTempVO 模板VO
     */
    void save(SmsTempVO smsTempVO);

    /**
     * 更新短信模板
     * @param smsTempVO 模板VO
     */
    void update(SmsTempVO smsTempVO);
}
