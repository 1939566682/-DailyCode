# beacon-webmaster 前后端接口对比分析

> 分析日期：2026-06-29  
> 前端 HTML 页面：26 个业务页面（不含 UEditor 插件页面）  
> 后端 Controller：4 个，共 6 个接口

---

## 一、后端已实现的接口（6 个）

后端仅有 **4 个 Controller**，共实现 **6 个接口**：

| # | Controller | 接口路径 | 方法 | 功能 |
|---|-----------|---------|------|------|
| 1 | `SmsUserController` | `POST /sys/login` | POST | 用户登录（Shiro 认证 + 验证码校验） |
| 2 | `SmsUserController` | `GET /sys/user/info` | GET | 获取当前登录用户信息（nickname, username） |
| 3 | `SmsUserController` | `GET /sys/menu/user` | GET | 获取当前用户菜单树（基于角色权限） |
| 4 | `ClientBusinessController` | `GET /sys/clientbusiness/all` | GET | 获取所有客户列表（管理员查全部，普通用户查自己） |
| 5 | `SearchController` | `GET /sys/search/list` | GET | 搜索短信记录（调用 SearchClient → beacon-search 微服务） |
| 6 | `KaptchaController` | `GET /captcha.jpg` | GET | 生成验证码图片（Kaptcha + Shiro Session） |

### 后端文件清单

| 类型 | 文件 |
|------|------|
| Controller | `SmsUserController`, `ClientBusinessController`, `SearchController`, `KaptchaController` |
| Service | `ClientBusinessService`, `MenuService`, `SmsRoleService`, `SmsUserService` |
| Service Impl | `ClientBusinessServiceImpl`, `MenuServiceImpl`, `SmsRoleServiceImpl`, `SmsUserServiceImpl` |
| Mapper | `ClientBusinessMapper`, `SmsMenuMapper`, `SmsRoleMapper`, `SmsUserMapper` |
| Entity | `ClientBusiness`, `SmsMenu`, `SmsRole`, `SmsUser`（含 Example 类） |
| VO/DTO | `ClientBusinessVO`, `SearchSmsVO`, `UserDTO`, `ResultVO` |
| Client | `SearchClient`（Feign 调用 beacon-search） |
| Config | `ShiroConfig`, `KaptchaConfig` |
| Realm | `ShiroRealm` |

---

## 二、前端页面调用的接口（70+ 个）

### 2.1 认证 & 通用（✅ 已实现）

| 页面 | 接口 | 方法 | 状态 |
|------|------|------|------|
| login.html | `/sys/login` | POST | ✅ 已实现 |
| login.html | `/captcha.jpg` | GET | ✅ 已实现 |
| index.html | `/sys/menu/user` | GET | ✅ 已实现 |
| index.html | `/sys/user/info` | GET | ✅ 已实现 |
| 多页面共用 | `/sys/clientbusiness/all` | GET | ✅ 已实现 |
| client/search.html | `/sys/search/list` | GET | ✅ 已实现 |

### 2.2 未实现的接口（❌ 按模块分组）

#### 客户模块（client）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| client/client.html | `/sys/client/list` | GET | 客户列表 | ❌ 未实现 |
| client/client.html | `/sys/client/del` | POST | 删除客户 | ❌ 未实现 |
| client/client.html | `/sys/client/info/{id}` | GET | 客户详情 | ❌ 未实现 |
| client/client.html | `/sys/client/save` | POST | 新增客户 | ❌ 未实现 |
| client/client.html | `/sys/client/update` | POST | 修改客户 | ❌ 未实现 |
| client/clientbusiness.html | `/sys/clientbusiness/list` | GET | 客户接入列表 | ❌ 未实现 |
| client/clientbusiness.html | `/sys/clientbusiness/del` | POST | 删除客户接入 | ❌ 未实现 |
| client/clientbusiness.html | `/sys/clientbusiness/info/{id}` | GET | 客户接入详情 | ❌ 未实现 |
| client/clientbusiness.html | `/sys/clientbusiness/save` | POST | 新增客户接入 | ❌ 未实现 |
| client/clientbusiness.html | `/sys/clientbusiness/update` | POST | 修改客户接入 | ❌ 未实现 |
| client/clientchannel.html | `/sys/clientchannel/list` | GET | 客户通道列表 | ❌ 未实现 |
| client/clientchannel.html | `/sys/clientchannel/del` | POST | 删除客户通道 | ❌ 未实现 |
| client/clientchannel.html | `/sys/channel/all` | GET | 所有通道列表 | ❌ 未实现 |
| client/clientchannel.html | `/sys/clientchannel/info/{id}` | GET | 客户通道详情 | ❌ 未实现 |
| client/clientchannel.html | `/sys/clientchannel/save` | POST | 新增客户通道 | ❌ 未实现 |
| client/clientchannel.html | `/sys/clientchannel/update` | POST | 修改客户通道 | ❌ 未实现 |
| client/smssend.html | `/sys/sms/save` | POST | 发送短信 | ❌ 未实现 |
| client/smssend.html | `/sys/sms/update` | POST | 更新短信 | ❌ 未实现 |
| client/userpay.html | `/sys/clientbusiness/pay` | POST(form) | 客户充值支付 | ❌ 未实现 |

