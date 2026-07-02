# Presentation Outline

## Page 1 [cover]
- **Title**: beacon-cloud 分布式短信平台
- **Content**: 基于 Spring Cloud 微服务架构的 SMS Gateway 项目汇报

## Page 2 [table_of_contents]
- **Title**: 目录
- **Content**: 1. 项目背景与定位; 2. 系统架构设计; 3. 关键技术亮点; 4. 项目成果与展望

## Page 3 [chapter]
- **Title**: 01 项目背景与定位
- **Content**: 了解 beacon-cloud 是什么、解决什么问题、使用什么技术

## Page 4 [content]
- **Title**: 项目定位与核心业务
- **Content**: beacon-cloud 是一个基于 Spring Cloud 微服务架构的分布式短信平台，提供从 API 接入、校验、策略路由到运营商通道下发的完整短信发送链路。核心业务场景：验证码短信、通知短信、营销短信。管理后台覆盖客户/通道/黑名单/敏感词/计费/报表全管理链路。参考项目整体分析"一、项目定位"

## Page 5 [content]
- **Title**: 技术栈一览
- **Content**: 以表格展示各层面技术选型：基础框架 Spring Boot 2.3.12，微服务 Spring Cloud Hoxton.SR12 + Spring Cloud Alibaba 2.2.6，注册/配置中心 Nacos，消息队列 RabbitMQ，缓存 Redis，数据库 MySQL，搜索引擎 Elasticsearch，任务调度 XXL-Job，短信协议 CMPP (Netty 4)，ORM MyBatis，权限框架 Apache Shiro，JDK Java 8。参考项目整体分析"二、技术栈一览"

## Page 6 [chapter]
- **Title**: 02 系统架构设计
- **Content**: 微服务拆分、模块职责、核心业务流程

## Page 7 [content]
- **Title**: 模块全景 — 10 个 Maven 子模块
- **Content**: 分三类展示：核心短信链路（beacon-api :10001 接入网关、beacon-strategy :18883 策略中心、beacon-push :18885 推送、beacon-SmsGateway :18886 短信网关）；数据与缓存（beacon-cache :18882、beacon-synchronization、beacon-search :18884）；运维与管理（beacon-monitor :18887、beacon-webmaster、beacon-common 公共模块）。参考项目整体分析"三、模块全景"

## Page 8 [content]
- **Title**: 核心业务流程 — 短信发送全链路
- **Content**: 以流程图展示：客户端请求 → beacon-api (前置校验链: apikey→IP→sign→template→mobile→fee) → RabbitMQ → beacon-strategy (二次策略校验: 号段补全→敏感词DFA→黑名单→频次限制→计费→通道路由) → RabbitMQ → beacon-push → beacon-SmsGateway (Netty CMPP 协议) → 运营商短信中心 → 用户手机。参考项目整体分析"四、核心业务流程"

## Page 9 [chapter]
- **Title**: 03 关键技术亮点
- **Content**: 设计模式、协议实现、动态配置、数据管理

## Page 10 [content]
- **Title**: 设计模式与技术创新
- **Content**: 五大亮点：(1) 策略模式+责任链：API和Strategy模块均使用 XxxFilterContext + Map<String, Filter> 注入实现动态校验链，顺序可通过 Nacos/Redis 动态配置；(2) 事件驱动：模块间通过 RabbitMQ 解耦，支持 confirm + return 机制保证消息可靠投递；(3) 雪花算法：全局唯一 ID 生成；(4) DFA 算法：敏感词过滤使用确定有限自动机实现高效匹配；(5) CMPP 协议：Netty 自定义编解码器实现运营商短信协议。参考项目整体分析"设计模式亮点"

## Page 11 [content]
- **Title**: 管理后台与数据模型
- **Content**: 管理后台采用 Spring Boot + Shiro + MyBatis + 原生 HTML/JS 前端，覆盖客户管理、通道管理、黑名单、敏感词、充值、报表、系统配置等全链路。核心数据表：client_business(客户业务)、client_sign(签名)、client_template(模板)、channel(通道信息)、mobile_black(黑名单)、mobile_dirty_word(敏感词)、mobile_area(号段归属地)等。参考项目整体分析"五、数据模型"和"六、管理后台"

## Page 12 [chapter]
- **Title**: 04 项目成果与展望
- **Content**: 已完成工作、待完善事项、未来方向

## Page 13 [content]
- **Title**: 项目状态与潜在风险
- **Content**: 已完成：核心短信发送链路完整、前置校验链6个过滤器、策略校验链9+个过滤器、CMPP协议Netty客户端、管理后台前端+后端CRUD、数据同步模块、Nacos配置中心。待完善：PhaseStrategyFilter号段同步逻辑、敏感词检测后处理、webmaster代码人工校对。潜在风险：Nacos配置明文密码需加密、核心链路缺少单元测试、Java 8+Spring Boot 2.3版本较旧、消息Reject后不重新入队可能导致丢失。参考项目整体分析"八、项目状态与观察"

## Page 14 [content]
- **Title**: 改进方向与总结
- **Content**: 四大改进方向：安全加固（密码加密、权限细化）、版本升级（Spring Boot 3 + JDK 17）、测试补全（核心链路单元测试+集成测试）、消息可靠性（死信队列+重试机制）。总结：beacon-cloud 是一个功能完整的分布式短信平台，架构设计合理，微服务拆分清晰，异步解耦，支持动态配置和运营商级短信下发能力。参考项目整体分析"十、总结"

## Page 15 [final]
- **Title**: 谢谢聆听
- **Content**: beacon-cloud — 基于 Spring Cloud 的分布式短信平台 | 问答交流
