package org.example.hjycommunity.service;

import org.example.hjycommunity.entity.HjyCommunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 小区 (HjyCommunity)表服务接口
 *
 * @author luoyu
 * @since 2026-04-03 19:19:59
 */
public interface HjyCommunityService {

    /**
     * 通过ID查询单条数据
     *
     * @param communityId 主键
     * @return 实例对象
     */
    HjyCommunity queryById(Long communityId);

    /**
     * 分页查询
     *
     * @param hjyCommunity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HjyCommunity> queryByPage(HjyCommunity hjyCommunity, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param hjyCommunity 实例对象
     * @return 实例对象
     */
    HjyCommunity insert(HjyCommunity hjyCommunity);

    /**
     * 修改数据
     *
     * @param hjyCommunity 实例对象
     * @return 实例对象
     */
    HjyCommunity update(HjyCommunity hjyCommunity);

    /**
     * 通过主键删除数据
     *
     * @param communityId 主键
     * @return 是否成功
     */
    boolean deleteById(Long communityId);

}