#### 通道模块（channel）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| channel/channel.html | `/sys/channel/list` | GET | 通道列表 | ❌ 未实现 |
| channel/channel.html | `/sys/channel/del` | POST | 删除通道 | ❌ 未实现 |
| channel/channel.html | `/sys/channel/info/{id}` | GET | 通道详情 | ❌ 未实现 |
| channel/channel.html | `/sys/channel/save` | POST | 新增通道 | ❌ 未实现 |
| channel/channel.html | `/sys/channel/update` | POST | 修改通道 | ❌ 未实现 |

#### 黑名单模块（black）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| black/blacklist.html | `/sys/black/list` | GET | 黑名单列表 | ❌ 未实现 |
| black/blacklist.html | `/sys/black/del` | POST | 删除黑名单 | ❌ 未实现 |
| black/blacklist.html | `/sys/black/info/{id}` | GET | 黑名单详情 | ❌ 未实现 |
| black/blacklist.html | `/sys/black/save` | POST | 新增黑名单 | ❌ 未实现 |
| black/blacklist.html | `/sys/black/update` | POST | 修改黑名单 | ❌ 未实现 |

#### 敏感词模块（dirtyword）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| dirtyword/dirtyword.html | `/sys/message/list` | GET | 敏感词列表 | ❌ 未实现 |
| dirtyword/dirtyword.html | `/sys/message/del` | POST | 删除敏感词 | ❌ 未实现 |
| dirtyword/dirtyword.html | `/sys/message/info/{id}` | GET | 敏感词详情 | ❌ 未实现 |
| dirtyword/dirtyword.html | `/sys/message/save` | POST | 新增敏感词 | ❌ 未实现 |
| dirtyword/dirtyword.html | `/sys/message/update` | POST | 修改敏感词 | ❌ 未实现 |

#### 号段模块（phase）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| phase/phase.html | `/sys/phase/list` | GET | 号段列表 | ❌ 未实现 |
| phase/phase.html | `/sys/phase/del` | POST | 删除号段 | ❌ 未实现 |
| phase/phase.html | `/sys/phase/info/{id}` | GET | 号段详情 | ❌ 未实现 |
| phase/phase.html | `/sys/phase/save` | POST | 新增号段 | ❌ 未实现 |
| phase/phase.html | `/sys/phase/update` | POST | 修改号段 | ❌ 未实现 |
| phase/phase.html | `/sys/provs/all` | GET | 所有省份列表 | ❌ 未实现 |
| phase/phase.html | `/sys/citys/all/{provId}` | GET | 省份下城市列表 | ❌ 未实现 |

#### 限流模块（limit）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| limit/limit.html | `/sys/limit/list` | GET | 限流列表 | ❌ 未实现 |
| limit/limit.html | `/sys/limit/del` | POST | 删除限流 | ❌ 未实现 |
| limit/limit.html | `/sys/limit/info/{id}` | GET | 限流详情 | ❌ 未实现 |
| limit/limit.html | `/sys/limit/save` | POST | 新增限流 | ❌ 未实现 |
| limit/limit.html | `/sys/limit/update` | POST | 修改限流 | ❌ 未实现 |

#### 过滤器配置模块（filter）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| filter/apigatewayfilter.html | `/sys/apigatewayfilter/list` | GET | API网关过滤器列表 | ❌ 未实现 |
| filter/apigatewayfilter.html | `/sys/apigatewayfilter/del` | POST | 删除过滤器 | ❌ 未实现 |
| filter/apigatewayfilter.html | `/sys/apigatewayfilter/info/{id}` | GET | 过滤器详情 | ❌ 未实现 |
| filter/apigatewayfilter.html | `/sys/apigatewayfilter/save` | POST | 新增过滤器 | ❌ 未实现 |
| filter/apigatewayfilter.html | `/sys/apigatewayfilter/update` | POST | 修改过滤器 | ❌ 未实现 |
| filter/stragetyfilter.html | `/sys/stragetyfilter/list` | GET | 策略过滤器列表 | ❌ 未实现 |
| filter/stragetyfilter.html | `/sys/stragetyfilter/del` | POST | 删除过滤器 | ❌ 未实现 |
| filter/stragetyfilter.html | `/sys/stragetyfilter/info/{id}` | GET | 过滤器详情 | ❌ 未实现 |
| filter/stragetyfilter.html | `/sys/stragetyfilter/save` | POST | 新增过滤器 | ❌ 未实现 |
| filter/stragetyfilter.html | `/sys/stragetyfilter/update` | POST | 修改过滤器 | ❌ 未实现 |

