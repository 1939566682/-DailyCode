# beacon-cloud 项目长期记忆

## 项目基本信息
- 定位：Spring Cloud 微服务架构的分布式短信平台
- 技术栈：Spring Boot 2.3.12 + Spring Cloud Hoxton.SR12 + Alibaba 2.2.6 + Nacos + RabbitMQ + Redis + MySQL + Elasticsearch + XXL-Job + Netty(CMPP) + Java 8 (Corretto)
- 10 个 Maven 子模块

## 代码规范
- Entity：不用 Lombok，手写 getter/setter（MBG 风格）
- VO：用 Lombok @Data
- Controller：用 @Slf4j + R.ok()/R.error()/R.okNamed() 响应
- 前端字段拼写错误需保留：despcription/descripton/cloum/acount/stragetyfilter/garyrelease
- Example Criteria 方法命名：首字母小写（andcorpnameLike，不是 andCorpnameLike）
- PageHelper 分页：offsetPage(offset, limit) + new PageInfo<>(list).getTotal()（不是 PageHelper.getTotal()）
- CRUD 五件套：list/del/info/save/update
- **info/all 接口响应格式**：用 R.okNamed("key", value) 返回前端期望的命名字段，不用 R.ok(data)
- **list 接口响应格式**：用 R.ok(total, rows) 返回 {code, msg, total, rows}，bootstrapTable server pagination 期望此格式
- **echarts/pie 响应格式**：返回 {code, msg, legendData, seriesData}，前端 smspie.js 用 r.legendData/r.seriesData

