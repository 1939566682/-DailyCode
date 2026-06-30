package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.Channel;
import org.example.entity.ChannelExample;
import org.example.mapper.ChannelMapper;
import org.example.service.ChannelService;
import org.example.util.PageResult;
import org.example.vo.ChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public PageResult<ChannelVO> list(int offset, int limit, String search) {
        ChannelExample example = new ChannelExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andchannelNameLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<Channel> list = channelMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        // 手动映射差异字段
        List<ChannelVO> voList = new java.util.ArrayList<>();
        for (Channel channel : list) {
            ChannelVO vo = new ChannelVO();
            vo.setId(channel.getId());
            vo.setChannelname(channel.getChannelName());
            vo.setChanneltype(channel.getChannelType());
            vo.setSpnumber(channel.getChannelNumber());
            vo.setProtocaltype(channel.getChannelProtocal());
            vo.setChannelarea(channel.getChannelArea());
            vo.setChannelprice(channel.getChannelPrice());
            vo.setIsavailable(channel.getIsAvailable());
            voList.add(vo);
        }
        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            channelMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public ChannelVO findById(Long id) {
        Channel channel = channelMapper.selectByPrimaryKey(id);
        if (channel == null) return null;
        ChannelVO vo = new ChannelVO();
        vo.setId(channel.getId());
        vo.setChannelname(channel.getChannelName());
        vo.setChanneltype(channel.getChannelType());
        vo.setSpnumber(channel.getChannelNumber());
        vo.setProtocaltype(channel.getChannelProtocal());
        vo.setChannelarea(channel.getChannelArea());
        vo.setChannelprice(channel.getChannelPrice());
        vo.setIsavailable(channel.getIsAvailable());
        return vo;
    }

    @Override
    public void save(ChannelVO channelVO) {
        Channel channel = new Channel();
        channel.setChannelName(channelVO.getChannelname());
        channel.setChannelType(channelVO.getChanneltype());
        channel.setChannelNumber(channelVO.getSpnumber());
        channel.setChannelProtocal(channelVO.getProtocaltype());
        channel.setChannelArea(channelVO.getChannelarea());
        channel.setChannelPrice(channelVO.getChannelprice());
        channel.setIsAvailable(channelVO.getIsavailable());
        channel.setCreated(new Date());
        channelMapper.insertSelective(channel);
    }

    @Override
    public void update(ChannelVO channelVO) {
        Channel channel = channelMapper.selectByPrimaryKey(channelVO.getId());
        if (channel == null) return;
        channel.setChannelName(channelVO.getChannelname());
        channel.setChannelType(channelVO.getChanneltype());
        channel.setChannelNumber(channelVO.getSpnumber());
        channel.setChannelProtocal(channelVO.getProtocaltype());
        channel.setChannelArea(channelVO.getChannelarea());
        channel.setChannelPrice(channelVO.getChannelprice());
        channel.setIsAvailable(channelVO.getIsavailable());
        channel.setUpdated(new Date());
        channelMapper.updateByPrimaryKeySelective(channel);
    }

    @Override
    public List<ChannelVO> findAll() {
        List<Channel> list = channelMapper.selectByExample(null);
        List<ChannelVO> voList = new java.util.ArrayList<>();
        for (Channel channel : list) {
            ChannelVO vo = new ChannelVO();
            vo.setId(channel.getId());
            vo.setChannelname(channel.getChannelName());
            vo.setChanneltype(channel.getChannelType());
            vo.setSpnumber(channel.getChannelNumber());
            vo.setProtocaltype(channel.getChannelProtocal());
            vo.setIsavailable(channel.getIsAvailable());
            voList.add(vo);
        }
        return voList;
    }
}
