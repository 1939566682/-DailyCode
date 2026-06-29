package org.example.service;

import org.example.util.PageResult;
import org.example.vo.ApiGatewayFilterVO;
import java.util.List;

public interface ApiGatewayFilterService {
    PageResult<ApiGatewayFilterVO> list(int offset, int limit, String search);
    void delete(Long[] ids);
    ApiGatewayFilterVO findById(Long id);
    void save(ApiGatewayFilterVO vo);
    void update(ApiGatewayFilterVO vo);
}
