# 教务管理系统

基于 **SSM（Spring + Spring MVC + MyBatis）** 框架开发的教务会员管理系统，支持多角色权限管理、会员管理、课程/教练/器材管理、商品销售、数据统计等功能。

## 技术栈

| 层级 | 技术 |
|------|------|
| 后端框架 | Spring 5.x + Spring MVC |
| 安全框架 | Spring Security 5.x |
| ORM | MyBatis 3.x |
| 数据库 | MySQL 8.0 |
| 前端 | Layui + Bootstrap Table + jQuery + SweetAlert |
| 构建工具 | Maven |
| 运行环境 | Java 8 + Tomcat |

## 功能模块

### 系统管理
- **用户管理** — 管理员账号的增删改查
- **角色权限** — 基于 RBAC 的角色与菜单权限分配
- **系统日志** — 操作日志查看
- **修改密码** — 个人密码修改

### 教务核心
- **会员管理** — 会员信息、会员卡管理、续卡记录、到期提醒
- **会员充值** — 充值记录查询与管理
- **教练管理** — 教练信息与授课科目关联
- **科目管理** — 课程科目的增删改查
- **器材管理** — 教学器材的库存管理

### 商品经营
- **商品列表** — 商品信息维护
- **商品销售** — 销售记录管理
- **统计报表** — 数据统计与图表展示

## 项目结构

```
edu-member-management/
├── 1.database-scripts/          # 数据库初始化脚本
│   ├── edu_system.sql           # 完整建库建表 + 初始数据
│   └── credentials.txt          # 默认账号密码（已排除上传）
├── 2.frontend/                  # 前端页面（纯 HTML/JS）
│   ├── pages/                   # 各功能页面
│   └── static/                  # 静态资源（CSS/JS/字体/图片）
└── 5.backend/                   # 后端 Java 工程
    ├── xq-edu-system/           # 主业务系统（Spring MVC + MyBatis）
    └── springSecurity-project/  # Spring Security 权限模块
```

## 快速开始

### 环境要求
- JDK 1.8+
- MySQL 8.0+
- Maven 3.x
- Tomcat 8.5+

### 1. 导入数据库
```sql
-- 执行数据库脚本
source 1.database-scripts/edu_system.sql;
```

### 2. 配置数据源
修改后端 `applicationContext.xml` 或 `jdbc.properties` 中的数据库连接信息：
```
jdbc.url=jdbc:mysql://localhost:3306/edu?useSSL=false&serverTimezone=Asia/Shanghai
jdbc.username=root
jdbc.password=你的密码
```

### 3. 构建部署
```bash
cd 5.backend/xq-edu-system
mvn clean package
# 将生成的 target/*.war 部署到 Tomcat webapps/
```

### 4. 访问系统
```
http://localhost:8080/
```
使用默认账号登录（见 `1.database-scripts/credentials.txt`）

## 默认账号

| 角色 | 用户名 | 密码 |
|------|--------|------|
| 管理员 | admin | 123456 |
| 普通用户 | bruce | 12345 |
| 普通用户 | jack | 123456 |

## License

仅供学习参考使用。
