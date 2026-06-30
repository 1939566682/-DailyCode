package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.ClientAccountRecord;
import org.example.entity.ClientAccountRecordExample;
import org.example.entity.ClientBusiness;
import org.example.entity.ClientBusinessExample;
import org.example.mapper.ClientAccountRecordMapper;
import org.example.mapper.ClientBusinessMapper;
import org.example.service.ClientAccountRecordService;
import org.example.util.PageResult;
import org.example.vo.AcountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户记录（充值/消费）Service 实现
 * 对应前端 acount.js
 * 路径前缀: /sys/acount
 */
@Service
public class AcountServiceImpl implements ClientAccountRecordService {

    @Autowired
    private ClientAccountRecordMapper clientAccountRecordMapper;

    @Autowired
    private ClientBusinessMapper clientBusinessMapper;

    @Override
    public PageResult<AcountVO> list(int offset, int limit, String search) {
        ClientAccountRecordExample example = new ClientAccountRecordExample();
        ClientAccountRecordExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(search)) {
            criteria.andpaidInfoLike("%" + search + "%");
        }
        example.setOrderByClause("id desc");

        PageHelper.offsetPage(offset, limit);
        List<ClientAccountRecord> list = clientAccountRecordMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();

        // 联查 client_business 拿 corpname
        Map<Long, String> corpNameMap = queryCorpNameMap(list);

        List<AcountVO> voList = new ArrayList<>();
        for (ClientAccountRecord record : list) {
            AcountVO vo = new AcountVO();
            vo.setId(record.getId());
            vo.setOrderid(record.getId());
            vo.setCorpname(corpNameMap.getOrDefault(record.getClientId(), ""));
            vo.setPaidvalue(record.getPaidValue());
            vo.setCreatetime(formatDate(record.getCreated()));
            vo.setPaytime(formatDate(record.getUpdated()));
            vo.setPaymentorder("");
            vo.setPaymentinfo(record.getPaidInfo());
            // paymentid 优先用 extend1 字段（若有）
            // 1=微信 2=支付宝
            vo.setPaymentid(null);
            vo.setClientId(record.getClientId());
            voList.add(vo);
        }

        return new PageResult<>(total, voList);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            clientAccountRecordMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public AcountVO findById(Long id) {
        ClientAccountRecord record = clientAccountRecordMapper.selectByPrimaryKey(id);
        if (record == null) {
            return null;
        }
        Map<Long, String> corpNameMap = queryCorpNameMap(java.util.Collections.singletonList(record));
        AcountVO vo = new AcountVO();
        vo.setId(record.getId());
        vo.setOrderid(record.getId());
        vo.setCorpname(corpNameMap.getOrDefault(record.getClientId(), ""));
        vo.setPaidvalue(record.getPaidValue());
        vo.setCreatetime(formatDate(record.getCreated()));
        vo.setPaytime(formatDate(record.getUpdated()));
        vo.setPaymentorder("");
        vo.setPaymentinfo(record.getPaidInfo());
        vo.setPaymentid(null);
        vo.setClientId(record.getClientId());
        return vo;
    }

    @Override
    public void save(AcountVO acountVO) {
        ClientAccountRecord record = new ClientAccountRecord();
        record.setClientId(acountVO.getClientId());
        record.setPaidValue(acountVO.getPaidvalue());
        record.setPaidInfo(acountVO.getPaymentinfo());
        record.setPaidState(0); // 默认 0-充值记录
        clientAccountRecordMapper.insertSelective(record);
    }

    @Override
    public void update(AcountVO acountVO) {
        ClientAccountRecord record = new ClientAccountRecord();
        record.setId(acountVO.getId());
        record.setClientId(acountVO.getClientId());
        record.setPaidValue(acountVO.getPaidvalue());
        record.setPaidInfo(acountVO.getPaymentinfo());
        clientAccountRecordMapper.updateByPrimaryKeySelective(record);
    }

    private Map<Long, String> queryCorpNameMap(List<ClientAccountRecord> list) {
        Map<Long, String> map = new HashMap<>();
        if (list == null || list.isEmpty()) {
            return map;
        }
        // 收集 clientId
        List<Long> ids = new ArrayList<>();
        for (ClientAccountRecord r : list) {
            if (r.getClientId() != null && !ids.contains(r.getClientId())) {
                ids.add(r.getClientId());
            }
        }
        if (ids.isEmpty()) {
            return map;
        }
        // 联查 client_business
        try {
            ClientBusinessExample cbExample = new ClientBusinessExample();
            cbExample.createCriteria().andIdIn(ids);
            List<ClientBusiness> businesses = clientBusinessMapper.selectByExample(cbExample);
            for (ClientBusiness b : businesses) {
                map.put(b.getId(), b.getCorpname());
            }
        } catch (Exception e) {
            // 容错：联查失败不影响主流程
        }
        return map;
    }

    private String formatDate(java.util.Date d) {
        if (d == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
    }
}
