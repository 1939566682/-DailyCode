-- ============================================================
-- beacon-cloud 菜单修复脚本（v2 — 含测试数据清理）
-- 1. 清理之前错误插入的数据 + 测试垃圾数据
-- 2. 重新插入正确的新功能模块菜单
-- 3. 补全 role_menu 关联
-- ============================================================

-- ==================== 第一步：清理所有错误/测试数据 ====================

-- 删除之前错误插入的菜单数据（ID 20-73）
DELETE FROM sms_menu WHERE id IN (20,21,22,23,24,30,31,32,40,41,42,50,51,52,53,54,60,61,62,70,71,72,73);

-- 删除测试垃圾数据（ID 12-16: test_del_menu_1, test_del_menu_2, 待修复菜单, 测试目录_new, 测试菜单_new）
DELETE FROM sms_menu WHERE id IN (12,13,14,15,16);

-- 删除对应的 role_menu 关联
DELETE FROM sms_role_menu WHERE role_id = 1 AND menu_id IN (12,13,14,15,16,20,21,22,23,24,30,31,32,40,41,42,50,51,52,53,54,60,61,62,70,71,72,73);


-- ==================== 第二步：插入新一级菜单 (type=0) ====================
-- ID 从 100 开始，与已有 ID 1-11 完全不冲突

INSERT INTO sms_menu (id, name, parent_id, url, icon, type, sort, is_delete) VALUES
(100, '客户管理',   0, NULL,           'fa fa-users',       0, 200, 0),
(101, '渠道配置',   0, NULL,           'fa fa-sitemap',     0, 300, 0),
(102, '短信管理',   0, NULL,           'fa fa-envelope-o',  0, 400, 0),
(103, 'API网关',    0, NULL,           'fa fa-exchange',    0, 500, 0),
(104, '统计分析',   0, NULL,           'fa fa-bar-chart-o', 0, 600, 0),
(105, '系统配置',   0, NULL,           'fa fa-cogs',        0, 700, 0);


-- ==================== 第三步：插入新二级菜单 (type=1) ====================

-- 客户管理(100) 子菜单
INSERT INTO sms_menu (id, name, parent_id, url, icon, type, sort, is_delete) VALUES
(200, '客户业务',  100, 'client/clientbusiness.html', 'fa fa-building-o',    1, 201, 0),
(201, '客户信息',  100, 'client/client.html',         'fa fa-user-plus',     1, 202, 0),
(202, '客户渠道',  100, 'client/clientchannel.html',  'fa fa-link',          1, 203, 0),
(203, '短信发送',  100, 'client/smssend.html',        'fa fa-paper-plane-o', 1, 205, 0);

-- 渠道配置(101) 子菜单
INSERT INTO sms_menu (id, name, parent_id, url, icon, type, sort, is_delete) VALUES
(204, '渠道管理',  101, 'channel/channel.html',  'fa fa-arrows',     1, 301, 0),
(205, '阶段管理',  101, 'phase/phase.html',      'fa fa-level-down', 1, 302, 0),
(206, '限制管理',  101, 'limit/limit.html',      'fa fa-ban',        1, 303, 0);

-- 短信管理(102) 子菜单
INSERT INTO sms_menu (id, name, parent_id, url, icon, type, sort, is_delete) VALUES
(207, '活动管理',  102, 'activity/activity.html',  'fa fa-calendar',    1, 401, 0),
(208, '账户管理',  102, 'acount/acount.html',      'fa fa-credit-card', 1, 402, 0),
(209, '短信模板',  102, 'temp/smstemp.html',       'fa fa-file-code-o', 1, 403, 0);

-- API网关(103) 子菜单
INSERT INTO sms_menu (id, name, parent_id, url, icon, type, sort, is_delete) VALUES
(210, 'API映射',      103, 'api/api_mapping.html',        'fa fa-random',   1, 501, 0),
(211, '灰度发布',     103, 'api/api_grayrelease.html',    'fa fa-share-alt', 1, 502, 0),
(212, '公共参数',     103, 'api/public_params.html',      'fa fa-list-alt',  1, 503, 0),
(213, 'API网关过滤',  103, 'filter/apigatewayfilter.html','fa fa-filter',    1, 504, 0),
(214, '策略过滤',     103, 'filter/stragetyfilter.html',  'fa fa-shield',    1, 505, 0);

-- 统计分析(104) 子菜单
INSERT INTO sms_menu (id, name, parent_id, url, icon, type, sort, is_delete) VALUES
(215, '发送统计',  104, 'echarts/smspie.html', 'fa fa-pie-chart',  1, 601, 0),
(216, '柱状图',    104, 'echarts/bar.html',    'fa fa-bar-chart',  1, 602, 0),
(217, '折线图',    104, 'echarts/line.html',   'fa fa-line-chart', 1, 603, 0);

-- 系统配置(105) 子菜单
INSERT INTO sms_menu (id, name, parent_id, url, icon, type, sort, is_delete) VALUES
(218, '通知配置',  105, 'config/notify.html',          'fa fa-bell-o',  1, 701, 0),
(219, '搜索参数',  105, 'config/searchparams.html',    'fa fa-sliders', 1, 702, 0),
(220, '敏感词',    105, 'dirtyword/dirtyword.html',    'fa fa-warning', 1, 703, 0),
(221, '黑名单',    105, 'black/blacklist.html',        'fa fa-ban',     1, 704, 0);


-- ==================== 第四步：角色菜单关联（管理员 role_id=1） ====================

INSERT INTO sms_role_menu (role_id, menu_id, is_delete) VALUES
(1, 100, 0), (1, 200, 0), (1, 201, 0), (1, 202, 0), (1, 203, 0);
INSERT INTO sms_role_menu (role_id, menu_id, is_delete) VALUES
(1, 101, 0), (1, 204, 0), (1, 205, 0), (1, 206, 0);
INSERT INTO sms_role_menu (role_id, menu_id, is_delete) VALUES
(1, 102, 0), (1, 207, 0), (1, 208, 0), (1, 209, 0);
INSERT INTO sms_role_menu (role_id, menu_id, is_delete) VALUES
(1, 103, 0), (1, 210, 0), (1, 211, 0), (1, 212, 0), (1, 213, 0), (1, 214, 0);
INSERT INTO sms_role_menu (role_id, menu_id, is_delete) VALUES
(1, 104, 0), (1, 215, 0), (1, 216, 0), (1, 217, 0);
INSERT INTO sms_role_menu (role_id, menu_id, is_delete) VALUES
(1, 105, 0), (1, 218, 0), (1, 219, 0), (1, 220, 0), (1, 221, 0);
