# RicohGrLinkPro（GR Link）

理光 GR 系列相机的跨平台伴侣应用。连接相机后可配对、浏览机内照片、导入到电脑，并进行远程拍摄与参数调节；未连接时也可添加本地文件夹，统一管理电脑上的照片库。

当前仓库以 **UI 与设计系统** 为主：界面与交互已按设计稿落地，相机通信、文件导入等能力仍使用模拟数据，后续会逐步接入真实设备与文件系统。

## 功能概览

| 模块 | 说明 | 实现状态 |
|------|------|----------|
| **相机连接** | 发现/配对 GR 相机，显示型号、序列号、电量与连接进度 | UI 完成，连接为模拟状态 |
| **远程控制** | 实时取景占位、快门、曝光/ISO 滑条、胶片模拟、白平衡与对焦模式 | UI 完成 |
| **本地照片库** | 网格/列表浏览、集合标题与 EXIF 风格元数据展示 | UI + 模拟数据 |
| **文件夹** | 添加本地目录、按文件夹切换浏览 | UI 完成，「添加文件夹」待接系统文件选择 |
| **设置** | 开机启动、外观、默认保存路径、连接后自动导入、RAW/镜头配置、云存储占位等 | UI 完成 |
| **导入照片** | 侧边栏一键从相机导入 | 入口已预留，逻辑待实现 |

顶部工具栏在连接相机后显示电量；可在 **Remote / Library / Editor** 标签间切换（Editor 为规划项）。

## 支持平台

基于 [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) 与 [Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/) 构建：

- **Desktop（JVM）** — macOS / Linux / Windows
- **Android**
- **iOS**（通过 `iosApp` + Compose 嵌入）

## 设计稿

`design_by_ai/` 目录存放 AI 生成的界面原型，与当前 Compose 实现一一对应，便于对照与迭代：

| 目录 | 对应界面 |
|------|----------|
| [`camera_connection/`](design_by_ai/camera_connection/) | 相机连接与配对 |
| [`remote_control_panel/`](design_by_ai/remote_control_panel/) | 远程控制面板 |
| [`local_photos_library/`](design_by_ai/local_photos_library/) | 本地照片库 |
| [`settings/`](design_by_ai/settings/) | 设置 |

每个子目录包含 `code.html`（可浏览器打开）与 `screen.png` 截图。

视觉规范见 [`design_by_ai/obsidian_amber/DESIGN.md`](design_by_ai/obsidian_amber/DESIGN.md)：**Obsidian & Amber** 主题——深色 Obsidian 背景、理光标志性橙色用于快门与强调、玻璃拟态层级、Inter + JetBrains Mono 字体体系，贴近 macOS 专业工具风格。

应用内主题实现位于 `composeApp/src/commonMain/kotlin/com/stephen/ricohgrlinkpro/theme/`。

## 项目结构

```
RicohGrLinkPro/
├── composeApp/                 # 共享 Compose 应用
│   └── src/
│       ├── commonMain/         # 跨平台 UI、ViewModel、模型、主题
│       ├── androidMain/        # Android 入口与平台实现
│       ├── iosMain/            # iOS 入口
│       └── jvmMain/            # Desktop 入口（main.kt）
├── iosApp/                     # Xcode 工程，承载 iOS 壳
├── design_by_ai/               # AI 设计稿（HTML + 截图 + DESIGN.md）
└── gradle/                     # 版本目录与 Wrapper
```

- 共享业务与界面代码放在 **`commonMain`**；平台差异使用 `expect` / `actual`。
- 依赖版本在 [`gradle/libs.versions.toml`](gradle/libs.versions.toml) 中集中管理。
- 资源使用 Compose Multiplatform 的 `composeResources`，勿混用各平台原生资源体系。

主要代码入口：

- [`App.kt`](composeApp/src/commonMain/kotlin/com/stephen/ricohgrlinkpro/App.kt) — 根 Composable
- [`AppShell.kt`](composeApp/src/commonMain/kotlin/com/stephen/ricohgrlinkpro/ui/shell/AppShell.kt) — 侧栏 + 顶栏 + 页面路由
- [`AppViewModel.kt`](composeApp/src/commonMain/kotlin/com/stephen/ricohgrlinkpro/AppViewModel.kt) — 导航与界面状态（当前含 `MockData`）

## 环境要求

- **JDK 11+**
- **Android Studio** 或 **IntelliJ IDEA**（含 KMP / Compose 插件）— 用于 Android / Desktop / 共享模块开发
- **Xcode**（仅 iOS 构建与真机调试）
- 首次克隆后使用仓库自带的 Gradle Wrapper（`./gradlew`），无需单独安装 Gradle

## 构建与运行

### 全量编译

```shell
./gradlew :composeApp:build
```

### Desktop

```shell
./gradlew :composeApp:run
```

Windows：

```shell
.\gradlew.bat :composeApp:run
```

### Android

```shell
./gradlew :composeApp:assembleDebug
```

或在 IDE 工具栏选择 Android 运行配置。最低 SDK：24。

### iOS

在 IDE 中选择 iOS 运行配置，或在 Xcode 中打开 [`iosApp`](iosApp) 目录后运行。**请勿手动编辑** `iosApp/*.xcodeproj` 内工程设置，应通过 Xcode 修改。

## 开发说明

- 修改 `commonMain` 时避免引入仅某一平台可用的依赖，否则其他目标无法编译。
- 每处 `expect` 声明都需在对应目标的 `actual` 中实现。
- 相机协议、USB/Wi‑Fi 传输、本地文件索引等将分阶段替换 `data/MockData.kt` 与 `AppViewModel` 中的占位逻辑。

更详细的协作约定见 [`AGENTS.md`](AGENTS.md) / [`CLAUDE.md`](CLAUDE.md)。

## 路线图（规划）

- [ ] 理光 GR 相机发现、配对与稳定连接
- [ ] 机内照片列表与批量导入
- [ ] 远程快门与拍摄参数同步
- [ ] 本地文件夹选择与增量索引
- [ ] 设置项持久化与系统「登录时启动」集成

## 许可证

尚未指定；如需开源发布请补充 `LICENSE` 文件。

## 相关链接

- [Compose Multiplatform 文档](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform.html)
- [Kotlin Multiplatform 文档](https://kotlinlang.org/docs/multiplatform.html)
