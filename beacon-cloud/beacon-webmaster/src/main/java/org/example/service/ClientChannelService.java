package org.example.service;

import org.example.util.PageResult;
import org.example.vo.ClientChannelVO;
import java.util.List;

public interface ClientChannelService {
    PageResult<ClientChannelVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    ClientChannelVO findById(Long id);
    void save(ClientChannelVO clientChannelVO);
    void update(ClientChannelVO clientChannelVO);
}