#### 充值/账户模块（acount）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| acount/acount.html | `/sys/acount/list` | GET | 充值记录列表 | ❌ 未实现 |
| acount/acount.html | `/sys/acount/del` | POST | 删除充值记录 | ❌ 未实现 |
| acount/acount.html | `/sys/acount/info/{id}` | GET | 充值记录详情 | ❌ 未实现 |
| acount/acount.html | `/sys/acount/save` | POST | 新增充值记录 | ❌ 未实现 |
| acount/acount.html | `/sys/acount/update` | POST | 修改充值记录 | ❌ 未实现 |

#### 活动模块（activity）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| activity/activity.html | `/sys/activity/list` | GET | 活动列表 | ❌ 未实现 |
| activity/activity.html | `/sys/activity/del` | POST | 删除活动 | ❌ 未实现 |
| activity/activity.html | `/sys/activity/info/{id}` | GET | 活动详情 | ❌ 未实现 |
| activity/activity.html | `/sys/activity/save` | POST | 新增活动 | ❌ 未实现 |
| activity/activity.html | `/sys/activity/update` | POST | 修改活动 | ❌ 未实现 |

#### API配置模块（api）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| api/api_grayrelease.html | `/sys/grayrelease/list` | GET | 灰度发布列表 | ❌ 未实现 |
| api/api_grayrelease.html | `/sys/grayrelease/del` | POST | 删除灰度配置 | ❌ 未实现 |
| api/api_grayrelease.html | `/sys/grayrelease/info/{id}` | GET | 灰度配置详情 | ❌ 未实现 |
| api/api_grayrelease.html | `/sys/grayrelease/save` | POST | 新增灰度配置 | ❌ 未实现 |
| api/api_grayrelease.html | `/sys/grayrelease/update` | POST | 修改灰度配置 | ❌ 未实现 |
| api/api_mapping.html | `/sys/apimapping/list` | GET | API映射列表 | ❌ 未实现 |
| api/api_mapping.html | `/sys/apimapping/del` | POST | 删除映射 | ❌ 未实现 |
| api/api_mapping.html | `/sys/apimapping/info/{id}` | GET | 映射详情 | ❌ 未实现 |
| api/api_mapping.html | `/sys/apimapping/save` | POST | 新增映射 | ❌ 未实现 |
| api/api_mapping.html | `/sys/apimapping/update` | POST | 修改映射 | ❌ 未实现 |
| api/public_params.html | `/sys/publicparams/list` | GET | 公共参数列表 | ❌ 未实现 |
| api/public_params.html | `/sys/publicparams/del` | POST | 删除参数 | ❌ 未实现 |
| api/public_params.html | `/sys/publicparams/info/{id}` | GET | 参数详情 | ❌ 未实现 |
| api/public_params.html | `/sys/publicparams/save` | POST | 新增参数 | ❌ 未实现 |
| api/public_params.html | `/sys/publicparams/update` | POST | 修改参数 | ❌ 未实现 |

#### 配置模块（config）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| config/notify.html | `/sys/notify/list` | GET | 通知配置列表 | ❌ 未实现 |
| config/notify.html | `/sys/notify/del` | POST | 删除通知 | ❌ 未实现 |
| config/notify.html | `/sys/notify/info/{id}` | GET | 通知详情 | ❌ 未实现 |
| config/notify.html | `/sys/notify/save` | POST | 新增通知 | ❌ 未实现 |
| config/notify.html | `/sys/notify/update` | POST | 修改通知 | ❌ 未实现 |
| config/searchparams.html | `/sys/searchparams/list` | GET | 搜索参数列表 | ❌ 未实现 |
| config/searchparams.html | `/sys/searchparams/del` | POST | 删除参数 | ❌ 未实现 |
| config/searchparams.html | `/sys/searchparams/info/{id}` | GET | 参数详情 | ❌ 未实现 |
| config/searchparams.html | `/sys/searchparams/save` | POST | 新增参数 | ❌ 未实现 |
| config/searchparams.html | `/sys/searchparams/update` | POST | 修改参数 | ❌ 未实现 |

#### 图表模块（echarts）

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| echarts/bar.html | `/sys/echarts/bar` | GET | 柱状图数据 | ❌ 未实现 |
| echarts/line.html | `/sys/echarts/line` | GET | 折线图数据 | ❌ 未实现 |
| echarts/pie.html | `/sys/echarts/pie` | GET | 饼图数据 | ❌ 未实现 |
| echarts/smspie.html | `/sys/echarts/pie` (带参数) | GET | 短信成功率饼图 | ❌ 未实现 |

#### 其他

