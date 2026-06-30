package org.example.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.example.entity.SmsRole;
import org.example.entity.SmsRoleExample;

public interface SmsRoleMapper {
    long countByExample(SmsRoleExample example);

    int deleteByExample(SmsRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsRole row);

    int insertSelective(SmsRole row);

    List<SmsRole> selectByExample(SmsRoleExample example);

    SmsRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") SmsRole row, @Param("example") SmsRoleExample example);

    int updateByExample(@Param("row") SmsRole row, @Param("example") SmsRoleExample example);

    int updateByPrimaryKeySelective(SmsRole row);

    int updateByPrimaryKey(SmsRole row);
    
    /**
     * 根据用户id查询角色名称
     * @param userId
     * @return
     */
	Set<String> findRoleNameByUserId(@Param("userId")Long userId);
}
