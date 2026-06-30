package org.example.service;

import org.example.util.PageResult;
import org.example.vo.ClientVO;
import java.util.List;

public interface ClientService {
    PageResult<ClientVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    ClientVO findById(Long id);
    void save(ClientVO clientVO);
    void update(ClientVO clientVO);
    List<ClientVO> findAll();
}
