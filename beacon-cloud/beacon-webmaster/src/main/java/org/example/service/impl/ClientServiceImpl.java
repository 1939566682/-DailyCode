package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.Client;
import org.example.entity.ClientExample;
import org.example.mapper.ClientMapper;
import org.example.service.ClientService;
import org.example.util.PageResult;
import org.example.util.VOConverter;
import org.example.vo.ClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public PageResult<ClientVO> list(int offset, int limit, String search) {
        ClientExample example = new ClientExample();
        if (search != null && !search.isEmpty()) {
            example.createCriteria().andcorpnameLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");
        PageHelper.offsetPage(offset, limit);
        List<Client> list = clientMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();
        List<ClientVO> voList = VOConverter.toVOList(list, ClientVO.class);
        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            clientMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public ClientVO findById(Long id) {
        Client client = clientMapper.selectByPrimaryKey(id);
        return VOConverter.toVO(client, ClientVO.class);
    }

    @Override
    public void save(ClientVO clientVO) {
        Client client = VOConverter.toEntity(clientVO, Client.class);
        client.setCreated(new Date());
        clientMapper.insertSelective(client);
    }

    @Override
    public void update(ClientVO clientVO) {
        Client client = VOConverter.toEntity(clientVO, Client.class);
        client.setUpdated(new Date());
        clientMapper.updateByPrimaryKeySelective(client);
    }

    @Override
    public List<ClientVO> findAll() {
        List<Client> list = clientMapper.selectByExample(null);
        return VOConverter.toVOList(list, ClientVO.class);
    }
}
