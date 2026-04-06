package org.example.hjycommunity.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.hjycommunity.community.domain.dto.HjyCommunityDTO;
import org.example.hjycommunity.community.domain.pojo.HjyCommunity;

import java.util.List;

/**
 * HjyCommunityMapper
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 18:40
 */

@Mapper
public interface HjyCommunityMapper extends BaseMapper<HjyCommunity> {

    List<HjyCommunityDTO> QueryCommunityList(HjyCommunity hjyCommunity);

}
