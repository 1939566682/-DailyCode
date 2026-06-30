#!/usr/bin/env python3
"""Generate MBG-style Example.java and Mapper.xml files for all tables."""
import os

BASE = r"E:\idea_workscope\beacon-cloud\beacon-webmaster\src\main"
ENTITY_DIR = os.path.join(BASE, "java", "org", "example", "entity")
MAPPER_XML_DIR = os.path.join(BASE, "resources", "mapper")

# Table definitions: table_name, entity_name, columns list (db_col, java_prop, jdbc_type)
TABLES = {
    "client": {
        "entity": "Client",
        "columns": [
            ("id", "id", "BIGINT"),
            ("corpname", "corpname", "VARCHAR"),
            ("address", "address", "VARCHAR"),
            ("linkman", "linkman", "VARCHAR"),
            ("mobile", "mobile", "VARCHAR"),
            ("email", "email", "VARCHAR"),
            ("customermanager", "customermanager", "VARCHAR"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
            ("extend3", "extend3", "VARCHAR"),
            ("extend4", "extend4", "VARCHAR"),
        ],
        "search_fields": ["corpname", "address", "linkman"],
    },
    "channel": {
        "entity": "Channel",
        "columns": [
            ("id", "id", "BIGINT"),
            ("channel_name", "channelName", "VARCHAR"),
            ("channel_type", "channelType", "INTEGER"),
            ("channel_area", "channelArea", "VARCHAR"),
            ("channel_area_code", "channelAreaCode", "VARCHAR"),
            ("channel_price", "channelPrice", "BIGINT"),
            ("channel_protocal", "channelProtocal", "INTEGER"),
            ("channel_ip", "channelIp", "VARCHAR"),
            ("channel_port", "channelPort", "INTEGER"),
            ("channel_username", "channelUsername", "VARCHAR"),
            ("channel_password", "channelPassword", "VARCHAR"),
            ("channel__number", "channelNumber", "VARCHAR"),
            ("is_available", "isAvailable", "INTEGER"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
            ("extend3", "extend3", "VARCHAR"),
            ("extend4", "extend4", "VARCHAR"),
        ],
        "search_fields": ["channelName"],
    },
    "client_channel": {
        "entity": "ClientChannel",
        "columns": [
            ("id", "id", "BIGINT"),
            ("client_id", "clientId", "BIGINT"),
            ("channel_id", "channelId", "BIGINT"),
            ("client_channel_weight", "clientChannelWeight", "INTEGER"),
            ("client_channel_number", "clientChannelNumber", "VARCHAR"),
            ("is_available", "isAvailable", "INTEGER"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
        ],
        "search_fields": [],
    },
    "mobile_black": {
        "entity": "MobileBlack",
        "columns": [
            ("id", "id", "BIGINT"),
            ("black_number", "blackNumber", "VARCHAR"),
            ("client_id", "clientId", "INTEGER"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
        ],
        "search_fields": ["blackNumber"],
    },
    "mobile_dirtyword": {
        "entity": "MobileDirtyword",
        "columns": [
            ("id", "id", "BIGINT"),
            ("dirtyword", "dirtyword", "VARCHAR"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
            ("extend3", "extend3", "VARCHAR"),
            ("extend4", "extend4", "VARCHAR"),
        ],
        "search_fields": ["dirtyword"],
    },
    "mobile_area": {
        "entity": "MobileArea",
        "columns": [
            ("id", "id", "BIGINT"),
            ("mobile_number", "mobileNumber", "VARCHAR"),
            ("province", "province", "VARCHAR"),
            ("city", "city", "VARCHAR"),
            ("province_code", "provinceCode", "VARCHAR"),
            ("city_code", "cityCode", "VARCHAR"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
        ],
        "search_fields": ["mobileNumber"],
    },
    "code_limit": {
        "entity": "CodeLimit",
        "columns": [
            ("id", "id", "BIGINT"),
            ("limit_time", "limitTime", "INTEGER"),
            ("limit_count", "limitCount", "INTEGER"),
            ("description", "description", "VARCHAR"),
            ("limit_state", "limitState", "INTEGER"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
        ],
        "search_fields": ["description"],
    },
    "client_account_record": {
        "entity": "ClientAccountRecord",
        "columns": [
            ("id", "id", "BIGINT"),
            ("client_id", "clientId", "BIGINT"),
            ("paidvalue", "paidvalue", "BIGINT"),
            ("paidstate", "paidstate", "INTEGER"),
            ("paidinfo", "paidinfo", "VARCHAR"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
        ],
        "search_fields": [],
    },
    "client_balance": {
        "entity": "ClientBalance",
        "columns": [
            ("id", "id", "BIGINT"),
            ("client_id", "clientId", "BIGINT"),
            ("current_balance", "currentBalance", "BIGINT"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
        ],
        "search_fields": [],
    },
    "api_gateway_filter": {
        "entity": "ApiGatewayFilter",
        "columns": [
            ("id", "id", "BIGINT"),
            ("filters", "filters", "VARCHAR"),
            ("filter_state", "filterState", "INTEGER"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
            ("extend3", "extend3", "VARCHAR"),
            ("extend4", "extend4", "VARCHAR"),
        ],
        "search_fields": ["filters"],
    },
    "strategy_filter": {
        "entity": "StrategyFilter",
        "columns": [
            ("id", "id", "BIGINT"),
            ("filters", "filters", "VARCHAR"),
            ("filter_state", "filterState", "INTEGER"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
            ("extend3", "extend3", "VARCHAR"),
            ("extend4", "extend4", "VARCHAR"),
        ],
        "search_fields": ["filters"],
    },
    "sms_phase": {
        "entity": "SmsPhase",
        "columns": [
            ("id", "id", "BIGINT"),
            ("phase", "phase", "VARCHAR"),
            ("prov_id", "provId", "BIGINT"),
            ("city_id", "cityId", "BIGINT"),
            ("prov_name", "provName", "VARCHAR"),
            ("city_name", "cityName", "VARCHAR"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
        ],
        "search_fields": ["phase"],
    },
    "activity": {
        "entity": "Activity",
        "columns": [
            ("id", "id", "BIGINT"),
            ("title", "title", "VARCHAR"),
            ("author", "author", "VARCHAR"),
            ("begin_time", "beginTime", "TIMESTAMP"),
            ("end_time", "endTime", "TIMESTAMP"),
            ("link", "link", "VARCHAR"),
            ("cover_pic", "coverPic", "VARCHAR"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
        ],
        "search_fields": ["title"],
    },
    "gray_release": {
        "entity": "GrayRelease",
        "columns": [
            ("id", "id", "BIGINT"),
            ("service_id", "serviceId", "VARCHAR"),
            ("path", "path", "VARCHAR"),
            ("percent", "percent", "INTEGER"),
            ("forward", "forward", "INTEGER"),
            ("state", "state", "INTEGER"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
            ("extend3", "extend3", "VARCHAR"),
            ("extend4", "extend4", "VARCHAR"),
        ],
        "search_fields": ["serviceId"],
    },
    "api_mapping": {
        "entity": "ApiMapping",
        "columns": [
            ("id", "id", "BIGINT"),
            ("source_path", "sourcePath", "VARCHAR"),
            ("target_path", "targetPath", "VARCHAR"),
            ("method", "method", "VARCHAR"),
            ("state", "state", "INTEGER"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
            ("extend3", "extend3", "VARCHAR"),
            ("extend4", "extend4", "VARCHAR"),
        ],
        "search_fields": ["sourcePath"],
    },
    "public_params": {
        "entity": "PublicParams",
        "columns": [
            ("id", "id", "BIGINT"),
            ("param_name", "paramName", "VARCHAR"),
            ("param_type", "paramType", "VARCHAR"),
            ("create_date", "createDate", "TIMESTAMP"),
            ("description", "description", "VARCHAR"),
            ("is_must", "isMust", "INTEGER"),
            ("enable_state", "enableState", "INTEGER"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
            ("extend3", "extend3", "VARCHAR"),
            ("extend4", "extend4", "VARCHAR"),
        ],
        "search_fields": ["paramName"],
    },
    "notify_config": {
        "entity": "NotifyConfig",
        "columns": [
            ("id", "id", "BIGINT"),
            ("tag", "tag", "VARCHAR"),
            ("desp", "desp", "VARCHAR"),
            ("notify_state", "notifyState", "INTEGER"),
            ("cache_state", "cacheState", "INTEGER"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
            ("extend3", "extend3", "VARCHAR"),
            ("extend4", "extend4", "VARCHAR"),
        ],
        "search_fields": ["tag"],
    },
    "search_params": {
        "entity": "SearchParams",
        "columns": [
            ("id", "id", "BIGINT"),
            ("name", "name", "VARCHAR"),
            ("column_name", "columnName", "VARCHAR"),
            ("type", "type", "INTEGER"),
            ("t_order", "tOrder", "INTEGER"),
            ("state", "state", "INTEGER"),
            ("created", "created", "TIMESTAMP"),
            ("create_id", "createId", "BIGINT"),
            ("updated", "updated", "TIMESTAMP"),
            ("update_id", "updateId", "BIGINT"),
            ("is_delete", "isDelete", "TINYINT"),
            ("extend1", "extend1", "VARCHAR"),
            ("extend2", "extend2", "VARCHAR"),
            ("extend3", "extend3", "VARCHAR"),
            ("extend4", "extend4", "VARCHAR"),
        ],
        "search_fields": ["name"],
    },
}


def get_java_type(jdbc_type):
    mapping = {
        "BIGINT": "Long",
        "VARCHAR": "String",
        "INTEGER": "Integer",
        "TINYINT": "Byte",
        "TIMESTAMP": "Date",
    }
    return mapping.get(jdbc_type, "Object")


def generate_example(table_name, entity_name, columns, search_fields):
    """Generate MBG-style Example.java following the ClientBusinessExample pattern."""
    lines = []
    lines.append("package org.example.entity;")
    lines.append("")
    lines.append("import java.util.ArrayList;")
    lines.append("import java.util.Date;")
    lines.append("import java.util.List;")
    lines.append("")
    lines.append("public class " + entity_name + "Example {")
    lines.append("    protected String orderByClause;")
    lines.append("")
    lines.append("    protected boolean distinct;")
    lines.append("")
    lines.append("    protected List<Criteria> oredCriteria;")
    lines.append("")
    lines.append("    public " + entity_name + "Example() {")
    lines.append("        oredCriteria = new ArrayList<>();")
    lines.append("    }")
    lines.append("")
    lines.append("    public void setOrderByClause(String orderByClause) {")
    lines.append("        this.orderByClause = orderByClause;")
    lines.append("    }")
    lines.append("")
    lines.append("    public String getOrderByClause() {")
    lines.append("        return orderByClause;")
    lines.append("    }")
    lines.append("")
    lines.append("    public void setDistinct(boolean distinct) {")
    lines.append("        this.distinct = distinct;")
    lines.append("    }")
    lines.append("")
    lines.append("    public boolean isDistinct() {")
    lines.append("        return distinct;")
    lines.append("    }")
    lines.append("")
    lines.append("    public List<Criteria> getOredCriteria() {")
    lines.append("        return oredCriteria;")
    lines.append("    }")
    lines.append("")
    lines.append("    public void or(Criteria criteria) {")
    lines.append("        oredCriteria.add(criteria);")
    lines.append("    }")
    lines.append("")
    lines.append("    public Criteria or() {")
    lines.append("        Criteria criteria = createCriteriaInternal();")
    lines.append("        oredCriteria.add(criteria);")
    lines.append("        return criteria;")
    lines.append("    }")
    lines.append("")
    lines.append("    public Criteria createCriteria() {")
    lines.append("        Criteria criteria = createCriteriaInternal();")
    lines.append("        if (oredCriteria.size() == 0) {")
    lines.append("            oredCriteria.add(criteria);")
    lines.append("        }")
    lines.append("        return criteria;")
    lines.append("    }")
    lines.append("")
    lines.append("    protected Criteria createCriteriaInternal() {")
    lines.append("        Criteria criteria = new Criteria();")
    lines.append("        return criteria;")
    lines.append("    }")
    lines.append("")
    lines.append("    public void clear() {")
    lines.append("        oredCriteria.clear();")
    lines.append("        orderByClause = null;")
    lines.append("        distinct = false;")
    lines.append("    }")
    lines.append("")
    # GeneratedCriteria
    lines.append("    protected abstract static class GeneratedCriteria {")
    lines.append("        protected List<Criterion> criteria;")
    lines.append("")
    lines.append("        protected GeneratedCriteria() {")
    lines.append("            super();")
    lines.append("            criteria = new ArrayList<>();")
    lines.append("        }")
    lines.append("")
    lines.append("        public boolean isValid() {")
    lines.append("            return criteria.size() > 0;")
    lines.append("        }")
    lines.append("")
    lines.append("        public List<Criterion> getAllCriteria() {")
    lines.append("            return criteria;")
    lines.append("        }")
    lines.append("")
    lines.append("        public List<Criterion> getCriteria() {")
    lines.append("            return criteria;")
    lines.append("        }")
    lines.append("")
    lines.append("        protected void addCriterion(String condition) {")
    lines.append("            if (condition == null) {")
    lines.append("                throw new RuntimeException(\"Value for condition cannot be null\");")
    lines.append("            }")
    lines.append("            criteria.add(new Criterion(condition));")
    lines.append("        }")
    lines.append("")
    lines.append("        protected void addCriterion(String condition, Object value, String property) {")
    lines.append("            if (value == null) {")
    lines.append("                throw new RuntimeException(\"Value for \" + property + \" cannot be null\");")
    lines.append("            }")
    lines.append("            criteria.add(new Criterion(condition, value));")
    lines.append("        }")
    lines.append("")
    lines.append("        protected void addCriterion(String condition, Object value1, Object value2, String property) {")
    lines.append("            if (value1 == null || value2 == null) {")
    lines.append("                throw new RuntimeException(\"Between values for \" + property + \" cannot be null\");")
    lines.append("            }")
    lines.append("            criteria.add(new Criterion(condition, value1, value2));")
    lines.append("        }")
    lines.append("")

    # Generate criteria for id column (BIGINT always)
    lines.append("        public Criteria andIdIsNull() {")
    lines.append("            addCriterion(\"id is null\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Criteria andIdIsNotNull() {")
    lines.append("            addCriterion(\"id is not null\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Criteria andIdEqualTo(Long value) {")
    lines.append("            addCriterion(\"id =\", value, \"id\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Criteria andIdNotEqualTo(Long value) {")
    lines.append("            addCriterion(\"id <>\", value, \"id\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Criteria andIdGreaterThan(Long value) {")
    lines.append("            addCriterion(\"id >\", value, \"id\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Criteria andIdGreaterThanOrEqualTo(Long value) {")
    lines.append("            addCriterion(\"id >=\", value, \"id\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Criteria andIdLessThan(Long value) {")
    lines.append("            addCriterion(\"id <\", value, \"id\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Criteria andIdLessThanOrEqualTo(Long value) {")
    lines.append("            addCriterion(\"id <=\", value, \"id\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Criteria andIdIn(List<Long> values) {")
    lines.append("            addCriterion(\"id in\", values, \"id\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Criteria andIdNotIn(List<Long> values) {")
    lines.append("            addCriterion(\"id not in\", values, \"id\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Criteria andIdBetween(Long value1, Long value2) {")
    lines.append("            addCriterion(\"id between\", value1, value2, \"id\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Criteria andIdNotBetween(Long value1, Long value2) {")
    lines.append("            addCriterion(\"id not between\", value1, value2, \"id\");")
    lines.append("            return (Criteria) this;")
    lines.append("        }")
    lines.append("")

    # Generate criteria for all other columns
    for db_col, java_prop, jdbc_type in columns:
        if java_prop == "id":
            continue
        java_type = get_java_type(jdbc_type)

        # Null/NotNull for all types
        lines.append("        public Criteria and" + java_prop + "IsNull() {")
        lines.append("            addCriterion(\"" + db_col + " is null\");")
        lines.append("            return (Criteria) this;")
        lines.append("        }")
        lines.append("")
        lines.append("        public Criteria and" + java_prop + "IsNotNull() {")
        lines.append("            addCriterion(\"" + db_col + " is not null\");")
        lines.append("            return (Criteria) this;")
        lines.append("        }")
        lines.append("")

        if java_type == "String":
            lines.append("        public Criteria and" + java_prop + "EqualTo(String value) {")
            lines.append("            addCriterion(\"" + db_col + " =\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "NotEqualTo(String value) {")
            lines.append("            addCriterion(\"" + db_col + " <>\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "GreaterThan(String value) {")
            lines.append("            addCriterion(\"" + db_col + " >\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "GreaterThanOrEqualTo(String value) {")
            lines.append("            addCriterion(\"" + db_col + " >=\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "LessThan(String value) {")
            lines.append("            addCriterion(\"" + db_col + " <\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "LessThanOrEqualTo(String value) {")
            lines.append("            addCriterion(\"" + db_col + " <=\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "Like(String value) {")
            lines.append("            addCriterion(\"" + db_col + " like\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "NotLike(String value) {")
            lines.append("            addCriterion(\"" + db_col + " not like\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "In(List<String> values) {")
            lines.append("            addCriterion(\"" + db_col + " in\", values, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "NotIn(List<String> values) {")
            lines.append("            addCriterion(\"" + db_col + " not in\", values, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "Between(String value1, String value2) {")
            lines.append("            addCriterion(\"" + db_col + " between\", value1, value2, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "NotBetween(String value1, String value2) {")
            lines.append("            addCriterion(\"" + db_col + " not between\", value1, value2, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
        elif java_type in ("Long", "Integer", "Byte"):
            lines.append("        public Criteria and" + java_prop + "EqualTo(" + java_type + " value) {")
            lines.append("            addCriterion(\"" + db_col + " =\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "NotEqualTo(" + java_type + " value) {")
            lines.append("            addCriterion(\"" + db_col + " <>\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "GreaterThan(" + java_type + " value) {")
            lines.append("            addCriterion(\"" + db_col + " >\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "GreaterThanOrEqualTo(" + java_type + " value) {")
            lines.append("            addCriterion(\"" + db_col + " >=\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "LessThan(" + java_type + " value) {")
            lines.append("            addCriterion(\"" + db_col + " <\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "LessThanOrEqualTo(" + java_type + " value) {")
            lines.append("            addCriterion(\"" + db_col + " <=\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "In(List<" + java_type + "> values) {")
            lines.append("            addCriterion(\"" + db_col + " in\", values, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "NotIn(List<" + java_type + "> values) {")
            lines.append("            addCriterion(\"" + db_col + " not in\", values, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "Between(" + java_type + " value1, " + java_type + " value2) {")
            lines.append("            addCriterion(\"" + db_col + " between\", value1, value2, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "NotBetween(" + java_type + " value1, " + java_type + " value2) {")
            lines.append("            addCriterion(\"" + db_col + " not between\", value1, value2, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
        elif java_type == "Date":
            lines.append("        public Criteria and" + java_prop + "EqualTo(Date value) {")
            lines.append("            addCriterion(\"" + db_col + " =\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "GreaterThanOrEqualTo(Date value) {")
            lines.append("            addCriterion(\"" + db_col + " >=\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "LessThanOrEqualTo(Date value) {")
            lines.append("            addCriterion(\"" + db_col + " <=\", value, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "Between(Date value1, Date value2) {")
            lines.append("            addCriterion(\"" + db_col + " between\", value1, value2, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")
            lines.append("        public Criteria and" + java_prop + "NotBetween(Date value1, Date value2) {")
            lines.append("            addCriterion(\"" + db_col + " not between\", value1, value2, \"" + java_prop + "\");")
            lines.append("            return (Criteria) this;")
            lines.append("        }")
            lines.append("")

    lines.append("    }")
    lines.append("")
    lines.append("    public static class Criteria extends GeneratedCriteria {")
    lines.append("        protected Criteria() {")
    lines.append("            super();")
    lines.append("        }")
    lines.append("    }")
    lines.append("")
    # Criterion inner class
    lines.append("    public static class Criterion {")
    lines.append("        private String condition;")
    lines.append("")
    lines.append("        private Object value;")
    lines.append("")
    lines.append("        private Object secondValue;")
    lines.append("")
    lines.append("        private boolean noValue;")
    lines.append("")
    lines.append("        private boolean singleValue;")
    lines.append("")
    lines.append("        private boolean betweenValue;")
    lines.append("")
    lines.append("        private boolean listValue;")
    lines.append("")
    lines.append("        private String typeHandler;")
    lines.append("")
    lines.append("        public String getCondition() {")
    lines.append("            return condition;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Object getValue() {")
    lines.append("            return value;")
    lines.append("        }")
    lines.append("")
    lines.append("        public Object getSecondValue() {")
    lines.append("            return secondValue;")
    lines.append("        }")
    lines.append("")
    lines.append("        public boolean isNoValue() {")
    lines.append("            return noValue;")
    lines.append("        }")
    lines.append("")
    lines.append("        public boolean isSingleValue() {")
    lines.append("            return singleValue;")
    lines.append("        }")
    lines.append("")
    lines.append("        public boolean isBetweenValue() {")
    lines.append("            return betweenValue;")
    lines.append("        }")
    lines.append("")
    lines.append("        public boolean isListValue() {")
    lines.append("            return listValue;")
    lines.append("        }")
    lines.append("")
    lines.append("        public String getTypeHandler() {")
    lines.append("            return typeHandler;")
    lines.append("        }")
    lines.append("")
    lines.append("        protected Criterion(String condition) {")
    lines.append("            super();")
    lines.append("            this.condition = condition;")
    lines.append("            this.typeHandler = null;")
    lines.append("            this.noValue = true;")
    lines.append("        }")
    lines.append("")
    lines.append("        protected Criterion(String condition, Object value, String typeHandler) {")
    lines.append("            super();")
    lines.append("            this.condition = condition;")
    lines.append("            this.value = value;")
    lines.append("            this.typeHandler = typeHandler;")
    lines.append("            if (value instanceof List<?>) {")
    lines.append("                this.listValue = true;")
    lines.append("            } else {")
    lines.append("                this.singleValue = true;")
    lines.append("            }")
    lines.append("        }")
    lines.append("")
    lines.append("        protected Criterion(String condition, Object value) {")
    lines.append("            this(condition, value, null);")
    lines.append("        }")
    lines.append("")
    lines.append("        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {")
    lines.append("            super();")
    lines.append("            this.condition = condition;")
    lines.append("            this.value = value;")
    lines.append("            this.secondValue = secondValue;")
    lines.append("            this.typeHandler = typeHandler;")
    lines.append("            this.betweenValue = true;")
    lines.append("        }")
    lines.append("")
    lines.append("        protected Criterion(String condition, Object value, Object secondValue) {")
    lines.append("            this(condition, value, secondValue, null);")
    lines.append("        }")
    lines.append("    }")
    lines.append("}")
    return "\n".join(lines)


def generate_mapper_xml(table_name, entity_name, columns):
    """Generate Mapper XML file following the ClientBusinessMapper.xml pattern.
    Uses plain string concatenation to avoid f-string conflicts with MyBatis #{}/${} syntax."""
    ns = "org.example.mapper." + entity_name + "Mapper"
    entity_type = "org.example.entity." + entity_name
    example_type = "org.example.entity." + entity_name + "Example"

    # Build resultMap
    rm_lines = []
    for i, (db_col, java_prop, jdbc_type) in enumerate(columns):
        tag = "id" if i == 0 else "result"
        rm_lines.append('    <' + tag + ' column="' + db_col + '" jdbcType="' + jdbc_type + '" property="' + java_prop + '" />')

    # Build Base_Column_List - format with line breaks every ~6 columns
    # Each line group ends with comma (except the last group)
    col_strs = [c[0] for c in columns]
    # Split into groups of 6 for readability
    col_groups = []
    for i in range(0, len(col_strs), 6):
        col_groups.append(", ".join(col_strs[i:i+6]))
    if len(col_groups) == 1:
        base_col_list = col_groups[0]
    else:
        # Add comma after each group except the last
        groups_with_comma = [g + "," for g in col_groups[:-1]] + [col_groups[-1]]
        base_col_list = "\n    ".join(groups_with_comma)

    # Build insert columns and values
    # Format: wrap columns at ~3 per line for readability
    ins_col_strs = [c[0] for c in columns]
    ins_val_strs = ["#{" + c[1] + ",jdbcType=" + c[2] + "}" for c in columns]
    # Insert columns formatted in groups of 3
    ins_col_groups = []
    ins_val_groups = []
    for i in range(0, len(ins_col_strs), 3):
        ins_col_groups.append("      " + ", ".join(ins_col_strs[i:i+3]))
        ins_val_groups.append("      " + ", ".join(ins_val_strs[i:i+3]))

    # Build insertSelective - if test blocks
    is_col_blocks = []
    is_val_blocks = []
    for db_col, java_prop, jdbc_type in columns:
        mybatis_val = "#{" + java_prop + ",jdbcType=" + jdbc_type + "}"
        if db_col != java_prop:
            # Column name differs from property name
            is_col_blocks.append("      <if test=\"" + java_prop + " != null\">\n        " + db_col + ",\n      </if>")
        else:
            is_col_blocks.append("      <if test=\"" + java_prop + " != null\">\n        " + db_col + ",\n      </if>")
        is_val_blocks.append("      <if test=\"" + java_prop + " != null\">\n        " + mybatis_val + ",\n      </if>")

    # Build updateByPrimaryKeySelective - if test blocks (skip id)
    ups_blocks = []
    for db_col, java_prop, jdbc_type in columns:
        if java_prop == "id":
            continue
        mybatis_val = "#{" + java_prop + ",jdbcType=" + jdbc_type + "}"
        ups_blocks.append("      <if test=\"" + java_prop + " != null\">\n        " + db_col + " = " + mybatis_val + ",\n      </if>")

    # Build updateByExampleSelective - if test blocks with row. prefix
    uexs_blocks = []
    for db_col, java_prop, jdbc_type in columns:
        mybatis_val = "#{row." + java_prop + ",jdbcType=" + jdbc_type + "}"
        uexs_blocks.append("      <if test=\"row." + java_prop + " != null\">\n        " + db_col + " = " + mybatis_val + ",\n      </if>")

    # Build updateByPrimaryKey (full set clause, skip id)
    # Each assignment line ends with comma (except the last line)
    upk_items = []
    for db_col, java_prop, jdbc_type in columns:
        if java_prop == "id":
            continue
        mybatis_val = "#{" + java_prop + ",jdbcType=" + jdbc_type + "}"
        upk_items.append(db_col + " = " + mybatis_val)
    upk_lines = []
    for i, item in enumerate(upk_items):
        if i < len(upk_items) - 1:
            upk_lines.append("      " + item + ",")
        else:
            upk_lines.append("      " + item)

    # Build updateByExample (full set clause with row. prefix)
    # Each assignment line ends with comma (except the last line)
    uex_items = []
    for db_col, java_prop, jdbc_type in columns:
        mybatis_val = "#{row." + java_prop + ",jdbcType=" + jdbc_type + "}"
        uex_items.append(db_col + " = " + mybatis_val)
    uex_lines = []
    for i, item in enumerate(uex_items):
        if i < len(uex_items) - 1:
            uex_lines.append("      " + item + ",")
        else:
            uex_lines.append("      " + item)

    # Now build the complete XML using plain string concatenation (NO f-strings)
    xml_parts = []
    xml_parts.append('<?xml version="1.0" encoding="UTF-8"?>')
    xml_parts.append('<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">')
    xml_parts.append('<mapper namespace="' + ns + '">')
    xml_parts.append('  <resultMap id="BaseResultMap" type="' + entity_type + '">')
    xml_parts.extend(rm_lines)
    xml_parts.append('  </resultMap>')

    # Example_Where_Clause (static template - no variable substitution needed)
    xml_parts.append('  <sql id="Example_Where_Clause">')
    xml_parts.append('    <where>')
    xml_parts.append('      <foreach collection="oredCriteria" item="criteria" separator="or">')
    xml_parts.append('        <if test="criteria.valid">')
    xml_parts.append('          <trim prefix="(" prefixOverrides="and" suffix=")">')
    xml_parts.append('            <foreach collection="criteria.criteria" item="criterion">')
    xml_parts.append('              <choose>')
    xml_parts.append('                <when test="criterion.noValue">')
    xml_parts.append('                  and ${criterion.condition}')
    xml_parts.append('                </when>')
    xml_parts.append('                <when test="criterion.singleValue">')
    xml_parts.append('                  and ${criterion.condition} #{criterion.value}')
    xml_parts.append('                </when>')
    xml_parts.append('                <when test="criterion.betweenValue">')
    xml_parts.append('                  and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}')
    xml_parts.append('                </when>')
    xml_parts.append('                <when test="criterion.listValue">')
    xml_parts.append('                  and ${criterion.condition}')
    xml_parts.append('                  <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">')
    xml_parts.append('                    #{listItem}')
    xml_parts.append('                  </foreach>')
    xml_parts.append('                </when>')
    xml_parts.append('              </choose>')
    xml_parts.append('            </foreach>')
    xml_parts.append('          </trim>')
    xml_parts.append('        </if>')
    xml_parts.append('      </foreach>')
    xml_parts.append('    </where>')
    xml_parts.append('  </sql>')

    # Update_By_Example_Where_Clause
    xml_parts.append('  <sql id="Update_By_Example_Where_Clause">')
    xml_parts.append('    <where>')
    xml_parts.append('      <foreach collection="example.oredCriteria" item="criteria" separator="or">')
    xml_parts.append('        <if test="criteria.valid">')
    xml_parts.append('          <trim prefix="(" prefixOverrides="and" suffix=")">')
    xml_parts.append('            <foreach collection="criteria.criteria" item="criterion">')
    xml_parts.append('              <choose>')
    xml_parts.append('                <when test="criterion.noValue">')
    xml_parts.append('                  and ${criterion.condition}')
    xml_parts.append('                </when>')
    xml_parts.append('                <when test="criterion.singleValue">')
    xml_parts.append('                  and ${criterion.condition} #{criterion.value}')
    xml_parts.append('                </when>')
    xml_parts.append('                <when test="criterion.betweenValue">')
    xml_parts.append('                  and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}')
    xml_parts.append('                </when>')
    xml_parts.append('                <when test="criterion.listValue">')
    xml_parts.append('                  and ${criterion.condition}')
    xml_parts.append('                  <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">')
    xml_parts.append('                    #{listItem}')
    xml_parts.append('                  </foreach>')
    xml_parts.append('                </when>')
    xml_parts.append('              </choose>')
    xml_parts.append('            </foreach>')
    xml_parts.append('          </trim>')
    xml_parts.append('        </if>')
    xml_parts.append('      </foreach>')
    xml_parts.append('    </where>')
    xml_parts.append('  </sql>')

    # Base_Column_List
    xml_parts.append('  <sql id="Base_Column_List">')
    xml_parts.append('    ' + base_col_list)
    xml_parts.append('  </sql>')

    # selectByExample
    xml_parts.append('  <select id="selectByExample" parameterType="' + example_type + '" resultMap="BaseResultMap">')
    xml_parts.append('    select')
    xml_parts.append('    <if test="distinct">')
    xml_parts.append('      distinct')
    xml_parts.append('    </if>')
    xml_parts.append('    <include refid="Base_Column_List" />')
    xml_parts.append('    from ' + table_name)
    xml_parts.append('    <if test="_parameter != null">')
    xml_parts.append('      <include refid="Example_Where_Clause" />')
    xml_parts.append('    </if>')
    xml_parts.append('    <if test="orderByClause != null">')
    xml_parts.append('      order by ${orderByClause}')
    xml_parts.append('    </if>')
    xml_parts.append('  </select>')

    # selectByPrimaryKey
    xml_parts.append('  <select id="selectByPrimaryKey" parameterType="java.lang.Long" resultMap="BaseResultMap">')
    xml_parts.append('    select')
    xml_parts.append('    <include refid="Base_Column_List" />')
    xml_parts.append('    from ' + table_name)
    xml_parts.append('    where id = #{id,jdbcType=BIGINT}')
    xml_parts.append('  </select>')

    # deleteByPrimaryKey
    xml_parts.append('  <delete id="deleteByPrimaryKey" parameterType="java.lang.Long">')
    xml_parts.append('    delete from ' + table_name)
    xml_parts.append('    where id = #{id,jdbcType=BIGINT}')
    xml_parts.append('  </delete>')

    # deleteByExample
    xml_parts.append('  <delete id="deleteByExample" parameterType="' + example_type + '">')
    xml_parts.append('    delete from ' + table_name)
    xml_parts.append('    <if test="_parameter != null">')
    xml_parts.append('      <include refid="Example_Where_Clause" />')
    xml_parts.append('    </if>')
    xml_parts.append('  </delete>')

    # insert (full)
    xml_parts.append('  <insert id="insert" parameterType="' + entity_type + '">')
    xml_parts.append('    insert into ' + table_name + ' (' + ", ".join(ins_col_strs) + ')')
    xml_parts.append('    values (' + ", ".join(ins_val_strs) + ')')
    xml_parts.append('  </insert>')

    # insertSelective
    xml_parts.append('  <insert id="insertSelective" parameterType="' + entity_type + '">')
    xml_parts.append('    insert into ' + table_name)
    xml_parts.append('    <trim prefix="(" suffix=")" suffixOverrides=",">')
    xml_parts.extend(is_col_blocks)
    xml_parts.append('    </trim>')
    xml_parts.append('    <trim prefix="values (" suffix=")" suffixOverrides=",">')
    xml_parts.extend(is_val_blocks)
    xml_parts.append('    </trim>')
    xml_parts.append('  </insert>')

    # countByExample
    xml_parts.append('  <select id="countByExample" parameterType="' + example_type + '" resultType="java.lang.Long">')
    xml_parts.append('    select count(*) from ' + table_name)
    xml_parts.append('    <if test="_parameter != null">')
    xml_parts.append('      <include refid="Example_Where_Clause" />')
    xml_parts.append('    </if>')
    xml_parts.append('  </select>')

    # updateByExampleSelective
    xml_parts.append('  <update id="updateByExampleSelective" parameterType="map">')
    xml_parts.append('    update ' + table_name)
    xml_parts.append('    <set>')
    xml_parts.extend(uexs_blocks)
    xml_parts.append('    </set>')
    xml_parts.append('    <if test="example != null">')
    xml_parts.append('      <include refid="Update_By_Example_Where_Clause" />')
    xml_parts.append('    </if>')
    xml_parts.append('  </update>')

    # updateByExample (full)
    xml_parts.append('  <update id="updateByExample" parameterType="map">')
    xml_parts.append('    update ' + table_name)
    xml_parts.append('    set')
    xml_parts.extend(uex_lines)
    xml_parts.append('    <if test="example != null">')
    xml_parts.append('      <include refid="Update_By_Example_Where_Clause" />')
    xml_parts.append('    </if>')
    xml_parts.append('  </update>')

    # updateByPrimaryKeySelective
    xml_parts.append('  <update id="updateByPrimaryKeySelective" parameterType="' + entity_type + '">')
    xml_parts.append('    update ' + table_name)
    xml_parts.append('    <set>')
    xml_parts.extend(ups_blocks)
    xml_parts.append('    </set>')
    xml_parts.append('    where id = #{id,jdbcType=BIGINT}')
    xml_parts.append('  </update>')

    # updateByPrimaryKey (full)
    xml_parts.append('  <update id="updateByPrimaryKey" parameterType="' + entity_type + '">')
    xml_parts.append('    update ' + table_name)
    xml_parts.append('    set')
    xml_parts.extend(upk_lines)
    xml_parts.append('    where id = #{id,jdbcType=BIGINT}')
    xml_parts.append('  </update>')

    xml_parts.append('</mapper>')

    return "\n".join(xml_parts)


# Generate all files
for table_name, info in TABLES.items():
    entity_name = info["entity"]
    columns = info["columns"]
    search_fields = info["search_fields"]

    # Generate Example.java
    example_content = generate_example(table_name, entity_name, columns, search_fields)
    example_path = os.path.join(ENTITY_DIR, entity_name + "Example.java")
    with open(example_path, "w", encoding="utf-8") as f:
        f.write(example_content)
    print("Generated: " + example_path)

    # Generate Mapper.xml
    xml_content = generate_mapper_xml(table_name, entity_name, columns)
    xml_path = os.path.join(MAPPER_XML_DIR, entity_name + "Mapper.xml")
    with open(xml_path, "w", encoding="utf-8") as f:
        f.write(xml_content)
    print("Generated: " + xml_path)

print("Done! All Example.java and Mapper.xml files generated.")