| 页面 | 接口 | 方法 | 功能 | 状态 |
|------|------|------|------|------|
| index.html | `/sys/user/password` | POST | 修改密码 | ❌ 未实现 |
| activity/activity.html | `http://localhost:8080/ytupload` | POST | 图片上传 | ❌ 未实现（且硬编码 localhost） |

---

## 三、统计汇总

| 维度 | 数量 |
|------|------|
| 前端业务 HTML 页面 | 26 个 |
| 前端调用的接口总数 | 约 70+ 个 |
| 后端已实现的接口 | **6 个** |
| 未实现的接口 | **约 64+ 个** |
| 后端实现的覆盖率 | **约 8.5%** |

### 未实现的接口按 CRUD 模式分类

所有未实现的接口都遵循 **标准 CRUD 五件套** 模式：

```
GET  /sys/{module}/list       → 列表（bootstrapTable 分页）
POST /sys/{module}/del        → 批量删除（id 数组）
GET  /sys/{module}/info/{id}  → 详情（单个查询）
POST /sys/{module}/save       → 新增
POST /sys/{module}/update     → 修改
```

外加一些辅助接口（下拉列表、图表数据、文件上传等）。

### 需要新增的后端模块预估

| 业务模块 | 需要的接口数 | 需新增的 Entity/Mapper/Service/Controller |
|---------|------------|------------------------------------------|
| client（客户） | 5 | 完整 CRUD |
| clientbusiness（客户接入） | 5 | 部分已有（仅 all），缺 list/del/info/save/update |
| clientchannel（客户通道） | 5+2辅助 | 完整 CRUD + channel/all 辅助 |
| channel（通道） | 5+1辅助 | 完整 CRUD + all 辅助 |
| black（黑名单） | 5 | 完整 CRUD |
| message（敏感词） | 5 | 完整 CRUD |
| phase（号段） | 5+2辅助 | 完整 CRUD + provs/all + citys/all/{provId} |
| limit（限流） | 5 | 完整 CRUD |
| apigatewayfilter（API网关过滤器） | 5 | 完整 CRUD |
| stragetyfilter（策略过滤器） | 5 | 完整 CRUD |
| acount（充值） | 5 | 完整 CRUD |
| activity（活动） | 5+1上传 | 完整 CRUD + 图片上传 |
| grayrelease（灰度发布） | 5 | 完整 CRUD |
| apimapping（API映射） | 5 | 完整 CRUD |
| publicparams（公共参数） | 5 | 完整 CRUD |
| notify（通知配置） | 5 | 完整 CRUD |
| searchparams（搜索参数） | 5 | 完整 CRUD |
| echarts（图表） | 3 | bar/line/pie 数据接口 |
| sms（短信发送） | 2 | save/update |
| user（密码修改） | 1 | password |

---

## 四、关键发现

1. **前端已完成、后端严重缺失**：前端 26 个页面的 UI 和 JS 交互逻辑全部写完，但后端只实现了认证相关的 6 个接口，业务管理接口覆盖率仅 **8.5%**
2. **所有 CRUD 接口模式统一**：前端遵循固定的 5 接口 CRUD 模式（list/del/info/save/update），可以用代码生成器批量生成
3. **项目根目录有代码生成脚本**：`generate_webmaster_code.py` / `generate_webmaster_code_v2.py` 正是为了批量生成这些 Controller/Service/Mapper 而写的，说明设计意图就是通过生成器补全后端
4. **硬编码 localhost**：`activity.html` 的图片上传接口写死了 `http://localhost:8080/ytupload`，生产部署会有问题
5. **已有 Entity/Mapper 可复用**：`synchronization` 模块已有 ClientBusiness/ClientSign/ClientTemplate/ClientBalance/ClientChannel/Channel/MobileArea/MobileBlack/MobileTransfer/MobileDirtyWord 的 Entity 和 Mapper，webmaster 可以直接引用或复制

---

## 五、优先级建议（如果要补全后端）

| 优先级 | 模块 | 原因 |
|--------|------|------|
| P0 | clientbusiness（补全） | 已有 all，核心业务最需要 |
| P0 | client（客户 CRUD） | 客户管理是核心 |
| P0 | channel + clientchannel | 短信通道配置直接影响业务 |
| P1 | black（黑名单） | 直接影响短信能否发送 |
| P1 | dirtyword（敏感词） | 内容安全合规 |
| P1 | filter（过滤器配置） | 动态校验链配置 |
| P2 | acount（充值） | 计费相关 |
| P2 | phase（号段） | 运营商路由 |
| P2 | limit（限流） | 频次控制 |
| P2 | echarts（图表） | 数据可视化 |
| P3 | api 配置模块 | 灰度发布等高级功能 |
| P3 | notify/searchparams/config | 系统配置 |
| P3 | activity | 营销活动管理 |
