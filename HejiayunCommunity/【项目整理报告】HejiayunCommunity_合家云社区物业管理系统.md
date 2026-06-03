# HejiayunCommunity 合家云社区物业管理平台 — 完整项目整理报告

> **作者：** Yang QingBo（杨清波）
> **项目路径：** `E:\idea_workscope\HejiayunCommunity`
> **后端启动：** `hjy-community` → `mvn spring-boot:run` → 8080 端口
> **前端启动：** `hejiayun_ui` → `npm run dev` → 80 端口
> **浏览器打开：** `http://localhost`

---

## 目录

1. [项目整体架构](#一项目整体架构)
2. [技术栈总览](#二技术栈总览)
3. [前后端对接说明](#三前后端对接说明)
4. [主工程 hjy-community 详解](#四主工程-hjy-community-详解)
5. [完整 API 接口文档](#五完整-api-接口文档)
6. [前端项目 hejiayun_ui 详解](#六前端项目-hejiayun_ui-详解)
7. [数据库设计](#七数据库设计)
8. [核心请求流程（完整链路）](#八核心请求流程完整链路)
9. [子模块说明](#九子模块说明)
10. [学习路径推断](#十学习路径推断)
11. [亮点与改进建议](#十一亮点与改进建议)
12. [快速启动与演示指南](#十二快速启动与演示指南)

---

## 一、项目整体架构

### 1.1 项目结构

```
E:\idea_workscope\HejiayunCommunity\
├── hjy-community/                ★★ 后端 Spring Boot 物业管理系统
├── hejiayun_ui/                  ★★ 前端 Vue + Element UI 管理后台
├── springsecurity_example/       练习① Spring Security + JWT
├── springsecurity_example2/      练习② Spring Security + 验证码+表单
├── easypoi_boot/                 练习③ EasyPOI Excel
├── easy_code/                    练习④ 代码生成器
├── HejiayunCommunity.iml         IDEA 项目文件
├── 【项目整理报告】...md            本文档
└── 【课堂演讲稿】...md              讲解稿
```

### 1.2 架构模式：前后端分离

```
┌─── 浏览器 ──────────────────────────────────────┐
│  http://localhost                                │
│  (Vue 2 + Element UI 管理后台)                   │
└─────────────────┬────────────────────────────────┘
                  │ 代理 /hejiayun/* → localhost:8080
                  ▼
┌─── Spring Boot 后端 ─────────────────────────────┐
│  http://localhost:8080                            │
│  (Spring Security + MyBatis-Plus + Redis)         │
└─────────────────┬────────────────────────────────┘
                  │
        ┌────────┴────────┐
        ▼                 ▼
┌─── MySQL ───┐   ┌─── Redis ─────┐
│ 数据库      │   │ Token/缓存    │
│ hejiayun_   │   │ localhost:6379│
│ community   │   └───────────────┘
└─────────────┘
```

---

## 二、技术栈总览

### 后端（hjy-community）

| 技术 | 版本 | 用途 |
|------|------|------|
| Spring Boot | 2.7.8 | 核心框架 |
| Spring Security | (2.7.8) | 认证授权 |
| MyBatis-Plus | 3.4.1 | ORM 框架 |
| MySQL + Druid | 8.0.32 / 1.2.2 | 数据库 + 连接池 |
| Redis + Jedis | (默认) | 缓存/Token 存储 |
| JJWT | 0.9.1 | JWT Token |
| EasyPOI | 4.2.0 | Excel 导入导出 |
| PageHelper | 1.4.1 | 分页插件 |
| Lombok | (provided) | 代码简化 |
| FastJSON | 1.2.74 | JSON 处理 |
| EasyCaptcha | 1.6.2 | 图形验证码 |
| Orika | 1.5.4 | 对象拷贝 |

### 前端（hejiayun_ui）

| 技术 | 版本 | 用途 |
|------|------|------|
| Vue | 2.6.10 | 前端框架 |
| Element UI | 2.14.1 | UI 组件库 |
| Vue Router | 3.0.2 | 前端路由 |
| Vuex | 3.1.0 | 全局状态管理 |
| Axios | 0.18.1 | HTTP 请求 |
| ECharts | 4.2.1 | 首页仪表盘图表 |
| js-cookie | 2.2.0 | Cookie 存储（存 Token） |
| jsencrypt | 3.0.0 | 密码 RSA 加密 |
| nprogress | 0.2.0 | 页面进度条 |
| vue-count-to | 1.0.13 | 数字滚动动画 |

### 开发工具

| 工具 | 用途 |
|:----|:------|
| IntelliJ IDEA | 后端开发 |
| VS Code / WebStorm | 前端开发 |
| Maven | 后端构建 |
| npm / Vue CLI 4 | 前端构建 |
| MySQL 8.0 + Redis | 数据存储 |
| Postman / 浏览器 | API 测试 |

---

## 三、前后端对接说明

### 3.1 代理配置（vue.config.js）

```javascript
devServer: {
  port: 80,
  proxy: {
    '/hejiayun': {                          // 前端所有 /hejiayun 开头的请求
      target: 'http://localhost:8080',      // 转发到后端 8080
      changeOrigin: true,
      pathRewrite: { '^/hejiayun': '' }    // 去掉 /hejiayun 前缀
    }
  }
}
```

### 3.2 请求映射关系

```
前端 Axios 请求                     →  后端实际地址（去掉 /hejiayun）
GET  /hejiayun/captchaImage        →  GET  /captchaImage
POST /hejiayun/login               →  POST /login
GET  /hejiayun/community/list      →  GET  /community/list
GET  /hejiayun/getInfo             →  GET  /getInfo
```

环境变量 `VUE_APP_BASE_API = '/hejiayun'` 统一管理这个前缀。

### 3.3 Axios 封装（request.js）

前端 `src/utils/request.js` 中 Axios 拦截器做了以下事情：
- **请求拦截器：** 从 Cookie 取 Token，自动加到 `Authorization: Bearer xxx` 请求头
- **响应拦截器：** 如果是 401（未登录），自动跳转到登录页；如果是其他错误，显示提示消息

### 3.4 启动顺序

```
第一步：启动 MySQL + Redis

第二步：启动后端（占 8080 端口）
  cd E:\idea_workscope\HejiayunCommunity\hjy-community
  mvn spring-boot:run

第三步：启动前端（占 80 端口）
  cd E:\idea_workscope\HejiayunCommunity\hejiayun_ui
  npm run dev

第四步：浏览器打开
  http://localhost
```

---

## 四、主工程 hjy-community 详解

### 4.1 包结构

```
org.example.hjycommunity
├── HjyCommunityApplication.java          ★ 启动入口
│
├── common/                               ★ 公共基础设施层
│   ├── constant/         
│   │   ├── Constants.java                Token 前缀、验证码 Key
│   │   ├── HttpStatus.java               HTTP 状态码
│   │   └── UserConstants.java            用户常量
│   ├── core/          
│   │   ├── controller/BaseController.java  基类（分页、响应）
│   │   ├── domain/
│   │   │   ├── BaseEntity.java           Entity 基类
│   │   │   ├── BaseResponse.java         统一响应 {code,msg,data,success}
│   │   │   └── ResultCode.java           状态码枚举
│   │   ├── exception/                    全局异常体系
│   │   └── page/                         PageDomain + PageResult
│   ├── handler/MyMateObjectHandler.java  自动填充
│   └── utils/                            RedisCache, SecurityUtils, ExcelUtils 等
│
├── framework/                            ★ 框架配置层（最核心）
│   ├── config/SecurityConfig.java        ★★ Spring Security 配置
│   ├── config/ResourcesConfig.java        CORS 跨域配置
│   ├── config/RedisConfig.java           Redis 配置
│   ├── security/filter/
│   │   ├── JwtAuthenticationTokenFilter.java  ★★ JWT 过滤器
│   │   └── LogoutSuccessHandlerImpl.java
│   ├── security/handler/AuthenticationEntryPointImpl.java
│   └── service/PermsExpressionService.java   @pe 权限表达式
│
├── system/                               ★★ 系统管理模块
│   ├── domain/                           实体（SysUser/Role/Menu/Dept/Area/Dict）
│   ├── mapper/                           7 个 Mapper 接口
│   └── service/                          LoginService, TokenService, 各 CRUD 服务
│
├── community/                            ★★ 物业管理模块
│   ├── domain/                           HjyCommunity + DTO + VO
│   ├── mapper/HjyCommunityMapper.java
│   └── service/HjyCommunityService.java  + Impl
│
└── web/                                  ★★ 控制层
    ├── controller/
    │   ├── common/CaptchaController.java
    │   ├── common/ExportExcelController.java
    │   ├── community/HjyCommunityController.java  （6个接口）
    │   ├── system/SysLoginController.java
    │   ├── system/SysAreaController.java
    │   ├── system/SysDeptController.java
    │   ├── system/SysDictDataController.java
    │   └── system/SysDictTypeController.java
    └── test/                             测试用
```

### 4.2 核心实体关系

```
SysUser（用户）
  ├── deptId → SysDept（物业公司/部门）
  ├── roles → SysRole（角色） → SysMenu（菜单/权限，多对多）
  └── 用户权限集合 → 存在 Redis 中

HjyCommunity（小区）
  ├── community_province_code → SysArea（省）
  ├── community_city_code → SysArea（市）
  ├── community_town_code → SysArea（区县）
  ├── deptId → SysDept（所属物业公司）
  └── 生成编码：COMMUNITY_ + 时间戳

SysDictType（字典类型）
  └── SysDictData（字典数据，通过 dictType 关联）
```

---

## 五、完整 API 接口文档

### 5.1 认证鉴权

| # | 方法 | 后端 URL | 前端路径 | 需Token | 权限 | 功能 |
|:-:|:----:|:---------|:---------|:------:|:----|:-----|
| 1 | GET | `/captchaImage` | `/hejiayun/captchaImage` | ✗ | — | 获取验证码 |
| 2 | POST | `/login` | `/hejiayun/login` | ✗ | — | 用户登录 |
| 3 | POST | `/logout` | `/hejiayun/logout` | ✓ | — | 用户登出 |
| 4 | GET | `/getInfo` | `/hejiayun/getInfo` | ✓ | — | 获取用户信息+权限 |
| 5 | GET | `/getRouters` | `/hejiayun/getRouters` | ✓ | — | 获取路由菜单 |

**详细说明：**

**① 获取验证码 `GET /captchaImage`**
- 生成 4 位数字图形验证码，文本存 Redis `captcha_codes:{uuid}`，有效期 600秒
- 返回：`{ uuid, img(base64) }`
- 代码：`CaptchaController.getCode()`
- 前端调用：登录页加载时自动调用，点验证码图片可刷新

**② 登录 `POST /login`**
- 请求体：`{ username, password, code, uuid }`
- 流程：验证码校验 → DB查用户+BCrypt校验 → 查角色权限 → 生成JWT → 存Redis
- Token 有效期 30 分钟，剩<20分钟自动刷新
- Redis Key：`login_tokens:{uuid}`
- 返回：`{ token }`
- 代码：`SysLoginController.login()` → `SysLoginServiceImpl.login()`
- 前端：登录页提交 → token 存 cookie → 跳转到首页

**③ 获取用户信息 `GET /getInfo`**
- 返回：用户信息 + 角色集合 + 权限集合（用于前端权限控制）
- 前端：页面刷新时调用，恢复用户状态

**④ 获取路由 `GET /getRouters`**
- 返回：RouterVo 结构的路由树
- 前端：动态生成侧边栏菜单和路由表

---

### 5.2 小区管理

| # | 方法 | 后端 URL | 前端路径 | 需Token | 权限 | 功能 |
|:-:|:----:|:---------|:---------|:------:|:----|:-----|
| 6 | GET | `/community/list` | `/hejiayun/community/list` | ✓ | system:community:list | 分页查询 |
| 7 | POST | `/community` | `/hejiayun/community` | ✓ | — | 新增 |
| 8 | GET | `/community/{id}` | `/hejiayun/community/{id}` | ✓ | — | 查详情 |
| 9 | PUT | `/community` | `/hejiayun/community` | ✓ | — | 修改 |
| 10 | DELETE | `/community/{ids}` | `/hejiayun/community/{ids}` | ✓ | — | 批量删除 |
| 11 | GET | `/community/queryPullDown` | `/hejiayun/community/queryPullDown` | ✓ | — | 下拉列表 |
| 12 | GET | `/exportExcel/exportCommunityExcel` | `/hejiayun/exportExcel/exportCommunityExcel` | ✗ | — | 导出Excel |

**详细说明：**

**⑥ 分页查询 `GET /community/list`**
- 参数：`pageNum, pageSize, communityName, communityProvinceCode, communityCityCode, communityTownCode`
- SQL 特点：LEFT JOIN sys_area 三遍，把省/市/区县编码转成中文名称
- 返回：`{ code, total, rows: [HjyCommunityDTO] }`
- 前端：小区管理页面的表格 + 模糊搜索 + 分页

**⑦ 新增 `POST /community`**
- 请求体：`{ communityName, communityProvinceCode, ..., communityDetailedAddress, communityLongitude, communityLatitude, deptId }`
- Service 层自动生成编码：`COMMUNITY_` + 当前时间戳

**⑩ 批量删除 `DELETE /community/{ids}`**
- 路径参数：逗号分隔多个 ID（如 `1631558965133582338,1631559055512444929`）

**⑫ 导出Excel `GET /exportExcel/exportCommunityExcel`**
- 使用 EasyPOI 库，导出为 .xls 文件
- 前端 API 调用会触发文件下载

---

### 5.3 系统管理

#### 部门管理

| 方法 | 后端 URL | 前端路径 | 需Token | 权限 | 功能 |
|:----:|:---------|:---------|:------:|:----|:-----|
| GET | `/system/dept/list` | `/hejiayun/system/dept/list` | ✓ | system:dept:list | 部门列表 |

#### 地区管理

| 方法 | 后端 URL | 前端路径 | 需Token | 功能 |
|:----:|:---------|:---------|:------:|:-----|
| GET | `/system/area/tree` | `/hejiayun/system/area/tree` | ✓ | 省/市/县树形结构 |

#### 字典数据 CRUD

| 方法 | 后端 URL | 前端路径 | 需Token | 功能 |
|:----:|:---------|:---------|:------:|:-----|
| GET | `/system/dict/data/list` | `/hejiayun/system/dict/data/list` | ✓ | 分页查询 |
| GET | `/system/dict/data/{dictCode}` | `/hejiayun/system/dict/data/{dictCode}` | ✓ | 按ID查详情 |
| GET | `/system/dict/data/type/{dictType}` | `/hejiayun/system/dict/data/type/{dictType}` | ✓ | 按类型查列表 |
| POST | `/system/dict/data` | `/hejiayun/system/dict/data` | ✓ | 新增 |
| PUT | `/system/dict/data` | `/hejiayun/system/dict/data` | ✓ | 修改 |
| DELETE | `/system/dict/data/{dictCodes}` | `/hejiayun/system/dict/data/{dictCodes}` | ✓ | 删除 |

#### 字典类型 CRUD

| 方法 | 后端 URL | 前端路径 | 需Token | 功能 |
|:----:|:---------|:---------|:------:|:-----|
| GET | `/system/dict/type/list` | `/hejiayun/system/dict/type/list` | ✓ | 分页查询 |
| GET | `/system/dict/type/{dictId}` | `/hejiayun/system/dict/type/{dictId}` | ✓ | 详情 |
| POST | `/system/dict/type` | `/hejiayun/system/dict/type` | ✓ | 新增（唯一性校验） |
| PUT | `/system/dict/type` | `/hejiayun/system/dict/type` | ✓ | 修改 |
| DELETE | `/system/dict/type/{dictIds}` | `/hejiayun/system/dict/type/{dictIds}` | ✓ | 删除 |
| DELETE | `/system/dict/type/clearCache` | `/hejiayun/system/dict/type/clearCache` | ✓ | 清空缓存 |

---

### 5.4 API 总表（26 个接口）

| # | 方法 | URL | 前端路径 | 需Token | 所属模块 |
|:-:|:----:|:----|:---------|:------:|:--------:|
| 1 | GET | `/captchaImage` | `/hejiayun/captchaImage` | ✗ | 认证 |
| 2 | POST | `/login` | `/hejiayun/login` | ✗ | 认证 |
| 3 | POST | `/logout` | `/hejiayun/logout` | ✓ | 认证 |
| 4 | GET | `/getInfo` | `/hejiayun/getInfo` | ✓ | 认证 |
| 5 | GET | `/getRouters` | `/hejiayun/getRouters` | ✓ | 认证 |
| 6 | GET | `/community/list` | `/hejiayun/community/list` | ✓ | 小区 |
| 7 | POST | `/community` | `/hejiayun/community` | ✓ | 小区 |
| 8 | GET | `/community/{id}` | `/hejiayun/community/{id}` | ✓ | 小区 |
| 9 | PUT | `/community` | `/hejiayun/community` | ✓ | 小区 |
| 10 | DELETE | `/community/{ids}` | `/hejiayun/community/{ids}` | ✓ | 小区 |
| 11 | GET | `/community/queryPullDown` | `/hejiayun/community/queryPullDown` | ✓ | 小区 |
| 12 | GET | `/exportExcel/exportCommunityExcel` | `/hejiayun/exportExcel/exportCommunityExcel` | ✗ | 小区 |
| 13 | GET | `/system/dept/list` | `/hejiayun/system/dept/list` | ✓ | 部门 |
| 14 | GET | `/system/area/tree` | `/hejiayun/system/area/tree` | ✓ | 地区 |
| 15 | GET | `/system/dict/data/list` | `/hejiayun/system/dict/data/list` | ✓ | 字典数据 |
| 16 | GET | `/system/dict/data/{code}` | `/hejiayun/system/dict/data/{code}` | ✓ | 字典数据 |
| 17 | GET | `/system/dict/data/type/{type}` | `/hejiayun/system/dict/data/type/{type}` | ✓ | 字典数据 |
| 18 | POST | `/system/dict/data` | `/hejiayun/system/dict/data` | ✓ | 字典数据 |
| 19 | PUT | `/system/dict/data` | `/hejiayun/system/dict/data` | ✓ | 字典数据 |
| 20 | DELETE | `/system/dict/data/{codes}` | `/hejiayun/system/dict/data/{codes}` | ✓ | 字典数据 |
| 21 | GET | `/system/dict/type/list` | `/hejiayun/system/dict/type/list` | ✓ | 字典类型 |
| 22 | GET | `/system/dict/type/{id}` | `/hejiayun/system/dict/type/{id}` | ✓ | 字典类型 |
| 23 | POST | `/system/dict/type` | `/hejiayun/system/dict/type` | ✓ | 字典类型 |
| 24 | PUT | `/system/dict/type` | `/hejiayun/system/dict/type` | ✓ | 字典类型 |
| 25 | DELETE | `/system/dict/type/{ids}` | `/hejiayun/system/dict/type/{ids}` | ✓ | 字典类型 |
| 26 | DELETE | `/system/dict/type/clearCache` | `/hejiayun/system/dict/type/clearCache` | ✓ | 字典类型 |

---

## 六、前端项目 hejiayun_ui 详解

### 6.1 项目状态

| 项目 | 说明 |
|:----|:------|
| 位置 | `E:\idea_workscope\HejiayunCommunity\hejiayun_ui` |
| 源码 | ✅ **完整** — 所有页面/组件/API/路由/状态管理齐全 |
| 依赖 | ✅ 已安装（node_modules 存在） |
| 可运行 | ✅ 可以直接 `npm run dev` 启动 |
| 前端端口 | 80 |
| 代理 | `/hejiayun` → `http://localhost:8080` |

### 6.2 完整前端目录结构

```
hejiayun_ui/src/
├── main.js                          ★ 入口文件（Vue 实例化）
├── App.vue                          ★ 根组件
├── permission.js                    ★ 路由权限守卫（登录鉴权）
├── settings.js                      项目配置（标题、主题等）
│
├── api/                             ★★ API 调用封装
│   ├── login.js                     登录/登出/验证码/用户信息
│   ├── menu.js                      菜单
│   ├── property/
│   │   ├── community.js             小区 CRUD + 导出 + 地区树 + 下拉
│   │   ├── building.js              楼宇管理
│   │   ├── unit.js                  单元管理
│   │   ├── room.js                  房屋管理
│   │   ├── owner.js                 业主管理
│   │   ├── ownerRoom.js             业主房屋关联
│   │   ├── repair.js                报修管理
│   │   ├── visitor.js               访客管理
│   │   ├── interaction.js           社区互动
│   │   └── suggest.js               建议反馈
│   ├── system/                      用户/角色/菜单/部门/字典/公告/岗位/参数配置
│   ├── monitor/                     在线用户/操作日志/登录日志/服务器/缓存/定时任务
│   └── tool/gen.js                  代码生成
│
├── router/index.js                  ★★ 路由配置
│
├── store/                           ★★ Vuex 状态管理
│   ├── index.js
│   ├── getters.js
│   └── modules/
│       ├── user.js                  用户状态（token, 信息, 权限）
│       ├── permission.js            路由/菜单权限
│       ├── app.js                   侧边栏/设备/主题
│       ├── settings.js              系统设置
│       └── tagsView.js              标签页
│
├── layout/                          ★★ 布局组件
│   ├── index.vue                    主布局（侧边栏+顶栏+主内容区）
│   └── components/
│       ├── Sidebar/                 侧边栏菜单
│       ├── Navbar.vue               顶栏导航
│       ├── TagsView/                标签页切换
│       ├── AppMain.vue              iframe 主内容区
│       └── Settings/                右侧设置面板
│
├── views/                           ★★ 页面组件
│   ├── login.vue                    登录页（验证码 + 账号密码 + 记住我）
│   ├── index.vue                    首页仪表盘（ECharts 图表）
│   ├── redirect.vue                 重定向
│   ├── error/401.vue, 404.vue       错误页
│   ├── dashboard/                   首页图表组件（柱状/折线/饼图/雷达图）
│   ├── property/                    ★ 物业管理页面
│   │   ├── community/index.vue      小区管理（搜索 + 表格 + 新增/编辑/删除/导出）
│   │   ├── building/index.vue       楼宇管理
│   │   ├── unit/index.vue           单元管理
│   │   ├── room/index.vue           房屋管理
│   │   ├── owner/index.vue          业主管理
│   │   ├── ownerRoom/index.vue      业主房屋
│   │   ├── repair/index.vue         报修管理
│   │   ├── visitor/index.vue        访客管理
│   │   ├── interaction/index.vue    社区互动
│   │   └── suggest/index.vue        建议反馈
│   ├── system/                      系统管理页面
│   │   ├── user/index.vue           用户管理
│   │   ├── role/index.vue           角色管理
│   │   ├── menu/index.vue           菜单管理
│   │   ├── dept/index.vue           部门管理
│   │   ├── dict/index.vue           字典类型
│   │   ├── dict/data.vue            字典数据
│   │   ├── post/index.vue           岗位管理
│   │   ├── config/index.vue         参数配置
│   │   └── notice/index.vue         公告管理
│   ├── monitor/                     系统监控页面
│   ├── tool/                        系统工具
│   └── components/icons/            图标管理
│
├── components/                      ★★ 公共组件
│   ├── Pagination/index.vue         分页组件
│   ├── Breadcrumb/index.vue         面包屑
│   ├── HeaderSearch/index.vue       全局搜索
│   └── ...                          SvgIcon, UploadImage, Editor 等
│
├── directive/permission/            ★ v-hasPermi / v-hasRole 权限指令
│
├── utils/                           ★★ 工具类
│   ├── request.js                   Axios 封装（请求/响应拦截器）
│   ├── auth.js                      token 存取（cookie）
│   ├── permission.js                权限校验（v-hasPermi 底层）
│   ├── validate.js                  表单校验规则
│   ├── jsencrypt.js                 RSA 加密
│   └── errorCode.js                 错误码映射
│
└── assets/                          静态资源（样式/图标/图片）
```

### 6.3 前端核心功能

#### 6.3.1 登录页面（login.vue）
- 用户输入账号、密码、验证码
- 验证码点击可刷新（调用 `/captchaImage`）
- 登录成功 → token 存 cookie → 获取用户信息/路由 → 跳转首页

#### 6.3.2 首页仪表盘（dashboard）
- PanelGroup：四项统计数据展示（数字滚动动画）
- ECharts 图表：柱状图、折线图、饼图、雷达图

#### 6.3.3 小区管理（property/community/index.vue）
- 搜索栏：按小区名称、编码查询
- 表格：序号、名称、编码、地址、创建时间、操作按钮
- 操作：新增、修改、删除（批量）、导出 Excel
- 权限控制：`v-hasPermi="['system:community:add']"` 控制按钮显示

#### 6.3.4 系统管理页面
- 用户管理：账号/姓名/手机号/状态/角色分配
- 角色管理：角色 + 菜单权限分配
- 菜单管理：树形结构菜单配置
- 字典管理：字典类型 + 字典数据（如性别男/女、状态启用/停用）

#### 6.3.5 权限控制实现
前后端双重权限控制：
- **后端**：`@PreAuthorize` 注解控制接口访问
- **前端**：`v-hasPermi` 指令控制按钮/页面显示

路由权限控制（`permission.js`）：
1. 从后端 `/getInfo` 拿到用户权限列表
2. 根据权限动态生成路由表
3. 无权限的路由不渲染，跳转 401 页面

### 6.4 前端路由配置

| 页面 | 路由路径 | 隐藏 |
|:----|:---------|:----:|
| 登录 | `/login` | ✓ |
| 首页 | `/index` | ✗ |
| 小区管理 | `/property/community` | ✗ |
| 楼宇管理 | `/property/building` | ✗ |
| 单元管理 | `/property/unit` | ✗ |
| 房屋管理 | `/property/room` | ✗ |
| 业主管理 | `/property/owner` | ✗ |
| 报修管理 | `/property/repair` | ✗ |
| 访客管理 | `/property/visitor` | ✗ |
| 社区互动 | `/property/interaction` | ✗ |
| 建议反馈 | `/property/suggest` | ✗ |
| 用户管理 | `/system/user` | ✗ |
| 角色管理 | `/system/role` | ✗ |
| 菜单管理 | `/system/menu` | ✗ |
| 部门管理 | `/system/dept` | ✗ |
| 字典管理 | `/system/dict` | ✗ |
| 公告管理 | `/system/notice` | ✗ |
| 岗位管理 | `/system/post` | ✗ |
| 参数配置 | `/system/config` | ✗ |
| 个人中心 | `/user/profile` | ✓ |
| 操作日志 | `/monitor/operlog` | ✗ |
| 登录日志 | `/monitor/logininfor` | ✗ |
| 在线用户 | `/monitor/online` | ✗ |
| 定时任务 | `/monitor/job` | ✗ |
| 缓存监控 | `/monitor/cache` | ✗ |
| 服务器监控 | `/monitor/server` | ✗ |
| 代码生成 | `/tool/gen` | ✗ |
| Swagger | `/tool/swagger` | ✗ |
| 图标管理 | `/components/icons` | ✗ |
| 401/404 | `/401` / `/404` | ✓ |

---

## 七、数据库设计

### 7.1 配置

```yaml
数据库: hejiayun_community
地址: jdbc:mysql://127.0.0.1:3306/hejiayun_community?useSSL=true&serverTimezone=GMT%2B8
用户/密码: root/root
连接池: Druid (initialSize=5, maxActive=20, maxWait=50000ms)
```

### 7.2 表结构

根据代码和 MyBatis-Plus 实体类反推：

```sql
-- 小区表（核心业务）
CREATE TABLE `hjy_community` (
  `community_id` bigint NOT NULL COMMENT '小区id（雪花算法）',
  `community_name` varchar(100) COMMENT '小区名称',
  `community_code` varchar(50) COMMENT '小区编码（COMMUNITY_+时间戳）',
  `community_province_code` varchar(20) COMMENT '省区划码',
  `community_city_code` varchar(20) COMMENT '市区划码',
  `community_town_code` varchar(20) COMMENT '区县区划码',
  `community_detailed_address` varchar(200) COMMENT '详细地址',
  `community_longitude` varchar(20) COMMENT '经度',
  `community_latitude` varchar(20) COMMENT '纬度',
  `dept_id` bigint COMMENT '物业id',
  `community_sort` int COMMENT '排序',
  `create_by` varchar(50), `create_time` datetime,
  `update_by` varchar(50), `update_time` datetime,
  `remark` varchar(500),
  PRIMARY KEY (`community_id`)
);

-- 系统用户表
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL,
  `dept_id` bigint, `user_name` varchar(30), `nick_name` varchar(30),
  `email` varchar(50), `phonenumber` varchar(11),
  `sex` char(1), `avatar` varchar(255),
  `password` varchar(100), `salt` varchar(20),
  `status` char(1), `del_flag` char(1),
  `login_ip` varchar(50), `login_date` datetime,
  PRIMARY KEY (`user_id`)
);

-- 行政区划表
CREATE TABLE `sys_area` (
  `code` varchar(20) PRIMARY KEY,
  `name` varchar(50), `level` int, `parent_code` varchar(20)
);

-- 其他表：sys_role, sys_menu, sys_dept, sys_dict_data, sys_dict_type
-- 以及关联表：sys_user_role, sys_role_menu
```

> **⚠️ 注意：** 项目中未附带 SQL 建表脚本。需要从 RuoYi-Vue 官方代码中获取，或手动在 MySQL 中创建。

---

## 八、核心请求流程（完整链路）

### 8.1 用户从浏览器登录的完整流程

```
浏览器                            前端 (Vue)                      后端 (Spring Boot)              Redis / DB
  │                                 │                               │                              │
  │ 打开 http://localhost            │                               │                              │
  │────────────────────────────────>│                               │                              │
  │                                 │                               │                              │
  │                                 │ Vue 实例化 → router 匹配      │                              │
  │                                 │ permission.js 检查 cookie     │                              │
  │                                 │ 有没有 token？                 │                              │
  │                                 │ 无 → 跳转 /login              │                              │
  │                                 │                               │                              │
  │  看到登录页                     │                               │                              │
  │<────────────────────────────────│                               │                              │
  │                                 │                               │                              │
  │  页面加载 → 自动调验证码        │                               │                              │
  │────────────────────────────────>│                               │                              │
  │                                 │ GET /hejiayun/captchaImage    │                              │
  │                                 │──────────────────────────────>│                              │
  │                                 │                               │ 生成4位验证码                 │
  │                                 │                               │─────────────────────────────>│
  │                                 │                               │ captcha_codes:{uuid}         │
  │                                 │ {uuid, img(base64)}          │                              │
  │                                 │<──────────────────────────────│                              │
  │  显示验证码图片                 │                               │                              │
  │<────────────────────────────────│                               │                              │
  │                                 │                               │                              │
  │  用户输入账号/密码/验证码       │                               │                              │
  │────────────────────────────────>│                               │                              │
  │                                 │ POST /hejiayun/login          │                              │
  │                                 │ {username,password,code,uuid} │                              │
  │                                 │──────────────────────────────>│                              │
  │                                 │                               │                              │
  │                                 │                               │ ① 验证码校验                 │
  │                                 │                               │─────────────────────────────>│
  │                                 │                               │<─────────────────────────────│
  │                                 │                               │                              │
  │                                 │                               │ ② AuthenticationManager      │
  │                                 │                               │ ③ UserDetailsServiceImpl    │
  │                                 │                               │ ④ 查用户+BCrypt校验          │
  │                                 │                               │─────────────────────────────>│
  │                                 │                               │<── sys_user 表──────────────│
  │                                 │                               │                              │
  │                                 │                               │ ⑤ 查角色+权限               │
  │                                 │                               │─────────────────────────────>│
  │                                 │                               │<── sys_role/menu 表─────────│
  │                                 │                               │                              │
  │                                 │                               │ ⑥ 生成JWT + 存Redis          │
  │                                 │                               │─────────────────────────────>│
  │                                 │                               │ login_tokens:{uuid}         │
  │                                 │ {token}                      │                              │
  │                                 │<──────────────────────────────│                              │
  │                                 │                               │                              │
  │                                 │ token → cookie                │                              │
  │                                 │ GET /hejiayun/getInfo         │                              │
  │                                 │ GET /hejiayun/getRouters      │                              │
  │                                 │                               │                              │
  │  看到首页（侧边栏+仪表盘）      │                               │                              │
  │<────────────────────────────────│                               │                              │
```

### 8.2 查询小区列表的请求链路

```
用户点击 "小区管理" 菜单
      │
      ▼
前端路由 → /property/community
      │
      ▼
community/index.vue加载 → created() → getList()
      │
      ▼
api/property/community.js → listCommunity(query)
      │
      ▼
utils/request.js (Axios)
  ├── 请求拦截器: 从cookie取token → 加到 Authorization header
  └── 发送 GET /hejiayun/community/list?pageNum=1&pageSize=10
      │
      ▼ 代理转发
http://localhost:8080/community/list
      │
      ▼
CorsFilter → JWT过滤器 → SecurityConfig权限校验
  ├── @PreAuthorize("@pe.hasPermission('system:community:list')")
  └── 无权限 → 返回403
      │
      ▼
HjyCommunityController.communityList()
  ├── startPage() → PageHelper 分页
  ├── HjyCommunityService.queryCommunityList()
  ├── HjyCommunityMapper.queryCommunityList()
  └── 执行SQL: SELECT hc.*, sa1.name, sa2.name, sa3.name
              FROM hjy_community hc
              LEFT JOIN sys_area sa1 ON hc.community_province_code=sa1.code
              LEFT JOIN sys_area sa2 ON hc.community_city_code=sa2.code
              LEFT JOIN sys_area sa3 ON hc.community_town_code=sa3.code
              <where> communityName模糊/省市县筛选 </where>
              ORDER BY create_time DESC
      │
      ▼
返回 PageResult {code:200, total, rows}
      │
      ▼
前端渲染表格（el-table + el-pagination）
```

---

## 九、子模块说明

### 9.1 springsecurity_example（练习① — 最完善）

**技术特色：** 自建 JWT 工具链 + 自定义权限表达式 + 单元测试

**API 接口：**

| 方法 | URL | 功能 | 权限注解 | 需Token |
|:----:|:----|:----|:---------|:------:|
| GET | `/captchaImage` | 获取验证码 | — | ✗ |
| POST | `/user/login` | 登录 | — | ✗ |
| GET | `/user/logout` | 登出 | — | ✓ |
| GET | `/hello` | 测试单权限 | `hasAuthority('system:user:list')` | ✓ |
| GET | `/ok` | 测试多权限 | `hasAnyAuthority(...)` | ✓ |
| GET | `/yes` | 测试自定义表达式 | `@my_ex.hasAuthority(...)` | ✓ |
| GET | `/role1` | 测试单角色 | `hasRole('admin')` | ✓ |
| GET | `/role2` | 测试多角色 | `hasAnyRole('admin','zhangsan')` | ✓ |
| GET | `/level1` | 测试组合条件1 | `hasRole('common') OR hasAnyAuthority(...)` | ✓ |
| GET | `/level2` | 测试组合条件2 | `hasAnyRole(...) OR hasAuthority(...)` | ✓ |
| GET | `/testCors` | 测试跨域 | — | ✓ |

### 9.2 springsecurity_example2（练习②）

**技术特色：** Kaptcha 验证码 + 表单登录 + Thymeleaf 页面

| 方法 | URL | 功能 | 需登录 |
|:----:|:----|:----|:------:|
| GET | `/code/image` | Kaptcha 验证码图片 | ✗ |
| GET | `/login.html` | 登录页面 | ✗ |
| POST | `/login` | 表单登录 | ✗ |
| GET | `/ok` | 登录成功页 | ✓ |

### 9.3 easypoi_boot（练习③）
Spring Boot 2.7.8 + MyBatis + Thymeleaf + EasyPOI，骨架状态。

### 9.4 easy_code（练习④）
IDEA EasyCode 插件测试，pom.xml + 空 mapper 目录。

---

## 十、学习路径推断

```
2026-04-03  项目初始化
    │
    ├── 第一阶段：Spring Security 基础
    │   2026-04-08  springsecurity_example2（Kaptcha + 表单登录）
    │   2026-04-09  springsecurity_example（JWT + @PreAuthorize）
    │
    ├── 第二阶段：EasyPOI 练习
    │   2026-04-07  easypoi_boot
    │
    ├── 第三阶段：整合实战（后端）
    │   2026-04-06  创建 hjy-community 项目
    │   2026-04-06  小区模块完成
    │   2026-04-07  地区/部门/Excel导出
    │   2026-04-12  Security + JWT 整合
    │   2026-04-13  CORS + Token过滤器
    │   2026-04-15  字典数据 CRUD
    │   2026-04-16  字典类型 CRUD + 缓存
    │
    └── 第四阶段：前端集成
        2026-04-03  初始化 hejiayun_ui（Vue + Element UI）
          → node_modules 安装
          → vue.config.js 代理配置
          → 完整页面开发（登录/首页/小区/系统管理/监控/工具）
```

---

## 十一、亮点与改进建议

### 亮点

| # | 亮点 | 说明 |
|:-:|:-----|:------|
| 1 | 前后端分离 | Vue + Spring Boot 标准架构 |
| 2 | 统一响应格式 | `BaseResponse<T>` 全局统一 |
| 3 | 全局异常处理 | `@ControllerAdvice` 统一异常 |
| 4 | RBAC 权限模型 | 用户→角色↔菜单/权限，前后端双重控制 |
| 5 | Token 自动刷新 | 30分钟有效期，<20分钟自动续期 |
| 6 | 自动填充 | MyBatis-Plus 自动处理 createTime/updateTime |
| 7 | 雪花 ID | MyBatis-Plus assign_id 策略 |
| 8 | 字典缓存 | Redis 缓存 + 清空接口 |
| 9 | EasyPOI 导出 | 一键导出 Excel |
| 10 | 前端页面完整 | 登录/仪表盘/小区管理/系统管理/监控/工具 齐全 |
| 11 | 前端权限指令 | `v-hasPermi` / `v-hasRole` 控制按钮级权限 |
| 12 | 学习路线完整 | 从独立练习到整合实战的完整记录 |

### 改进建议

| # | 问题 | 建议 |
|:-:|:-----|:------|
| 1 | 缺少 SQL 建表脚本 | 补充 schema.sql 和 data.sql |
| 2 | JWT 密钥硬编码 | `luoYu` 移入环境变量 |
| 3 | 密码明文配置 | 数据库密码用环境变量替代 |
| 4 | Java 版本不统一 | pom.xml 统一为 Java 11 |
| 5 | 缺少单元测试 | 补充 Service 层单元测试 |
| 6 | LoginUser 权限问题 | `getAuthorities()` 返回空列表，需补充 |
| 7 | 缺少事务注解 | Service 层补充 `@Transactional` |
| 8 | Swagger 未启用 | 已引入未配置 `@EnableSwagger2` |
| 9 | easy_code 版本问题 | spring-web 7.0.6 过高 |

---

## 十二、快速启动与演示指南

### 12.1 完整启动（前后端同时运行）

```bash
# 1. 确保 MySQL 和 Redis 已启动

# 2. 启动后端
cd E:\idea_workscope\HejiayunCommunity\hjy-community
mvn spring-boot:run
# → 后端 http://localhost:8080

# 3. 新开终端，启动前端
cd E:\idea_workscope\HejiayunCommunity\hejiayun_ui
npm run dev
# → 前端 http://localhost:80

# 4. 浏览器打开
# → http://localhost    看到登录页
```

### 12.2 课堂演示流程

```
1. 浏览器打开 http://localhost
   显示：合家云社区物业管理平台 登录页
   有账号/密码/验证码输入框

2. 输入默认账号：admin / admin123
   点击登录 → 进入首页仪表盘
   （有统计面板 + ECharts 图表）

3. 左侧菜单 → 点击"小区管理"
   显示：小区列表搜索+表格+新增/修改/删除/导出按钮

4. 点击"新增" → 填写小区信息 → 保存
   → 回到列表，数据刷新

5. 点击"导出" → 下载 Excel 文件
   → 用 Excel 打开查看

6. 左侧菜单 → 系统管理 → 用户管理/角色管理/菜单管理
   → 展示 RBAC 权限配置

7. 打开浏览器的开发者工具 → Network 标签
   → 展示请求头中的 Authorization: Bearer xxx
   → 展示响应 JSON 数据
```

### 12.3 纯后端 API 测试（不带前端）

```bash
# 1. 获取验证码
curl http://localhost:8080/captchaImage
# 返回 {"uuid":"xxx","img":"data:..."}

# 2. 登录（替换实际验证码）
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123","code":"上一步验证码","uuid":"上一步uuid"}'
# 返回 {"token":"eyJhbG..."}

# 3. 查询小区列表
curl -H "Authorization: Bearer eyJhbG..." \
  "http://localhost:8080/community/list?pageNum=1&pageSize=10"

# 4. 新增小区
curl -X POST http://localhost:8080/community \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbG..." \
  -d '{"communityName":"测试小区","communityDetailedAddress":"测试地址"}'

# 5. 导出 Excel
curl -o 小区信息.xls "http://localhost:8080/exportExcel/exportCommunityExcel"
```

### 12.4 练习模块启动

```bash
# springsecurity_example（推荐看权限测试接口）
cd E:\idea_workscope\HejiayunCommunity\springsecurity_example
mvn spring-boot:run
# → http://localhost:8080/hello

# springsecurity_example2（有登录页面）
cd E:\idea_workscope\HejiayunCommunity\springsecurity_example2
mvn spring-boot:run
# → http://localhost:8080/login.html
```

---

> **文档生成日期：** 2026-06-03
> **文档说明：** 基于项目源码自动分析生成，涵盖后端（Spring Boot）+ 前端（Vue 2）完整内容
