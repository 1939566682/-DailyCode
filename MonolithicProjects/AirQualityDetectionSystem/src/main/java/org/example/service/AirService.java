package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.dto.AirAddDTO;
import org.example.dto.AirUpdateDTO;
import org.example.vo.AirVO;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 19:16
 */

public interface AirService {

    /**
     * 分页条件查询空气质量信息
     */
    PageInfo<AirVO> getAirListByDistrictId(Integer districtId, Integer pageNum, Integer pageSize);

    /**
     * 添加空气质量信息
     */
    void addAir(AirAddDTO air);

    /**
     * 修改空气质量信息
     */
    void updateAirById(AirUpdateDTO air);

    /**
     * 删除空气质量信息
     */
    void deleteAirById(Integer id);
}
