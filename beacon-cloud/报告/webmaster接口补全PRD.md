# beacon-webmaster 接口补全 PRD

> 编写日期：2026-07-01  
> 项目：beacon-cloud / beacon-webmaster  
> 编写人：产品经理 许清楚（Xu）

---

## 一、项目信息

| 字段 | 值 |
|------|-----|
| Language | 中文 |
| Programming Language | Java 8 + Spring Boot 2.3.12 + MyBatis + Shiro |
| Project Name | beacon_webmaster_api_completion |
| 原始需求 | 补全 beacon-webmaster 管理后台模块中前端已调用但后端未实现的约 64+ 个业务接口，将接口覆盖率从 8.5% 提升到 100% |

---

## 二、产品定义

### Product Goals

1. **接口覆盖率 100%**：补全前端 26 个业务页面调用的全部后端接口，消除"前端可用、后端空壳"的状态
2. **模式统一、可批量生成**：所有 CRUD 模块遵循统一五件套模式，借助 MyBatis Generator + 代码模板批量产出，降低开发成本
3. **与现有架构无缝衔接**：新增接口严格遵循现有 Controller/Service/Mapper 分层结构和 Shiro 认证体系，不引入额外技术依赖

### User Stories

1. **As a** 平台管理员，**I want** 在管理后台完整使用客户管理页面的增删改查功能，**so that** 我可以自助完成客户接入配置，无需直接操作数据库
2. **As a** 平台管理员，**I want** 黑名单和敏感词管理页面能正常工作，**so that** 我可以及时拦截违规号码和敏感内容，保障短信合规发送
3. **As a** 运维人员，**I want** 通道管理和客户通道分配功能可用，**so that** 我可以灵活配置短信路由通道，避免单通道故障导致业务中断
4. **As a** 平台管理员，**I want** 数据图表页面能展示短信发送统计，**so that** 我可以直观了解平台运营状况并做出决策
5. **As a** 客户，**I want** 通过管理后台修改自己的密码，**so that** 我可以保障账号安全

---

## 三、技术规范

### 3.1 需求池

#### P0 — 核心业务（Must Have）

无这些接口，管理后台核心业务流程完全无法运转。

| # | 模块 | 接口路径前缀 | 接口数 | 说明 |
|---|------|-------------|--------|------|
| 1 | client（客户） | `/sys/client/` | 5 | list / del / info/{id} / save / update |
| 2 | clientbusiness（客户接入） | `/sys/clientbusiness/` | 5 | list / del / info/{id} / save / update（已有 `all`，需补全其余 5 个） |
| 3 | clientchannel（客户通道） | `/sys/clientchannel/` | 5+2辅助 | 五件套 + `/sys/channel/all`（通道下拉） |
| 4 | channel（通道） | `/sys/channel/` | 5+1辅助 | 五件套 + `/sys/channel/all`（所有通道下拉） |

**P0 接口总数：22 个**

#### P1 — 安全合规（Should Have）

无这些接口，短信发送链路的安全校验无法通过管理后台配置，只能手动改数据库/Redis。

| # | 模块 | 接口路径前缀 | 接口数 | 说明 |
|---|------|-------------|--------|------|
| 5 | black（黑名单） | `/sys/black/` | 5 | 五件套 |
| 6 | message/dirtyword（敏感词） | `/sys/message/` | 5 | 五件套（前端路径为 message，实际管理脏词库） |
| 7 | filter/apigatewayfilter（API网关过滤器） | `/sys/apigatewayfilter/` | 5 | 五件套 |
| 8 | filter/stragetyfilter（策略过滤器） | `/sys/stragetyfilter/` | 5 | 五件套 |

**P1 接口总数：20 个**

#### P2 — 运营配置（Should Have）

运营与数据可视化所需。

| # | 模块 | 接口路径前缀 | 接口数 | 说明 |
|---|------|-------------|--------|------|
| 9 | acount（充值记录） | `/sys/acount/` | 5 | 五件套 |
| 10 | phase（号段） | `/sys/phase/` | 5+2辅助 | 五件套 + `/sys/provs/all` + `/sys/citys/all/{provId}` |
| 11 | limit（限流） | `/sys/limit/` | 5 | 五件套 |
| 12 | echarts（图表） | `/sys/echarts/` | 3 | bar / line / pie（非 CRUD，需定制实现） |

**P2 接口总数：20 个**

#### P3 — 系统配置（Could Have）

高级功能与系统配置，不影响核心短信链路运转。