## Shiro 认证
- ShiroConfig: /public/** anon, /sys/login anon, /** authc
- 所有 /sys/* 接口需要登录认证（authc filter）
- 未登录访问 /sys/* → 302重定向到 /login.html → AJAX请求会表现为404
- **测试API前必须先登录！**

## 前端JS响应字段名映射表
| 前端JS | 接口路径 | 期望字段名 | 后端返回 |
|--------|---------|-----------|---------|
| acount.js | /sys/acount/info/{id} | r.acount | R.okNamed("acount") |
| activity.js | /sys/activity/info/{id} | r.activity | R.okNamed("activity") |
| api.js | /sys/apimapping/info/{id} | r.apimapping | R.okNamed("apimapping") |
| garyrelease.js | /sys/grayrelease/info/{id} | r.grayrelease | R.okNamed("grayrelease") |
| public_params.js | /sys/publicparams/info/{id} | r.param | R.okNamed("param") |
| blacklist.js | /sys/black/info/{id} | r.black | R.okNamed("black") |
| channel.js | /sys/channel/info/{id} | r.channel | R.okNamed("channel") |
| channel.js | /sys/channel/all | r.channelsites | R.okNamed("channelsites") |
| client.js | /sys/client/info/{id} | r.client | R.okNamed("client") |
| clientbusiness.js | /sys/clientbusiness/info/{id} | r.clientbusiness | R.okNamed("clientbusiness") |
| clientbusiness.js | /sys/clientbusiness/all | r.sites | R.okNamed("sites") |
| clientchannel.js | /sys/clientchannel/info/{id} | r.clientchannel | R.okNamed("clientchannel") |
| apigatewayfilter.js | /sys/apigatewayfilter/info/{id} | r.filter | R.okNamed("filter") |
| stragetyfilter.js | /sys/stragetyfilter/info/{id} | r.filter | R.okNamed("filter") |
| limit.js | /sys/limit/info/{id} | r.limit | R.okNamed("limit") |
| notify.js | /sys/notify/info/{id} | r.notify | R.okNamed("notify") |
| searchparams.js | /sys/searchparams/info/{id} | r.searchparams | R.okNamed("searchparams") |
| dirtyword.js | /sys/dirtyword/info/{id} | r.message | R.okNamed("message") |
| phase.js | /sys/phase/info/{id} | r.phase | R.okNamed("phase") |
| phase.js | /sys/phase/provs/all | r.sites | R.okNamed("sites") |
| phase.js | /sys/phase/citys/all/{provId} | r.citys | R.okNamed("citys") |
| user.js | /sys/user/info/{userId} | r.user | R.okNamed("user") |
| index.js | /sys/user/info | r.user | R.okNamed("user") (已改前端) |
| index.js | /sys/menu/user | r.data | R.ok(data) → data字段 |
| menu.js | /sys/menu/info/{id} | r.menu | R.okNamed("menu") |
| menu.js | /sys/menu/select | r.menuList | R.okNamed("menuList") |
| smspie.js | /sys/echarts/pie | r.legendData/r.seriesData | Map{legendData,seriesData} |

## Maven 编译注意
- **Lombok 注解处理器必须在 maven-compiler-plugin 的 annotationProcessorPaths 中显式声明**
- beacon-webmaster pom.xml 已添加 Lombok annotationProcessorPaths（版本 1.18.20）
## 数据库注意事项
- rbac 五表实际数据比 rbac.sql 的 INSERT 多：ID 9=短信信息管理, 10=短信搜索, 11=短信统计 + 测试数据(12-16)
- 新增菜单必须用 ID 100+ 和 200+，避免与已有 1-16 冲突
- sms_user: admin(id=1, nickname=落雨), test(id=2, nickname=测试)
- sms_role: 管理员(id=1), 测试(id=2)
- 不要未经用户许可运行 Maven install/命令
- beacon-common 的 R.java 修改后需先重新编译 beacon-common 再编译 beacon-webmaster

## Entity-DB 字段对齐（重要！）
- DB 表是预先存在的（从 beacon-synchronization 同步过来），Java Entity 必须适配 DB 列名
- 不适配会让 `selectByExample` 报 `Unknown column 'xxx' in 'field list'` → 500
- 已修复的字段映射（不要回退）：
  - **ClientBalance**: `currentBalance` → `balance`（DB 列名就是 balance，无 current_ 前缀）
  - **CodeLimit**: 删除 `limitState` 字段（DB code_limit 表无此列，VO 上保留作虚拟字段默认 1）
  - **ClientAccountRecord**: `paidvalue/paidstate/paidinfo` → `paidValue/paidState/paidInfo`（Java 驼峰）→ DB `paid_value/paid_state/paid_info`（下划线）
- 修改 MBG 生成的 Example 类时：批量删除 `andxxx*` 方法后要检查方法体 `return (Criteria) this;` 和 `}` 是否清干净，否则编译报"需要class, interface或enum"
- **sms_temp 表**（用户DB中不存在）：DML 文件在 `beacon-webmaster/src/main/resources/sql/sms_temp.sql`，需用户手动执行

## 文件命名
- Java 公共类名必须与文件名完全一致（大小写敏感）
- PublicParamsServiceImpl.java（不是 PublicparamsServiceImpl.java）

## 已删除的死文件（不要重建）
- `service/LimitService.java`、`vo/LimitVO.java`、`service/impl/LimitServiceImpl.java` — 旧实现，已被 `CodeLimitService/CodeLimitVO/CodeLimitServiceImpl` 取代
- `service/AcountService.java` — 旧接口，已被 `ClientAccountRecordService` 取代

## 缺失的Controller（需要额外Service/Mapper/Entity/数据库表）
- RoleController — role.js 用 /system/role/* 前缀，涉及 sms_role_menu/sms_user_role 关联表
- SmsTempController — smstemp.js 用 /sys/smstemp/* 系列6个接口，需要 sms_template 表
- SmsSendController — smssend.js 用 /sys/sms/save 和 /sys/sms/update，webmaster内短信发送功能

## Maven 编译小贴士
- Git Bash 调 `mvn` 会找不到 plexus launcher，**必须用 `mvn.cmd` 后缀**：`"D:/develop/02-Lang/Maven/apache-maven-3.9.14/bin/mvn.cmd" -pl beacon-webmaster -am compile -DskipTests`
