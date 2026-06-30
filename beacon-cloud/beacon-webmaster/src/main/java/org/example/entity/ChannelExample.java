package org.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChannelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChannelExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andchannelNameIsNull() {
            addCriterion("channel_name is null");
            return (Criteria) this;
        }

        public Criteria andchannelNameIsNotNull() {
            addCriterion("channel_name is not null");
            return (Criteria) this;
        }

        public Criteria andchannelNameEqualTo(String value) {
            addCriterion("channel_name =", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelNameNotEqualTo(String value) {
            addCriterion("channel_name <>", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelNameGreaterThan(String value) {
            addCriterion("channel_name >", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelNameGreaterThanOrEqualTo(String value) {
            addCriterion("channel_name >=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelNameLessThan(String value) {
            addCriterion("channel_name <", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelNameLessThanOrEqualTo(String value) {
            addCriterion("channel_name <=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelNameLike(String value) {
            addCriterion("channel_name like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelNameNotLike(String value) {
            addCriterion("channel_name not like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelNameIn(List<String> values) {
            addCriterion("channel_name in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelNameNotIn(List<String> values) {
            addCriterion("channel_name not in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelNameBetween(String value1, String value2) {
            addCriterion("channel_name between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelNameNotBetween(String value1, String value2) {
            addCriterion("channel_name not between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andchannelTypeIsNull() {
            addCriterion("channel_type is null");
            return (Criteria) this;
        }

        public Criteria andchannelTypeIsNotNull() {
            addCriterion("channel_type is not null");
            return (Criteria) this;
        }

        public Criteria andchannelTypeEqualTo(Integer value) {
            addCriterion("channel_type =", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andchannelTypeNotEqualTo(Integer value) {
            addCriterion("channel_type <>", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andchannelTypeGreaterThan(Integer value) {
            addCriterion("channel_type >", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andchannelTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_type >=", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andchannelTypeLessThan(Integer value) {
            addCriterion("channel_type <", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andchannelTypeLessThanOrEqualTo(Integer value) {
            addCriterion("channel_type <=", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andchannelTypeIn(List<Integer> values) {
            addCriterion("channel_type in", values, "channelType");
            return (Criteria) this;
        }

        public Criteria andchannelTypeNotIn(List<Integer> values) {
            addCriterion("channel_type not in", values, "channelType");
            return (Criteria) this;
        }

        public Criteria andchannelTypeBetween(Integer value1, Integer value2) {
            addCriterion("channel_type between", value1, value2, "channelType");
            return (Criteria) this;
        }

        public Criteria andchannelTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_type not between", value1, value2, "channelType");
            return (Criteria) this;
        }

        public Criteria andchannelAreaIsNull() {
            addCriterion("channel_area is null");
            return (Criteria) this;
        }

        public Criteria andchannelAreaIsNotNull() {
            addCriterion("channel_area is not null");
            return (Criteria) this;
        }

        public Criteria andchannelAreaEqualTo(String value) {
            addCriterion("channel_area =", value, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaNotEqualTo(String value) {
            addCriterion("channel_area <>", value, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaGreaterThan(String value) {
            addCriterion("channel_area >", value, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaGreaterThanOrEqualTo(String value) {
            addCriterion("channel_area >=", value, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaLessThan(String value) {
            addCriterion("channel_area <", value, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaLessThanOrEqualTo(String value) {
            addCriterion("channel_area <=", value, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaLike(String value) {
            addCriterion("channel_area like", value, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaNotLike(String value) {
            addCriterion("channel_area not like", value, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaIn(List<String> values) {
            addCriterion("channel_area in", values, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaNotIn(List<String> values) {
            addCriterion("channel_area not in", values, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaBetween(String value1, String value2) {
            addCriterion("channel_area between", value1, value2, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaNotBetween(String value1, String value2) {
            addCriterion("channel_area not between", value1, value2, "channelArea");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeIsNull() {
            addCriterion("channel_area_code is null");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeIsNotNull() {
            addCriterion("channel_area_code is not null");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeEqualTo(String value) {
            addCriterion("channel_area_code =", value, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeNotEqualTo(String value) {
            addCriterion("channel_area_code <>", value, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeGreaterThan(String value) {
            addCriterion("channel_area_code >", value, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("channel_area_code >=", value, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeLessThan(String value) {
            addCriterion("channel_area_code <", value, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("channel_area_code <=", value, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeLike(String value) {
            addCriterion("channel_area_code like", value, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeNotLike(String value) {
            addCriterion("channel_area_code not like", value, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeIn(List<String> values) {
            addCriterion("channel_area_code in", values, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeNotIn(List<String> values) {
            addCriterion("channel_area_code not in", values, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeBetween(String value1, String value2) {
            addCriterion("channel_area_code between", value1, value2, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelAreaCodeNotBetween(String value1, String value2) {
            addCriterion("channel_area_code not between", value1, value2, "channelAreaCode");
            return (Criteria) this;
        }

        public Criteria andchannelPriceIsNull() {
            addCriterion("channel_price is null");
            return (Criteria) this;
        }

        public Criteria andchannelPriceIsNotNull() {
            addCriterion("channel_price is not null");
            return (Criteria) this;
        }

        public Criteria andchannelPriceEqualTo(Long value) {
            addCriterion("channel_price =", value, "channelPrice");
            return (Criteria) this;
        }

        public Criteria andchannelPriceNotEqualTo(Long value) {
            addCriterion("channel_price <>", value, "channelPrice");
            return (Criteria) this;
        }

        public Criteria andchannelPriceGreaterThan(Long value) {
            addCriterion("channel_price >", value, "channelPrice");
            return (Criteria) this;
        }

        public Criteria andchannelPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("channel_price >=", value, "channelPrice");
            return (Criteria) this;
        }

        public Criteria andchannelPriceLessThan(Long value) {
            addCriterion("channel_price <", value, "channelPrice");
            return (Criteria) this;
        }

        public Criteria andchannelPriceLessThanOrEqualTo(Long value) {
            addCriterion("channel_price <=", value, "channelPrice");
            return (Criteria) this;
        }

        public Criteria andchannelPriceIn(List<Long> values) {
            addCriterion("channel_price in", values, "channelPrice");
            return (Criteria) this;
        }

        public Criteria andchannelPriceNotIn(List<Long> values) {
            addCriterion("channel_price not in", values, "channelPrice");
            return (Criteria) this;
        }

        public Criteria andchannelPriceBetween(Long value1, Long value2) {
            addCriterion("channel_price between", value1, value2, "channelPrice");
            return (Criteria) this;
        }

        public Criteria andchannelPriceNotBetween(Long value1, Long value2) {
            addCriterion("channel_price not between", value1, value2, "channelPrice");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalIsNull() {
            addCriterion("channel_protocal is null");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalIsNotNull() {
            addCriterion("channel_protocal is not null");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalEqualTo(Integer value) {
            addCriterion("channel_protocal =", value, "channelProtocal");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalNotEqualTo(Integer value) {
            addCriterion("channel_protocal <>", value, "channelProtocal");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalGreaterThan(Integer value) {
            addCriterion("channel_protocal >", value, "channelProtocal");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_protocal >=", value, "channelProtocal");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalLessThan(Integer value) {
            addCriterion("channel_protocal <", value, "channelProtocal");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalLessThanOrEqualTo(Integer value) {
            addCriterion("channel_protocal <=", value, "channelProtocal");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalIn(List<Integer> values) {
            addCriterion("channel_protocal in", values, "channelProtocal");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalNotIn(List<Integer> values) {
            addCriterion("channel_protocal not in", values, "channelProtocal");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalBetween(Integer value1, Integer value2) {
            addCriterion("channel_protocal between", value1, value2, "channelProtocal");
            return (Criteria) this;
        }

        public Criteria andchannelProtocalNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_protocal not between", value1, value2, "channelProtocal");
            return (Criteria) this;
        }

        public Criteria andchannelIpIsNull() {
            addCriterion("channel_ip is null");
            return (Criteria) this;
        }

        public Criteria andchannelIpIsNotNull() {
            addCriterion("channel_ip is not null");
            return (Criteria) this;
        }

        public Criteria andchannelIpEqualTo(String value) {
            addCriterion("channel_ip =", value, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelIpNotEqualTo(String value) {
            addCriterion("channel_ip <>", value, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelIpGreaterThan(String value) {
            addCriterion("channel_ip >", value, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelIpGreaterThanOrEqualTo(String value) {
            addCriterion("channel_ip >=", value, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelIpLessThan(String value) {
            addCriterion("channel_ip <", value, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelIpLessThanOrEqualTo(String value) {
            addCriterion("channel_ip <=", value, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelIpLike(String value) {
            addCriterion("channel_ip like", value, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelIpNotLike(String value) {
            addCriterion("channel_ip not like", value, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelIpIn(List<String> values) {
            addCriterion("channel_ip in", values, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelIpNotIn(List<String> values) {
            addCriterion("channel_ip not in", values, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelIpBetween(String value1, String value2) {
            addCriterion("channel_ip between", value1, value2, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelIpNotBetween(String value1, String value2) {
            addCriterion("channel_ip not between", value1, value2, "channelIp");
            return (Criteria) this;
        }

        public Criteria andchannelPortIsNull() {
            addCriterion("channel_port is null");
            return (Criteria) this;
        }

        public Criteria andchannelPortIsNotNull() {
            addCriterion("channel_port is not null");
            return (Criteria) this;
        }

        public Criteria andchannelPortEqualTo(Integer value) {
            addCriterion("channel_port =", value, "channelPort");
            return (Criteria) this;
        }

        public Criteria andchannelPortNotEqualTo(Integer value) {
            addCriterion("channel_port <>", value, "channelPort");
            return (Criteria) this;
        }

        public Criteria andchannelPortGreaterThan(Integer value) {
            addCriterion("channel_port >", value, "channelPort");
            return (Criteria) this;
        }

        public Criteria andchannelPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_port >=", value, "channelPort");
            return (Criteria) this;
        }

        public Criteria andchannelPortLessThan(Integer value) {
            addCriterion("channel_port <", value, "channelPort");
            return (Criteria) this;
        }

        public Criteria andchannelPortLessThanOrEqualTo(Integer value) {
            addCriterion("channel_port <=", value, "channelPort");
            return (Criteria) this;
        }

        public Criteria andchannelPortIn(List<Integer> values) {
            addCriterion("channel_port in", values, "channelPort");
            return (Criteria) this;
        }

        public Criteria andchannelPortNotIn(List<Integer> values) {
            addCriterion("channel_port not in", values, "channelPort");
            return (Criteria) this;
        }

        public Criteria andchannelPortBetween(Integer value1, Integer value2) {
            addCriterion("channel_port between", value1, value2, "channelPort");
            return (Criteria) this;
        }

        public Criteria andchannelPortNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_port not between", value1, value2, "channelPort");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameIsNull() {
            addCriterion("channel_username is null");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameIsNotNull() {
            addCriterion("channel_username is not null");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameEqualTo(String value) {
            addCriterion("channel_username =", value, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameNotEqualTo(String value) {
            addCriterion("channel_username <>", value, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameGreaterThan(String value) {
            addCriterion("channel_username >", value, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("channel_username >=", value, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameLessThan(String value) {
            addCriterion("channel_username <", value, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameLessThanOrEqualTo(String value) {
            addCriterion("channel_username <=", value, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameLike(String value) {
            addCriterion("channel_username like", value, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameNotLike(String value) {
            addCriterion("channel_username not like", value, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameIn(List<String> values) {
            addCriterion("channel_username in", values, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameNotIn(List<String> values) {
            addCriterion("channel_username not in", values, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameBetween(String value1, String value2) {
            addCriterion("channel_username between", value1, value2, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelUsernameNotBetween(String value1, String value2) {
            addCriterion("channel_username not between", value1, value2, "channelUsername");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordIsNull() {
            addCriterion("channel_password is null");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordIsNotNull() {
            addCriterion("channel_password is not null");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordEqualTo(String value) {
            addCriterion("channel_password =", value, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordNotEqualTo(String value) {
            addCriterion("channel_password <>", value, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordGreaterThan(String value) {
            addCriterion("channel_password >", value, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("channel_password >=", value, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordLessThan(String value) {
            addCriterion("channel_password <", value, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordLessThanOrEqualTo(String value) {
            addCriterion("channel_password <=", value, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordLike(String value) {
            addCriterion("channel_password like", value, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordNotLike(String value) {
            addCriterion("channel_password not like", value, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordIn(List<String> values) {
            addCriterion("channel_password in", values, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordNotIn(List<String> values) {
            addCriterion("channel_password not in", values, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordBetween(String value1, String value2) {
            addCriterion("channel_password between", value1, value2, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelPasswordNotBetween(String value1, String value2) {
            addCriterion("channel_password not between", value1, value2, "channelPassword");
            return (Criteria) this;
        }

        public Criteria andchannelNumberIsNull() {
            addCriterion("channel__number is null");
            return (Criteria) this;
        }

        public Criteria andchannelNumberIsNotNull() {
            addCriterion("channel__number is not null");
            return (Criteria) this;
        }

        public Criteria andchannelNumberEqualTo(String value) {
            addCriterion("channel__number =", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andchannelNumberNotEqualTo(String value) {
            addCriterion("channel__number <>", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andchannelNumberGreaterThan(String value) {
            addCriterion("channel__number >", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andchannelNumberGreaterThanOrEqualTo(String value) {
            addCriterion("channel__number >=", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andchannelNumberLessThan(String value) {
            addCriterion("channel__number <", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andchannelNumberLessThanOrEqualTo(String value) {
            addCriterion("channel__number <=", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andchannelNumberLike(String value) {
            addCriterion("channel__number like", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andchannelNumberNotLike(String value) {
            addCriterion("channel__number not like", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andchannelNumberIn(List<String> values) {
            addCriterion("channel__number in", values, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andchannelNumberNotIn(List<String> values) {
            addCriterion("channel__number not in", values, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andchannelNumberBetween(String value1, String value2) {
            addCriterion("channel__number between", value1, value2, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andchannelNumberNotBetween(String value1, String value2) {
            addCriterion("channel__number not between", value1, value2, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andisAvailableIsNull() {
            addCriterion("is_available is null");
            return (Criteria) this;
        }

        public Criteria andisAvailableIsNotNull() {
            addCriterion("is_available is not null");
            return (Criteria) this;
        }

        public Criteria andisAvailableEqualTo(Integer value) {
            addCriterion("is_available =", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andisAvailableNotEqualTo(Integer value) {
            addCriterion("is_available <>", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andisAvailableGreaterThan(Integer value) {
            addCriterion("is_available >", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andisAvailableGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_available >=", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andisAvailableLessThan(Integer value) {
            addCriterion("is_available <", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andisAvailableLessThanOrEqualTo(Integer value) {
            addCriterion("is_available <=", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andisAvailableIn(List<Integer> values) {
            addCriterion("is_available in", values, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andisAvailableNotIn(List<Integer> values) {
            addCriterion("is_available not in", values, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andisAvailableBetween(Integer value1, Integer value2) {
            addCriterion("is_available between", value1, value2, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andisAvailableNotBetween(Integer value1, Integer value2) {
            addCriterion("is_available not between", value1, value2, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andcreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andcreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andcreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andcreateIdIsNull() {
            addCriterion("create_id is null");
            return (Criteria) this;
        }

        public Criteria andcreateIdIsNotNull() {
            addCriterion("create_id is not null");
            return (Criteria) this;
        }

        public Criteria andcreateIdEqualTo(Long value) {
            addCriterion("create_id =", value, "createId");
            return (Criteria) this;
        }

        public Criteria andcreateIdNotEqualTo(Long value) {
            addCriterion("create_id <>", value, "createId");
            return (Criteria) this;
        }

        public Criteria andcreateIdGreaterThan(Long value) {
            addCriterion("create_id >", value, "createId");
            return (Criteria) this;
        }

        public Criteria andcreateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("create_id >=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andcreateIdLessThan(Long value) {
            addCriterion("create_id <", value, "createId");
            return (Criteria) this;
        }

        public Criteria andcreateIdLessThanOrEqualTo(Long value) {
            addCriterion("create_id <=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andcreateIdIn(List<Long> values) {
            addCriterion("create_id in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andcreateIdNotIn(List<Long> values) {
            addCriterion("create_id not in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andcreateIdBetween(Long value1, Long value2) {
            addCriterion("create_id between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andcreateIdNotBetween(Long value1, Long value2) {
            addCriterion("create_id not between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andupdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andupdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andupdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andupdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andupdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andupdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andupdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andupdateIdIsNull() {
            addCriterion("update_id is null");
            return (Criteria) this;
        }

        public Criteria andupdateIdIsNotNull() {
            addCriterion("update_id is not null");
            return (Criteria) this;
        }

        public Criteria andupdateIdEqualTo(Long value) {
            addCriterion("update_id =", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andupdateIdNotEqualTo(Long value) {
            addCriterion("update_id <>", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andupdateIdGreaterThan(Long value) {
            addCriterion("update_id >", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andupdateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("update_id >=", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andupdateIdLessThan(Long value) {
            addCriterion("update_id <", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andupdateIdLessThanOrEqualTo(Long value) {
            addCriterion("update_id <=", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andupdateIdIn(List<Long> values) {
            addCriterion("update_id in", values, "updateId");
            return (Criteria) this;
        }

        public Criteria andupdateIdNotIn(List<Long> values) {
            addCriterion("update_id not in", values, "updateId");
            return (Criteria) this;
        }

        public Criteria andupdateIdBetween(Long value1, Long value2) {
            addCriterion("update_id between", value1, value2, "updateId");
            return (Criteria) this;
        }

        public Criteria andupdateIdNotBetween(Long value1, Long value2) {
            addCriterion("update_id not between", value1, value2, "updateId");
            return (Criteria) this;
        }

        public Criteria andisDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andisDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andisDeleteEqualTo(Byte value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andisDeleteNotEqualTo(Byte value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andisDeleteGreaterThan(Byte value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andisDeleteGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andisDeleteLessThan(Byte value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andisDeleteLessThanOrEqualTo(Byte value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andisDeleteIn(List<Byte> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andisDeleteNotIn(List<Byte> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andisDeleteBetween(Byte value1, Byte value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andisDeleteNotBetween(Byte value1, Byte value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andextend1IsNull() {
            addCriterion("extend1 is null");
            return (Criteria) this;
        }

        public Criteria andextend1IsNotNull() {
            addCriterion("extend1 is not null");
            return (Criteria) this;
        }

        public Criteria andextend1EqualTo(String value) {
            addCriterion("extend1 =", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend1NotEqualTo(String value) {
            addCriterion("extend1 <>", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend1GreaterThan(String value) {
            addCriterion("extend1 >", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend1GreaterThanOrEqualTo(String value) {
            addCriterion("extend1 >=", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend1LessThan(String value) {
            addCriterion("extend1 <", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend1LessThanOrEqualTo(String value) {
            addCriterion("extend1 <=", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend1Like(String value) {
            addCriterion("extend1 like", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend1NotLike(String value) {
            addCriterion("extend1 not like", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend1In(List<String> values) {
            addCriterion("extend1 in", values, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend1NotIn(List<String> values) {
            addCriterion("extend1 not in", values, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend1Between(String value1, String value2) {
            addCriterion("extend1 between", value1, value2, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend1NotBetween(String value1, String value2) {
            addCriterion("extend1 not between", value1, value2, "extend1");
            return (Criteria) this;
        }

        public Criteria andextend2IsNull() {
            addCriterion("extend2 is null");
            return (Criteria) this;
        }

        public Criteria andextend2IsNotNull() {
            addCriterion("extend2 is not null");
            return (Criteria) this;
        }

        public Criteria andextend2EqualTo(String value) {
            addCriterion("extend2 =", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend2NotEqualTo(String value) {
            addCriterion("extend2 <>", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend2GreaterThan(String value) {
            addCriterion("extend2 >", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend2GreaterThanOrEqualTo(String value) {
            addCriterion("extend2 >=", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend2LessThan(String value) {
            addCriterion("extend2 <", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend2LessThanOrEqualTo(String value) {
            addCriterion("extend2 <=", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend2Like(String value) {
            addCriterion("extend2 like", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend2NotLike(String value) {
            addCriterion("extend2 not like", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend2In(List<String> values) {
            addCriterion("extend2 in", values, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend2NotIn(List<String> values) {
            addCriterion("extend2 not in", values, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend2Between(String value1, String value2) {
            addCriterion("extend2 between", value1, value2, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend2NotBetween(String value1, String value2) {
            addCriterion("extend2 not between", value1, value2, "extend2");
            return (Criteria) this;
        }

        public Criteria andextend3IsNull() {
            addCriterion("extend3 is null");
            return (Criteria) this;
        }

        public Criteria andextend3IsNotNull() {
            addCriterion("extend3 is not null");
            return (Criteria) this;
        }

        public Criteria andextend3EqualTo(String value) {
            addCriterion("extend3 =", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend3NotEqualTo(String value) {
            addCriterion("extend3 <>", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend3GreaterThan(String value) {
            addCriterion("extend3 >", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend3GreaterThanOrEqualTo(String value) {
            addCriterion("extend3 >=", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend3LessThan(String value) {
            addCriterion("extend3 <", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend3LessThanOrEqualTo(String value) {
            addCriterion("extend3 <=", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend3Like(String value) {
            addCriterion("extend3 like", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend3NotLike(String value) {
            addCriterion("extend3 not like", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend3In(List<String> values) {
            addCriterion("extend3 in", values, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend3NotIn(List<String> values) {
            addCriterion("extend3 not in", values, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend3Between(String value1, String value2) {
            addCriterion("extend3 between", value1, value2, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend3NotBetween(String value1, String value2) {
            addCriterion("extend3 not between", value1, value2, "extend3");
            return (Criteria) this;
        }

        public Criteria andextend4IsNull() {
            addCriterion("extend4 is null");
            return (Criteria) this;
        }

        public Criteria andextend4IsNotNull() {
            addCriterion("extend4 is not null");
            return (Criteria) this;
        }

        public Criteria andextend4EqualTo(String value) {
            addCriterion("extend4 =", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andextend4NotEqualTo(String value) {
            addCriterion("extend4 <>", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andextend4GreaterThan(String value) {
            addCriterion("extend4 >", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andextend4GreaterThanOrEqualTo(String value) {
            addCriterion("extend4 >=", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andextend4LessThan(String value) {
            addCriterion("extend4 <", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andextend4LessThanOrEqualTo(String value) {
            addCriterion("extend4 <=", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andextend4Like(String value) {
            addCriterion("extend4 like", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andextend4NotLike(String value) {
            addCriterion("extend4 not like", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andextend4In(List<String> values) {
            addCriterion("extend4 in", values, "extend4");
            return (Criteria) this;
        }

        public Criteria andextend4NotIn(List<String> values) {
            addCriterion("extend4 not in", values, "extend4");
            return (Criteria) this;
        }

        public Criteria andextend4Between(String value1, String value2) {
            addCriterion("extend4 between", value1, value2, "extend4");
            return (Criteria) this;
        }

        public Criteria andextend4NotBetween(String value1, String value2) {
            addCriterion("extend4 not between", value1, value2, "extend4");
            return (Criteria) this;
        }

    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}