| # | 模块 | 接口路径前缀 | 接口数 | 说明 |
|---|------|-------------|--------|------|
| 13 | grayrelease（灰度发布） | `/sys/grayrelease/` | 5 | 五件套 |
| 14 | apimapping（API映射） | `/sys/apimapping/` | 5 | 五件套 |
| 15 | publicparams（公共参数） | `/sys/publicparams/` | 5 | 五件套 |
| 16 | notify（通知配置） | `/sys/notify/` | 5 | 五件套 |
| 17 | searchparams（搜索参数） | `/sys/searchparams/` | 5 | 五件套 |
| 18 | activity（活动） | `/sys/activity/` | 5+1辅助 | 五件套 + 图片上传（`/ytupload`） |

**P3 接口总数：31 个**

#### 其他 — 非标准 CRUD

| # | 模块 | 接口路径 | 接口数 | 说明 |
|---|------|---------|--------|------|
| 19 | sms（短信发送） | `/sys/sms/save` + `/sys/sms/update` | 2 | 短信发送与更新 |
| 20 | user/password（密码修改） | `/sys/user/password` | 1 | 修改当前用户密码 |

**其他接口总数：3 个**

---

**各优先级接口总数汇总**

| 优先级 | 接口数 |
|--------|--------|
| P0 | 22 |
| P1 | 20 |
| P2 | 20 |
| P3 | 31 |
| 其他 | 3 |
| **合计** | **96** |

> 注：与原始统计"约 64+ 未实现"的差异在于：辅助接口（all/provs/citys）、图表接口、短信/密码接口在原始统计中可能被归入已有接口或未单独计数。本 PRD 以前端页面实际调用的完整清单为准。

---

### 3.2 CRUD 五件套统一接口规范

所有标准 CRUD 模块的接口遵循以下固定模式：

```
GET  /sys/{module}/list       → 列表查询（分页）
POST /sys/{module}/del        → 批量删除
GET  /sys/{module}/info/{id}  → 单条详情
POST /sys/{module}/save       → 新增
POST /sys/{module}/update     → 修改
```

#### 接口详细规范

| 接口 | Method | Path | 入参 | 返回 |
|------|--------|------|------|------|
| **列表** | GET | `/sys/{module}/list` | `offset`（分页偏移）、`limit`（每页条数）、`search`（可选，模糊搜索） | `ResultVO<Object>` → `{total: N, rows: [Entity...]}`（bootstrapTable 格式） |
| **删除** | POST | `/sys/{module}/del` | `Long[] ids`（批量 ID 数组） | `ResultVO<Object>` → `R.ok()` / `R.error()` |
| **详情** | GET | `/sys/{module}/info/{id}` | `@PathVariable Long id` | `ResultVO<Object>` → 单个 Entity JSON |
| **新增** | POST | `/sys/{module}/save` | `@RequestBody Entity` JSON | `ResultVO<Object>` → `R.ok()` / `R.error()` |
| **修改** | POST | `/sys/{module}/update` | `@RequestBody Entity` JSON | `ResultVO<Object>` → `R.ok()` / `R.error()` |

#### 辅助接口规范

| 类型 | Method | Path | 说明 |
|------|--------|------|------|
| **下拉列表（all）** | GET | `/sys/{module}/all` 或 `/sys/{relatedModule}/all` | 返回全部记录（不分页），用于下拉框填充 |
| **级联下拉（省份/城市）** | GET | `/sys/provs/all` | 返回所有省份列表 |
| **级联下拉（城市）** | GET | `/sys/citys/all/{provId}` | 根据省份 ID 返回城市列表 |
| **图表数据** | GET | `/sys/echarts/bar` `/sys/echarts/line` `/sys/echarts/pie` | 返回 ECharts 格式的统计数据 |
| **图片上传** | POST | `/ytupload` | 上传活动图片，返回图片 URL |
| **密码修改** | POST | `/sys/user/password` | 修改当前登录用户密码 |

---

### 3.3 技术实现要求

| 项目 | 要求 |
|------|------|
| **Controller** | `@RestController` + `@RequestMapping("/sys")`，返回 `ResultVO<Object>`，使用 `R.ok()` / `R.error()` |
| **Service** | 接口 + Impl 双层，注入 Mapper |
| **Mapper** | MyBatis Generator 生成，标准 MBG 方法（selectByExample / selectByPrimaryKey / insert / insertSelective / updateByPrimaryKeySelective / deleteByPrimaryKey） |
| **Entity** | MyBatis Generator 生成，包含对应 Example 类 |
| **认证** | 所有接口需经过 Shiro 认证（已在 `ShiroConfig` 中配置 `filterChainDefinitionMap`） |
| **分页** | list 接口使用 bootstrapTable 参数格式（offset + limit），返回 `{total, rows}` 结构 |

#### 可复用资源

