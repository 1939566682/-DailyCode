package org.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.example.entity.SmsMenu;
import org.example.entity.SmsMenuExample;

public interface SmsMenuMapper {
    long countByExample(SmsMenuExample example);

    int deleteByExample(SmsMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsMenu row);

    int insertSelective(SmsMenu row);

    List<SmsMenu> selectByExample(SmsMenuExample example);

    SmsMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") SmsMenu row, @Param("example") SmsMenuExample example);

    int updateByExample(@Param("row") SmsMenu row, @Param("example") SmsMenuExample example);

    int updateByPrimaryKeySelective(SmsMenu row);

    int updateByPrimaryKey(SmsMenu row);
    
    /**
     * 根据用户id查询用户的菜单信息
     * @param id
     * @return
     */
	List<Map<String, Object>> findMenuByUserId(@Param("userId") Long id);
}
