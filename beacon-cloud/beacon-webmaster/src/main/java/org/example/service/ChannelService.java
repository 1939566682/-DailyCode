package org.example.service;

import org.example.util.PageResult;
import org.example.vo.ChannelVO;
import java.util.List;

public interface ChannelService {
    PageResult<ChannelVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    ChannelVO findById(Long id);
    void save(ChannelVO channelVO);
    void update(ChannelVO channelVO);
    List<ChannelVO> findAll();
}