| 来源 | 可复用内容 |
|------|-----------|
| **synchronization 模块** | ClientBusiness, ClientChannel, Channel, MobileBlack, MobileDirtyWord, MobileArea 的 Entity + Mapper |
| **webmaster 已有** | ClientBusinessMapper（已有），MyBatis Generator 配置（generatorConfig.xml） |
| **代码生成脚本** | `generate_webmaster_code.py` / `generate_webmaster_code_v2.py`（项目根目录） |

#### 新增模块需创建的全套文件

每个标准 CRUD 模块需新增：
- 1 个 Controller
- 1 个 Service 接口
- 1 个 ServiceImpl
- 1 个 Mapper 接口（若 synchronization 无可复用）
- 1 个 Mapper XML（若 synchronization 无可复用）
- 1 个 Entity 类 + 1 个 Example 类（若 synchronization 无可复用）

---

### 3.4 UI Design Draft（概述）

前端 UI 已全部完成（26 个 HTML 页面），本次需求仅涉及后端接口补全，不涉及前端改动。各页面的 UI 布局和交互逻辑已在现有 HTML 中定义：

- **列表页**：bootstrapTable 分页表格 + 搜索框 + 新增/编辑/删除按钮
- **编辑页**：弹窗表单（layui layer），提交后调用 save/update
- **下拉框**：select2 / layui select，数据源调用 `xxx/all` 辅助接口
- **图表页**：ECharts 容器，数据源调用 bar/line/pie 接口

---

## 四、待确认问题

| # | 问题 | 影响范围 | 建议默认方案 |
|---|------|---------|-------------|
| 1 | **synchronization 模块的 Entity/Mapper 能否被 webmaster 直接引用？** 还是需要复制到 webmaster 模块？ | P0 全部模块 + P1 black/dirtyword | 如果 Maven 依赖允许跨模块引用，直接引用减少重复代码；否则复制到 webmaster |
| 2 | **clientbusiness 的 list 接口与已有的 all 接口功能重叠**，list 带分页 vs all 不分页，是否需要保留 all？ | P0 clientbusiness | 保留 all（前端多处下拉框依赖），list 增加分页查询能力 |
| 3 | **echarts 图表接口的数据来源**：是从 MySQL 聚合查询还是调用 beacon-search 微服务（ES）？ | P2 echarts | 建议调用 beacon-search 的 Feign 接口获取统计数据，与前端 search/list 保持一致 |
| 4 | **sms/save 和 sms/update 的业务含义**：是"发送短信"还是"保存/更新短信模板"？前端 smssend.html 调用此接口 | 其他 sms | 需确认：若为"发送短信"，则应调用 beacon-api 微服务而非本地 DB 操作；若为"短信记录保存"，则直接入库 |
| 5 | **activity 图片上传接口 `/ytupload`**：前端硬编码 `http://localhost:8080/ytupload`，生产部署需改为相对路径，且上传存储方式未定（本地磁盘？OSS？） | P3 activity | 建议改为 `/sys/activity/upload` 相对路径，本地磁盘存储 + 可配置 OSS 方案 |
| 6 | **clientbusiness/pay 充值支付接口**：前端调用 `POST /sys/clientbusiness/pay`（form 表单），这是标准五件套之外的接口，业务逻辑复杂（余额变更 + 流水记录），是否纳入本次补全范围？ | P0 acount / clientbusiness | 建议纳入 acount 模块的 save 接口统一处理充值，pay 接口作为 acount 的增强接口 |
| 7 | **Shiro 权限过滤链配置**：新增 64+ 个接口需要全部配置为 `authc`（需认证）还是按角色细粒度控制？ | 全部接口 | 先统一 `authc`，后续可按角色细化 |
| 8 | **脏词模块前端调用路径为 `/sys/message/*`** 但业务含义是"敏感词管理"，后端模块命名应使用 `message` 还是 `dirtyword`？ | P1 dirtyword | 前端路径不可改，后端 Controller path 必须匹配 `/sys/message/*`，但 Service/Entity 可命名为 DirtyWord 相关 |

---

## 五、交付验收标准

| 标准 | 描述 |
|------|------|
| 接口覆盖率 | 前端 26 个页面调用的全部接口后端均已实现，覆盖率 ≥ 95%（P0+P1 必须 100%） |
| 功能可用性 | 每个接口可通过 Postman/curl 正常调用，返回符合 ResultVO 格式 |
| CRUD 一致性 | 所有标准模块遵循五件套模式，分页参数和返回格式统一 |
| 认证通过 | 所有接口经过 Shiro 认证，未登录调用返回 401 |
| 代码规范 | Controller/Service/Mapper 分层结构一致，命名规范统一 |
