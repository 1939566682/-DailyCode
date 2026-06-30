-- ============================================
-- sms_temp 建表 DDL（按 beacon-webmaster 的 SmsTemp 实体对齐）
-- 用户数据库无此表，需手动执行此脚本
-- 字段: id, template, paramter, creater, owntype, status, created, updated
-- 用途: 短信模板管理（前端 smstemp.js → /sys/smstemp/*）
-- ============================================
CREATE TABLE IF NOT EXISTS `sms_temp` (
  `id`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template`  VARCHAR(500) DEFAULT NULL COMMENT '模板内容',
  `paramter`  VARCHAR(200) DEFAULT NULL COMMENT '参数',
  `creater`   VARCHAR(50)  DEFAULT NULL COMMENT '创建者',
  `owntype`   INT          DEFAULT 2     COMMENT '创建者类型：1-管理员 / 2-普通用户',
  `status`    INT          DEFAULT 1     COMMENT '状态：0-停用 / 1-启用',
  `created`   DATETIME     DEFAULT NULL COMMENT '创建时间',
  `updated`   DATETIME     DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`),
  KEY `idx_created` (`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='短信模板表';
