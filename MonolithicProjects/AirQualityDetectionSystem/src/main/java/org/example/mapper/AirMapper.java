package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Air;
import org.example.vo.AirVO;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 19:15
 */

@Mapper
public interface AirMapper {

    /**
     * 分页条件查询空气质量信息
     */
    List<AirVO> getAirListByDistrictId(Integer districtId);

    /**
     * 添加空气质量信息
     */
    @Insert("insert into air_quality_index(district_id, monitor_time, pm10, pm25, monitoring_station)" +
            " values (#{districtId},#{monitorTime},#{pm10},#{pm25},#{monitoringStation})")
    Integer addAir(Air air);

    /**
     * 修改空气质量信息
     */
    @Update("update air_quality_index set district_id=#{districtId},monitor_time=#{monitorTime}," +
            "pm10=#{pm10},pm25=#{pm25},monitoring_station=#{monitoringStation} where id = #{id}")
    Integer updateAirById(Air air);

    /**
     * 删除空气质量信息
     */
    @Delete("delete from air_quality_index where id = #{id}")
    Integer deleteAirById(@Param("id") Integer id);

}
