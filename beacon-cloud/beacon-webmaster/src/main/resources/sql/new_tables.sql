-- ============================================
-- beacon-webmaster 新建表 DDL
-- ============================================

-- 1. client（客户基础信息表）
CREATE TABLE client (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    corpname VARCHAR(128) NOT NULL COMMENT '公司名',
    address VARCHAR(255) COMMENT '公司地址',
    linkman VARCHAR(64) COMMENT '联系人',
    mobile VARCHAR(32) COMMENT '联系人手机',
    email VARCHAR(255) COMMENT '邮箱',
    customermanager VARCHAR(64) COMMENT '客户经理',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0 NOT NULL,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255),
    extend3 VARCHAR(255),
    extend4 VARCHAR(255)
) COMMENT='客户基础信息表';

-- 2. api_gateway_filter（过滤器配置）
CREATE TABLE api_gateway_filter (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    filters VARCHAR(500) NOT NULL COMMENT '过滤器配置',
    filter_state INT DEFAULT 0 NOT NULL COMMENT '过滤器状态：0-禁用 1-启用',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0 NOT NULL,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255),
    extend3 VARCHAR(255),
    extend4 VARCHAR(255)
) COMMENT='过滤器配置表';

-- 3. strategy_filter（策略过滤器配置）
CREATE TABLE strategy_filter (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    filters VARCHAR(500) NOT NULL COMMENT '策略过滤器配置',
    filter_state INT DEFAULT 0 NOT NULL COMMENT '过滤器状态：0-禁用 1-启用',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0 NOT NULL,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255),
    extend3 VARCHAR(255),
    extend4 VARCHAR(255)
) COMMENT='策略过滤器配置表';

-- 4. sms_phase（号段配置）
CREATE TABLE sms_phase (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    phase VARCHAR(20) NOT NULL COMMENT '号段',
    prov_id BIGINT NOT NULL COMMENT '省份ID',
    city_id BIGINT NOT NULL COMMENT '城市ID',
    prov_name VARCHAR(50) NOT NULL COMMENT '省份名称',
    city_name VARCHAR(50) NOT NULL COMMENT '城市名称',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0 NOT NULL,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255)
) COMMENT='号段配置表';

-- 5. activity（活动）
CREATE TABLE activity (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL COMMENT '活动标题',
    author VARCHAR(100) NOT NULL COMMENT '作者',
    begin_time TIMESTAMP NOT NULL COMMENT '开始时间',
    end_time TIMESTAMP NOT NULL COMMENT '结束时间',
    link VARCHAR(500) COMMENT '活动链接',
    cover_pic VARCHAR(500) COMMENT '封面图片',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0 NOT NULL,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255)
) COMMENT='活动表';

-- 6. gray_release（灰度发布）
CREATE TABLE gray_release (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    service_id VARCHAR(100) NOT NULL COMMENT '服务ID',
    path VARCHAR(200) NOT NULL COMMENT '路径',
    percent INT DEFAULT 0 NOT NULL COMMENT '灰度百分比',
    forward INT DEFAULT 0 NOT NULL COMMENT '转发标识',
    state INT DEFAULT 0 NOT NULL COMMENT '状态：0-禁用 1-启用',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0 NOT NULL,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255),
    extend3 VARCHAR(255),
    extend4 VARCHAR(255)
) COMMENT='灰度发布表';

-- 7. api_mapping（API映射）
CREATE TABLE api_mapping (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    source_path VARCHAR(200) NOT NULL COMMENT '源路径',
    target_path VARCHAR(200) NOT NULL COMMENT '目标路径',
    method VARCHAR(10) NOT NULL COMMENT '请求方法',
    state INT DEFAULT 0 NOT NULL COMMENT '状态：0-禁用 1-启用',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0 NOT NULL,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255),
    extend3 VARCHAR(255),
    extend4 VARCHAR(255)
) COMMENT='API映射表';

-- 8. public_params（公共参数）
CREATE TABLE public_params (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    param_name VARCHAR(100) NOT NULL COMMENT '参数名称',
    param_type VARCHAR(50) NOT NULL COMMENT '参数类型',
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建日期',
    description VARCHAR(500) COMMENT '参数描述',
    is_must INT DEFAULT 0 NOT NULL COMMENT '是否必填：0-否 1-是',
    enable_state INT DEFAULT 0 NOT NULL COMMENT '启用状态：0-禁用 1-启用',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0 NOT NULL,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255),
    extend3 VARCHAR(255),
    extend4 VARCHAR(255)
) COMMENT='公共参数表';

