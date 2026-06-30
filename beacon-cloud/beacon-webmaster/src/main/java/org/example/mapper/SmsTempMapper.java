package org.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.SmsTemp;
import org.example.entity.SmsTempExample;

/**
 * SmsTempMapper - 短信模板Mapper
 * TODO: 需确认数据库 sms_temp 表是否已创建
 */
public interface SmsTempMapper {
    long countByExample(SmsTempExample example);

    int deleteByExample(SmsTempExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsTemp row);

    int insertSelective(SmsTemp row);

    List<SmsTemp> selectByExample(SmsTempExample example);

    SmsTemp selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") SmsTemp row, @Param("example") SmsTempExample example);

    int updateByExample(@Param("row") SmsTemp row, @Param("example") SmsTempExample example);

    int updateByPrimaryKeySelective(SmsTemp row);

    int updateByPrimaryKey(SmsTemp row);
}
