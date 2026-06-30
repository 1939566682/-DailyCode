package org.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StrategyFilterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StrategyFilterExample() {
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

        public Criteria andfiltersIsNull() {
            addCriterion("filters is null");
            return (Criteria) this;
        }

        public Criteria andfiltersIsNotNull() {
            addCriterion("filters is not null");
            return (Criteria) this;
        }

        public Criteria andfiltersEqualTo(String value) {
            addCriterion("filters =", value, "filters");
            return (Criteria) this;
        }

        public Criteria andfiltersNotEqualTo(String value) {
            addCriterion("filters <>", value, "filters");
            return (Criteria) this;
        }

        public Criteria andfiltersGreaterThan(String value) {
            addCriterion("filters >", value, "filters");
            return (Criteria) this;
        }

        public Criteria andfiltersGreaterThanOrEqualTo(String value) {
            addCriterion("filters >=", value, "filters");
            return (Criteria) this;
        }

        public Criteria andfiltersLessThan(String value) {
            addCriterion("filters <", value, "filters");
            return (Criteria) this;
        }

        public Criteria andfiltersLessThanOrEqualTo(String value) {
            addCriterion("filters <=", value, "filters");
            return (Criteria) this;
        }

        public Criteria andfiltersLike(String value) {
            addCriterion("filters like", value, "filters");
            return (Criteria) this;
        }

        public Criteria andfiltersNotLike(String value) {
            addCriterion("filters not like", value, "filters");
            return (Criteria) this;
        }

        public Criteria andfiltersIn(List<String> values) {
            addCriterion("filters in", values, "filters");
            return (Criteria) this;
        }

        public Criteria andfiltersNotIn(List<String> values) {
            addCriterion("filters not in", values, "filters");
            return (Criteria) this;
        }

        public Criteria andfiltersBetween(String value1, String value2) {
            addCriterion("filters between", value1, value2, "filters");
            return (Criteria) this;
        }

        public Criteria andfiltersNotBetween(String value1, String value2) {
            addCriterion("filters not between", value1, value2, "filters");
            return (Criteria) this;
        }

        public Criteria andfilterStateIsNull() {
            addCriterion("filter_state is null");
            return (Criteria) this;
        }

        public Criteria andfilterStateIsNotNull() {
            addCriterion("filter_state is not null");
            return (Criteria) this;
        }

        public Criteria andfilterStateEqualTo(Integer value) {
            addCriterion("filter_state =", value, "filterState");
            return (Criteria) this;
        }

        public Criteria andfilterStateNotEqualTo(Integer value) {
            addCriterion("filter_state <>", value, "filterState");
            return (Criteria) this;
        }

        public Criteria andfilterStateGreaterThan(Integer value) {
            addCriterion("filter_state >", value, "filterState");
            return (Criteria) this;
        }

        public Criteria andfilterStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("filter_state >=", value, "filterState");
            return (Criteria) this;
        }

        public Criteria andfilterStateLessThan(Integer value) {
            addCriterion("filter_state <", value, "filterState");
            return (Criteria) this;
        }

        public Criteria andfilterStateLessThanOrEqualTo(Integer value) {
            addCriterion("filter_state <=", value, "filterState");
            return (Criteria) this;
        }

        public Criteria andfilterStateIn(List<Integer> values) {
            addCriterion("filter_state in", values, "filterState");
            return (Criteria) this;
        }

        public Criteria andfilterStateNotIn(List<Integer> values) {
            addCriterion("filter_state not in", values, "filterState");
            return (Criteria) this;
        }

        public Criteria andfilterStateBetween(Integer value1, Integer value2) {
            addCriterion("filter_state between", value1, value2, "filterState");
            return (Criteria) this;
        }

        public Criteria andfilterStateNotBetween(Integer value1, Integer value2) {
            addCriterion("filter_state not between", value1, value2, "filterState");
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