-- 9. notify_config（通知配置）
CREATE TABLE notify_config (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tag VARCHAR(100) NOT NULL COMMENT '通知标签',
    desp VARCHAR(500) NOT NULL COMMENT '通知描述',
    notify_state INT DEFAULT 0 NOT NULL COMMENT '通知状态：0-禁用 1-启用',
    cache_state INT DEFAULT 0 NOT NULL COMMENT '缓存状态：0-禁用 1-启用',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0 NOT NULL,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255),
    extend3 VARCHAR(255),
    extend4 VARCHAR(255)
) COMMENT='通知配置表';

-- 10. search_params（搜索参数）
CREATE TABLE search_params (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL COMMENT '参数名称',
    column_name VARCHAR(100) NOT NULL COMMENT '列名',
    type INT DEFAULT 0 NOT NULL COMMENT '参数类型',
    t_order INT DEFAULT 0 NOT NULL COMMENT '排序',
    state INT DEFAULT 0 NOT NULL COMMENT '状态：0-禁用 1-启用',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255),
    extend3 VARCHAR(255),
    extend4 VARCHAR(255)
) COMMENT='搜索参数表';

-- ============================================
-- 第二批：5 张新表（修复客户业务/限制管理/短信模板/账户管理 500 错误）
-- 2026-06-29 注意：以下 DDL 是按 Entity 写的，已确认与用户实际表结构不一致
--   - client_business: 用户已有 12. client_balance 字段名是 balance（不是 current_balance）
--   - client_account_record: 用户字段是 paid_value/paid_state/paid_info（不是 paidvalue/paidstate/paidinfo）
--   - code_limit: 用户表无 limit_state 列
--   - sms_temp: 用户表不存在，已建独立文件 sms_temp.sql
-- 所以以下 11-15 段不要执行！保留作历史记录参照
-- ============================================

-- 11. client_business（客户业务接入信息表）
CREATE TABLE client_business (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    corpname VARCHAR(128) NOT NULL COMMENT '公司名',
    apikey VARCHAR(128) COMMENT '接入密钥',
    ip_address VARCHAR(128) COMMENT '接入IP',
    is_callback TINYINT DEFAULT 0 COMMENT '是否返回状态：0-不返回 1-返回',
    callback_url VARCHAR(500) COMMENT '接收状态地址',
    client_linkname VARCHAR(64) COMMENT '客户联系人',
    client_phone VARCHAR(32) COMMENT '客户手机号',
    client_filters VARCHAR(500) COMMENT '客户过滤器',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0,
    email VARCHAR(255) COMMENT '邮箱',
    extend2 VARCHAR(255) COMMENT '关联用户ID(字符串)',
    extend3 VARCHAR(255) COMMENT '优先级',
    extend4 VARCHAR(255) COMMENT '方式(1=http,2=WEB)'
) COMMENT='客户业务接入信息表';

-- 12. client_balance（客户余额表）
CREATE TABLE client_balance (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT NOT NULL COMMENT '客户ID',
    current_balance BIGINT DEFAULT 0 COMMENT '当前余额（分）',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0,
    extend1 VARCHAR(255),
    INDEX idx_client_id (client_id)
) COMMENT='客户余额表';

-- 13. client_account_record（客户账户记录表）
CREATE TABLE client_account_record (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT NOT NULL COMMENT '客户ID',
    paidvalue BIGINT DEFAULT 0 COMMENT '到账金额（分）',
    paidstate INT DEFAULT 0 COMMENT '支付状态',
    paidinfo VARCHAR(500) COMMENT '支付信息',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255),
    INDEX idx_client_id (client_id)
) COMMENT='客户账户记录表';

-- 14. code_limit（验证码/接口限制管理表）
CREATE TABLE code_limit (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    limit_time INT NOT NULL COMMENT '限制时间（秒）',
    limit_count INT NOT NULL COMMENT '限制次数',
    description VARCHAR(500) COMMENT '限制描述',
    limit_state INT DEFAULT 0 NOT NULL COMMENT '启用状态：0-禁用 1-启用',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    create_id BIGINT,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    update_id BIGINT,
    is_delete TINYINT DEFAULT 0,
    extend1 VARCHAR(255),
    extend2 VARCHAR(255)
) COMMENT='接口限制管理表';

-- 15. sms_temp（短信模板表）
CREATE TABLE sms_temp (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    template VARCHAR(500) COMMENT '模板内容',
    paramter VARCHAR(200) COMMENT '参数',
    creater VARCHAR(50) COMMENT '创建者',
    owntype INT DEFAULT 2 COMMENT '创建者类型 1-管理员 2-普通用户',
    status INT DEFAULT 1 COMMENT '状态 0-停用 1-启用',
    created DATETIME COMMENT '创建时间',
    updated DATETIME COMMENT '更新时间'
) COMMENT='短信模板表';
