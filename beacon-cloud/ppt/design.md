# Design Document

## 1. Profile Baseline Declaration

- **Profile selection**: `profiles/academic.md`
- **Selection rationale**: 用户是给老师做项目展示汇报，属于学术/项目汇报场景，academic profile 的内容严谨性、逻辑清晰度和专业规范最匹配
- **Referenced dimensions**: 高信息密度、图表主导的内容表达方式、导航栏设计、学术化叙事逻辑（背景→架构→方法→成果→展望）
- **Deviation notes**: 
  - 因是工程项目汇报而非论文答辩，装饰风格可略活泼，不使用大学logo
  - 不使用参考文献页（项目汇报非论文），改为项目总结页
  - 允许使用更现代的字体和配色（在学术稳重基础上提升视觉品质）

## 2. Style Baseline Declaration

- **Style anchor selection**: 
  - Monocle 杂志 — 参考其信息密度控制和优雅的排版节奏
  - Apple Keynote 技术发布 — 参考其简洁有力的数据呈现方式
- **Referenced dimension explanation**: 
  - 从 Monocle 参考：留白与内容的比例控制、章节分隔的节奏感、专业但不沉闷的色调
  - 从 Apple Keynote 参考：关键数据的放大展示、流程图的简洁表达、每页聚焦一个核心信息点

## 3. Style Details

### 3.1 Color Design Principles

- **Overall tone**: 保守稳重偏现代，以中性色为基底，局部暖色点缀
- **Temperature**: 偏暖中性，纸质矿物感
- **Primary color**: `#2C3E50`（深灰蓝，稳重专业，非俗套亮蓝）
- **Secondary color**: `#95A5A6`（中性灰，用于辅助信息、分隔线）
- **Accent color**: `#E67E22`（温暖橙色，用于关键数据、章节标记、核心结论高亮）
- **Background color**: `#FDFCFA`（暖白色，比纯白更柔和，降低视觉疲劳）
- **Text color**: `#2C3E50`（深色，确保高可读性）
- **Light text color**: `#FFFFFF`（用于深色背景上的文字）
- **Dark background**: `#2C3E50`（用于封面、章节页、结束页）

### 3.2 Font Usage Principles

- **Title font**: `QuattrocentoSans, alimamashuheiti` — 干净清晰，兼具学术感和现代商业感
- **Body font**: `QuattrocentoSans, MiSans` — 极佳屏幕可读性
- **Font size hierarchy**:
  - 封面标题：40px
  - 页面标题：28px
  - 副标题/章节标题：22px
  - 正文内容：18-20px（信息密度高时18px，适中时20px）
  - 辅助文字/注释：14px
  - 导航栏/页码：12px

### 3.3 Text Box and Container Styles

- **Content separation**: 优先使用留白和字号差异建立层次，辅以细线分隔
- **Cards**: 需要时使用直角矩形卡片，无边框，浅色填充（`#F5F3F0`），与背景形成微妙对比
- **Decorative elements**: 左侧使用 accent color 的竖线装饰（4px宽）作为标题或关键信息的视觉锚点；章节页使用大号半透明章节编号作为背景装饰

### 3.4 Image Style

- **Icons**: 使用 solid style Font Awesome 图标，颜色统一使用 primary 或 accent，使用克制，仅在辅助信息识别时使用
- **Tables**: 学术风格三线表变体，header 使用 primary 色填充+白色文字，数据行使用交替浅色背景
- **Charts**: 极简扁平风格，系列颜色从主色家族中选取，网格线使用极浅灰色
- **Illustrations**: 不使用外部图片（无高质量可用素材），以形状、图标、流程图构建全部视觉内容

## 4. Layout System

### 4.1 Global Layout Characteristics

- **Page size**: 1280 x 720 (16:9)
- **Page margins**: 左右 60px，上下 50px
- **Unified page elements**:
  - 顶部导航栏：高 36px，位于页面顶部，背景 `#2C3E50`，显示当前章节名称（白色14px），当前章节用 accent 色底部边框标记
  - 页码：右下角，12px，secondary 色
  - 内容区域：y 从 86px 到 680px，有效高度约 594px

### 4.2 Special Page Layouts

