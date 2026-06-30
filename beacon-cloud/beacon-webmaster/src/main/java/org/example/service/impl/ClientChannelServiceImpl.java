package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.Channel;
import org.example.entity.ClientBusiness;
import org.example.entity.ClientChannel;
import org.example.entity.ClientChannelExample;
import org.example.mapper.ChannelMapper;
import org.example.mapper.ClientBusinessMapper;
import org.example.mapper.ClientChannelMapper;
import org.example.service.ClientChannelService;
import org.example.util.PageResult;
import org.example.vo.ClientChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClientChannelServiceImpl implements ClientChannelService {
    @Autowired
    private ClientChannelMapper clientChannelMapper;
    @Autowired
    private ClientBusinessMapper clientBusinessMapper;
    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public PageResult<ClientChannelVO> list(int offset, int limit, String search) {
        ClientChannelExample example = new ClientChannelExample();
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<ClientChannel> list = clientChannelMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<ClientChannelVO> voList = new ArrayList<>();
        for (ClientChannel cc : list) {
            ClientChannelVO vo = new ClientChannelVO();
            vo.setId(cc.getId());
            vo.setClientId(cc.getClientId());
            vo.setChannelId(cc.getChannelId());
            vo.setExtendnumber(cc.getClientChannelNumber());
            vo.setIsavailable(cc.getIsAvailable());
            // 联查 client_business
            ClientBusiness cb = clientBusinessMapper.selectByPrimaryKey(cc.getClientId());
            if (cb != null) {
                vo.setCorpname(cb.getCorpname());
            }
            // 联查 channel
            Channel ch = channelMapper.selectByPrimaryKey(cc.getChannelId());
            if (ch != null) {
                vo.setChannelname(ch.getChannelName());
                vo.setPrice(ch.getChannelPrice());
            }
            voList.add(vo);
        }
        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            clientChannelMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public ClientChannelVO findById(Long id) {
        ClientChannel cc = clientChannelMapper.selectByPrimaryKey(id);
        if (cc == null) return null;
        ClientChannelVO vo = new ClientChannelVO();
        vo.setId(cc.getId());
        vo.setClientId(cc.getClientId());
        vo.setChannelId(cc.getChannelId());
        vo.setExtendnumber(cc.getClientChannelNumber());
        vo.setIsavailable(cc.getIsAvailable());
        ClientBusiness cb = clientBusinessMapper.selectByPrimaryKey(cc.getClientId());
        if (cb != null) {
            vo.setCorpname(cb.getCorpname());
        }
        Channel ch = channelMapper.selectByPrimaryKey(cc.getChannelId());
        if (ch != null) {
            vo.setChannelname(ch.getChannelName());
            vo.setPrice(ch.getChannelPrice());
        }
        return vo;
    }

    @Override
    public void save(ClientChannelVO clientChannelVO) {
        ClientChannel cc = new ClientChannel();
        cc.setClientId(clientChannelVO.getClientId());
        cc.setChannelId(clientChannelVO.getChannelId());
        cc.setClientChannelNumber(clientChannelVO.getExtendnumber());
        cc.setIsAvailable(clientChannelVO.getIsavailable() != null ? clientChannelVO.getIsavailable() : 0);
        cc.setCreated(new Date());
        clientChannelMapper.insertSelective(cc);
    }

    @Override
    public void update(ClientChannelVO clientChannelVO) {
        ClientChannel cc = clientChannelMapper.selectByPrimaryKey(clientChannelVO.getId());
        if (cc == null) return;
        cc.setClientId(clientChannelVO.getClientId());
        cc.setChannelId(clientChannelVO.getChannelId());
        cc.setClientChannelNumber(clientChannelVO.getExtendnumber());
        cc.setIsAvailable(clientChannelVO.getIsavailable());
        cc.setUpdated(new Date());
        clientChannelMapper.updateByPrimaryKeySelective(cc);
    }
}