- **Cover**: 深色背景 `#2C3E50`，居中布局，标题大字（40px白色），副标题（20px `#BDC3C7`），底部装饰线 accent 色，页面底部可放置项目信息
- **Table of contents**: 左侧 40% 放巨型 "CONTENTS" 竖排文字（装饰性），右侧 60% 用 grid 布局展示4个章节，每个章节有编号（accent色大字号）+ 标题 + 简短描述
- **Chapter divider**: 深色背景 `#2C3E50`，左侧放置超大号半透明章节编号（如 "01"），右侧放章节标题和副标题，accent 色竖线装饰
- **Final page**: 深色背景，居中 "谢谢聆听" 大字，下方项目信息，accent 色装饰线

### 4.3 Content Page Layout Patterns

- **Pattern A (表格/数据页)**: 顶部标题区（标题+accent竖线），下方全宽表格或图表区域
- **Pattern B (左右分栏)**: 左侧 55% 文字内容，右侧 45% 图表/流程图/图标列表，底部可对齐
- **Pattern C (流程展示)**: 顶部标题，中部用形状+箭头构建横向或纵向流程图，底部文字注释
- **Pattern D (要点列表)**: 顶部标题，下方 2-3 列 grid 布局，每列一个图标+标题+描述，适合展示设计模式亮点
- **Pattern E (图文卡片)**: 顶部标题，下方多卡片等宽排列，卡片内有图标、标题、描述

## 5. Style Usage Rules

- **textStyles usage**:
  - `title`: 页面标题（content页顶部）、章节标题（chapter页）
  - `subtitle`: 封面副标题、章节副标题、卡片标题
  - `body`: 正文内容、段落文字
  - `caption`: 辅助注释、来源标注、页码
  - `nav`: 导航栏文字
  - `heroNumber`: 章节页超大编号装饰
- **Color allocation**:
  - primary (`#2C3E50`): 导航栏背景、页面标题、表头背景、深色背景页
  - secondary (`#95A5A6`): 辅助文字、分隔线、边框、非激活导航
  - accent (`#E67E22`): 关键数据高亮、当前章节标记、装饰竖线、图标
  - background (`#FDFCFA`): 所有内容页背景
  - text (`#2C3E50`): 正文文字、表格数据文字
- **tableStyle**: `default` — 用于所有数据表格，header 使用 primary 填充，数据行交替使用 background 和 `#F5F3F0`

## 6. Risk Prohibitions

- [ ] 禁止使用蓝色/青色作为主色（俗套）
- [ ] 禁止白色背景（使用暖白色 `#FDFCFA`）
- [ ] 禁止装饰性图标和插图（仅使用功能性图标）
- [ ] 禁止渐变阴影和文字特效（保持学术扁平风格）
- [ ] 禁止左右布局底部不对齐
- [ ] 禁止上下布局内容不居中、左右留白不均
- [ ] 禁止正文字号低于 18px
- [ ] 禁止辅助文字/注释低于 12px
- [ ] 禁止表格无表头或表头无背景区分
- [ ] 禁止导航栏在各页位置不一致
- [ ] 禁止章节页仅用文字无装饰（必须有超大编号或深色背景等视觉重置元素）

## 7. Theme Definition

```yaml
theme:
  colors:
    primary: "#2C3E50"
    secondary: "#95A5A6"
    accent: "#E67E22"
    background: "#FDFCFA"
    text: "#2C3E50"
    light: "#FFFFFF"
    cardBg: "#F5F3F0"
    darkBg: "#2C3E50"
    lightText: "#BDC3C7"
  textStyles:
    title:
      fontSize: 28
      color: "$primary"
      fontFamily: "QuattrocentoSans, alimamashuheiti"
    subtitle:
      fontSize: 22
      color: "$primary"
      fontFamily: "QuattrocentoSans, alimamashuheiti"
    body:
      fontSize: 18
      color: "$text"
      fontFamily: "QuattrocentoSans, MiSans"
      lineHeight: 1.6
    caption:
      fontSize: 14
      color: "$secondary"
      fontFamily: "QuattrocentoSans, MiSans"
    nav:
      fontSize: 14
      color: "$light"
      fontFamily: "QuattrocentoSans, MiSans"
    heroNumber:
      fontSize: 96
      color: "#FFFFFF20"
      fontFamily: "QuattrocentoSans, alimamashuheiti"
  tableStyles:
    default:
      fontSize: 16
      fontFamily: "QuattrocentoSans, MiSans"
      headerFill: "$primary"
      headerColor: "$light"
      headerBold: true
      bodyFill: ["$background", "$cardBg"]
      bodyColor: "$text"
      border:
        style: solid
        width: 1
        color: "#E0E0E0"
```
