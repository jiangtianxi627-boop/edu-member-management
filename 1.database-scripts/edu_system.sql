/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.19 : Database - edu
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`edu` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `edu`;

/*Table structure for table `admin_role_menus` */

DROP TABLE IF EXISTS `admin_role_menus`;

CREATE TABLE `admin_role_menus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roleId` int DEFAULT NULL,
  `menuId` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_roleId_1` (`roleId`) USING BTREE,
  KEY `FK_menuId` (`menuId`) USING BTREE,
  CONSTRAINT `FK_menuId` FOREIGN KEY (`menuId`) REFERENCES `adminmenus` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_roleId_1` FOREIGN KEY (`roleId`) REFERENCES `adminrole` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `admin_role_menus` */

insert  into `admin_role_menus`(`id`,`roleId`,`menuId`) values (14,8,1),(15,8,2),(16,8,4),(17,8,5),(18,8,6),(19,8,7),(20,8,8),(36,1,1),(37,1,2),(38,1,3),(39,1,4),(40,1,5),(41,1,6),(42,1,7),(43,1,8),(44,1,9),(45,1,10),(46,1,11),(47,1,12),(48,1,13),(49,1,14),(50,1,15),(51,1,16),(52,1,17),(53,1,18),(54,1,19),(55,1,20),(56,1,21),(57,1,22),(58,1,23),(59,1,24),(60,1,25),(61,1,26),(62,2,4),(63,2,5),(64,2,6),(65,2,7),(66,2,8),(67,2,17),(68,2,18),(69,2,19),(70,2,20),(71,2,21),(72,3,10),(73,3,11),(74,3,12),(75,3,13),(76,3,14),(77,3,15),(78,3,16),(79,1,40);

/*Table structure for table `admin_user_role` */

DROP TABLE IF EXISTS `admin_user_role`;

CREATE TABLE `admin_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `adminId` int DEFAULT NULL,
  `roleId` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_adminId` (`adminId`) USING BTREE,
  KEY `FK_roleId` (`roleId`) USING BTREE,
  CONSTRAINT `FK_adminId` FOREIGN KEY (`adminId`) REFERENCES `adminuser` (`adminId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_roleId` FOREIGN KEY (`roleId`) REFERENCES `adminrole` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `admin_user_role` */

insert  into `admin_user_role`(`id`,`adminId`,`roleId`) values (12,1,1),(13,7,3),(14,8,2),(15,9,3),(16,10,1),(17,10,3),(20,11,2),(24,12,1);

/*Table structure for table `adminmenus` */

DROP TABLE IF EXISTS `adminmenus`;

CREATE TABLE `adminmenus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `iocn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `parentId` int DEFAULT NULL,
  `type` int DEFAULT NULL COMMENT '0 菜单  1 权限',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `sort` int DEFAULT NULL,
  `del` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `adminmenus` */

insert  into `adminmenus`(`id`,`title`,`url`,`iocn`,`parentId`,`type`,`remark`,`sort`,`del`) values (1,'学员管理',NULL,'&#xe6b8;',0,0,NULL,1,0),(2,'vip学员列表','/member.html','',1,0,NULL,1,0),(3,'vip学员到期','/memeber-expire.html',NULL,1,0,NULL,2,0),(4,'续费管理',NULL,'&#xe70b;',0,0,NULL,2,0),(5,'vip学员续卡管理','/member-card.html',NULL,4,0,NULL,1,0),(6,'vip学员续卡记录','/member-cardextend-records.html',NULL,4,0,NULL,2,0),(7,'vip学员余额充值','/member-charge.html',NULL,4,0,NULL,3,0),(8,'vip学员充值记录','/member-charge-records.html',NULL,4,0,NULL,4,0),(9,'vip学员卡类型管理','/member-type.html',NULL,4,0,NULL,5,0),(10,'教师管理',NULL,'&#xe723;',0,0,NULL,1,0),(11,'教师列表','/coach.html',NULL,10,0,NULL,1,0),(12,'vip学员课程','/coach-subject.html',NULL,10,0,NULL,2,0),(13,'课程管理','','&#xe723;',0,0,NULL,1,0),(14,'课程列表','/subject.html',NULL,13,0,NULL,1,0),(15,'教学用具管理',NULL,'&#xe723;',0,0,NULL,1,0),(16,'教学用具信息','/equipment.html',NULL,15,0,NULL,1,0),(17,'物品遗失',NULL,'&#xe723;',0,0,NULL,1,0),(18,'物品归还','/loss.html',NULL,17,0,NULL,1,0),(19,'商品管理',NULL,'&#xe6ce;',0,0,NULL,1,0),(20,'商品列表','/goods-list.html',NULL,19,0,NULL,1,0),(21,'商品售卖','/goods-sales.html',NULL,19,0,NULL,2,0),(22,'信息统计',NULL,'&#xe6b4;',0,0,NULL,1,0),(23,'收入统计','/statistics.html','',22,0,NULL,1,0),(24,'系统管理',NULL,'&#xe6b8;',0,0,NULL,1,0),(25,'用户管理','/adminusers.html',NULL,24,0,NULL,1,0),(26,'角色管理','/adminroles.html',NULL,24,0,NULL,1,0),(28,'会员列表权限','/member.html',NULL,NULL,1,'1',NULL,0),(29,'会员到期权限','/memeber-expire.html',NULL,NULL,1,'1',NULL,0),(30,'系统首页权限','/index.html',NULL,NULL,1,'1,2,3,8',NULL,0),(31,'home页面权限','/home.html',NULL,NULL,1,'1,2,3,8',NULL,0),(32,'商品列表权限','/goods-list.html',NULL,NULL,1,'1,2',NULL,0),(33,'商品新增权限','/goods/add',NULL,NULL,1,'1',NULL,0),(34,'商品新增更新','/goods/update',NULL,NULL,1,'1,2,3',NULL,0),(36,'教练列表权限','/coach.html',NULL,NULL,1,'1,2,3,8',NULL,0),(37,'器材新增权限','/equipment/add',NULL,NULL,1,'1',NULL,0),(38,'器材新增权限','/equipment/add',NULL,NULL,1,'1',NULL,0),(39,'器材列表','/equipment.html',NULL,NULL,1,'1,3',NULL,0),(40,'日志管理','/systemlog.html','',24,0,NULL,NULL,0);

/*Table structure for table `adminrole` */

DROP TABLE IF EXISTS `adminrole`;

CREATE TABLE `adminrole` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `adminrole` */

insert  into `adminrole`(`id`,`roleName`,`remark`,`del`) values (1,'管理员','管理员','0'),(2,'前台','前台','0'),(3,'教师','课程交付实施','0'),(8,'财务','财务人员','1'),(10,'角色测试数据1','角色测试数据的描述1','1');

/*Table structure for table `adminuser` */

DROP TABLE IF EXISTS `adminuser`;

CREATE TABLE `adminuser` (
  `adminId` int NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `adminPassword` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `idcard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `del` int DEFAULT NULL,
  PRIMARY KEY (`adminId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `adminuser` */

insert  into `adminuser`(`adminId`,`adminName`,`adminPassword`,`phone`,`birthday`,`idcard`,`createTime`,`gender`,`del`) values (1,'admin','$2a$10$CoE/IQTses28EPoaNcIkj.aSNA2Qd1vgUcbL7U7tQ/fqxCTWgs2Le','13345657667','2023-05-29','55464564565465777','2023-05-29 09:45:50',0,0),(7,'bruce','$2a$10$12pTdhl/dOLNFdMNDD9m8.b1kX0mu61bUMgdWuYpP1AEYUEJxwtOu','13345657668','2023-05-29','55464564565465433','2023-05-29 09:45:52',1,0),(8,'jack','$2a$10$CoE/IQTses28EPoaNcIkj.aSNA2Qd1vgUcbL7U7tQ/fqxCTWgs2Le','13345678901','2202-11-11','426718199011111254','2023-05-29 10:42:31',1,0),(9,'kite','$2a$10$znrcTIghyt.w4/zMeNQCu.LxwcfY.AIg5uSfqRJqe8KAeJtX6s5XC','13345678906','2023-05-12','426718199011111266','2023-05-29 10:48:31',0,0),(10,'tom','$2a$10$2nAnrPas8QKybKxWjfg.Ee0f4hCsnPyzRxGO7.GH1SNCG76F85Yj.','13345678901','2023-05-17','426718199011111277','2023-05-29 11:27:49',1,0),(11,'测试账号2','$2a$10$YSZMnOrcM0jkJCJPoWlRNOnjPfnz4wxOO.miTao3O/2M.X8hclYF.','13456098712','2025-05-21','421087198812018788','2025-05-22 13:10:38',0,1),(12,'测试账户3','$2a$10$MPKCzdLVfD3MrEoTJD4A3eySMdMluvYsLo/K8xYqtU8bwY2UnAsdC','13456098713','2025-05-22','421087198912017809','2025-05-25 23:23:18',1,1);

/*Table structure for table `cardsrecord` */

DROP TABLE IF EXISTS `cardsrecord`;

CREATE TABLE `cardsrecord` (
  `id` int NOT NULL AUTO_INCREMENT,
  `memberId` int DEFAULT NULL,
  `typeId` int DEFAULT NULL,
  `money` float DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `daoqi` datetime DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `del` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `cardsrecord` */

insert  into `cardsrecord`(`id`,`memberId`,`typeId`,`money`,`remark`,`daoqi`,`createtime`,`del`) values (1,37,2,150,'准备办理vip会员课程','2029-07-01 00:00:00','2023-05-19 16:04:11',0),(2,37,1,500,'准备办理vip会员课程','2029-09-29 00:00:00','2023-05-19 16:30:07',0),(3,37,6,21,'准备办理vip会员课程','2029-09-30 00:00:00','2023-05-19 16:31:10',0),(4,37,5,52,'准备办理vip会员课程','2029-10-07 00:00:00','2023-05-19 16:34:29',0),(5,47,6,21,'','2024-01-22 00:00:00','2024-01-21 23:12:11',0),(6,37,3,1000,'','2030-09-29 00:00:00','2024-01-22 11:53:33',0),(7,38,3,1000,'年卡','2025-01-21 00:00:00','2024-01-22 12:10:56',0),(8,38,3,1000,'','2026-01-21 00:00:00','2024-01-22 12:25:58',0),(9,47,2,150,'','2024-02-21 00:00:00','2024-01-22 12:26:15',0),(10,48,1,500,'','2024-07-20 00:00:00','2024-01-22 13:28:48',0),(11,48,5,52,'','2024-07-27 00:00:00','2024-01-24 11:21:54',0),(12,50,1,500,'','2024-06-30 00:00:00','2024-04-01 11:01:45',0),(13,54,3,1000,'年卡续费','2026-05-13 00:00:00','2025-05-13 22:49:52',0);

/*Table structure for table `chargerecords` */

DROP TABLE IF EXISTS `chargerecords`;

CREATE TABLE `chargerecords` (
  `id` int NOT NULL AUTO_INCREMENT,
  `memberId` int DEFAULT NULL,
  `money` double DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `chargetime` datetime DEFAULT NULL,
  `del` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `chargerecords` */

insert  into `chargerecords`(`id`,`memberId`,`money`,`remark`,`chargetime`,`del`) values (1,45,160,'准备提升日常英语口语','2023-05-19 10:57:46',0),(2,45,160,'准备报雅思课程','2023-08-01 10:58:35',0),(3,45,10000,'准备报商务英语课程','2023-02-01 11:01:00',0),(4,44,800,'想考托福考试，将来出国','2023-05-19 14:11:41',0),(5,44,1,'想提升考研英语的成绩','2023-08-26 14:12:10',0),(6,34,5600,'想上体验试听课','2023-05-19 14:41:34',0),(7,35,10000,'准备给孩子报启蒙英语课程','2023-09-19 14:42:52',0),(8,47,6000,'','2024-01-21 23:11:26',0),(9,37,6000,'','2024-01-22 11:53:25',0),(10,38,6000,'','2024-01-22 12:10:40',0),(11,48,1000,'','2024-01-22 13:28:23',0),(12,51,1,'','2024-01-24 11:01:23',0),(13,50,11,'','2024-01-24 11:01:32',0),(14,48,30,'','2024-01-24 11:22:19',0),(15,52,10,'想提升英语水平，提升4 6级成绩','2024-03-27 15:15:23',0),(16,50,6000,'想提升口语水平','2024-03-27 15:25:33',0),(17,48,5000,'备战英语辩论赛','2024-03-27 15:26:00',0),(18,51,7000,'做跨境电商,想提升商务英语水平','2024-03-27 15:26:25',0),(19,52,10000,'准备出国备战雅思','2024-03-27 15:27:59',0),(20,48,3000,'学习英语,日常爱好而已','2024-03-27 18:08:56',0),(21,55,10000,'应对英语6级','2024-03-27 18:09:21',0),(22,54,8000,'','2024-03-27 18:11:31',0),(23,50,8000,'备战专业英语8级','2024-03-27 18:14:47',0),(24,48,10000,'提升自己英语口语水平','2024-03-27 18:15:26',0),(25,55,20000,'','2024-03-27 18:15:41',0),(26,52,10000,'','2024-03-27 18:21:27',0),(27,51,10000,'','2024-03-27 18:21:32',0),(28,48,10000,'','2024-04-01 12:17:50',0),(29,54,8000,'','2024-04-01 12:17:54',0),(30,50,10000,'','2024-04-01 12:17:58',0),(31,52,8000,'','2024-04-01 12:18:21',0),(32,51,8000,'','2024-04-01 12:18:25',0),(33,48,100000,'','2024-04-01 12:26:10',0),(34,54,100000,'','2024-04-01 12:26:15',0),(35,50,100000,'','2024-04-01 12:26:19',0),(36,55,100000,'','2024-02-01 12:26:23',0),(37,51,100000,'','2024-02-01 12:26:32',0),(38,52,100000,'','2024-04-01 12:26:36',0),(39,50,20,'充值','2025-04-15 14:52:35',0),(40,50,70,'缴费充值','2025-04-15 14:54:09',0),(41,50,50,'缴费充值','2025-04-15 14:56:34',0),(42,52,3350,'5月13日充值缴费','2025-05-13 21:49:51',0);

/*Table structure for table `coach` */

DROP TABLE IF EXISTS `coach`;

CREATE TABLE `coach` (
  `coachId` int NOT NULL AUTO_INCREMENT,
  `coachName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `coachPhone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `coachSex` int DEFAULT NULL,
  `coachAge` int DEFAULT NULL,
  `coachDate` date DEFAULT NULL,
  `teach` int DEFAULT NULL,
  `coachWages` double DEFAULT NULL,
  `coachAddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `coachStatic` int DEFAULT '0',
  `del` int DEFAULT NULL,
  PRIMARY KEY (`coachId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `coach` */

insert  into `coach`(`coachId`,`coachName`,`coachPhone`,`coachSex`,`coachAge`,`coachDate`,`teach`,`coachWages`,`coachAddress`,`coachStatic`,`del`) values (2,'eric','13243253432',0,22,'2023-01-02',2,6000,'北京外国语大学',0,0),(3,'james','13324332344',0,20,'2023-03-02',2,10000,'南京大学',0,0),(4,'miller','13324245453',1,25,'2023-03-10',3,18000,'斯坦福大学',0,0),(11,'steven','13342244112',1,18,'2023-08-01',2,16500,'加州大学伯克利分校',0,0),(12,'holks','13433324335',0,20,'2023-10-04',1,18000,'武汉大学',0,0),(14,'oscar','15299985622',1,35,'2020-04-02',5,10000,'上海交通大学',0,0),(16,'sunny','15785456231',1,22,'2020-05-06',2,12000,'北京大学',0,0),(17,'ketty','13356781555',1,45,'2023-05-22',3,15500,'深圳大学',0,0),(18,'curry','15307089909',0,23,'2024-01-24',8,20000,'帝国理工学院',0,0),(20,'guest','13545094482',1,22,'2025-04-25',5,3000,'华盛顿大学',0,1),(21,'测试教师数据1','13545678902',0,10,'2025-05-16',5,8000,'测试数据',0,1);

/*Table structure for table `equipment` */

DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `eqId` int NOT NULL AUTO_INCREMENT,
  `eqName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `eqText` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `del` int DEFAULT NULL,
  PRIMARY KEY (`eqId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `equipment` */

insert  into `equipment`(`eqId`,`eqName`,`eqText`,`del`) values (1,'课本教材','包括学生用书、教师用书、练习册等',0),(6,'课外读物','英语绘本、英语小说、分级阅读书籍等',0),(7,'笔记本','用于记录学生课堂笔记、完成作业等',0),(8,'便签纸','方便学生记录单词、短语或老师的提示',1),(9,'光盘','包含英语听力、口语、动画等教学内容',0),(10,'耳机','学生听英语音频时使用，保证学习效果',1),(11,'字母卡片','用于教授字母发音、拼写等',0),(12,'单词卡片','帮助学生记忆单词，可通过游戏等方式进行教学',1),(13,'拼图','与英语学习相关的拼图,如含有英语单词或图片的拼图，增加学习趣味性',1),(14,'白板笔','在白板上书写教学内容,需要定期更换',1),(15,'修正带','学生修改书写错误时使用',1),(16,'文件夹','用于整理和存放学生的作业、试卷等资料',1),(17,'书写工具','包括各类笔以及笔芯、墨水等消耗品',1),(18,'听力材料','如英语歌曲、电影、广播等音频材料以及配套的听力练习册',1),(19,'地球仪','在教授地理相关的英语知识时使用，帮助学生更好的理解地理位置和国家名称等',0),(20,'电子互动白板','结合了传统白板和计算机技术，教师可以直接在白板上进行书写、操作软件等。',0),(21,'a','a',0),(22,'222','222',0),(23,'测试器材数据','测试器材数据',1);

/*Table structure for table `goodinfo` */

DROP TABLE IF EXISTS `goodinfo`;

CREATE TABLE `goodinfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `goodsid` int DEFAULT NULL,
  `memberid` int DEFAULT NULL,
  `count` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `del` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `goodinfo` */

insert  into `goodinfo`(`id`,`goodsid`,`memberid`,`count`,`price`,`createtime`,`remark`,`del`) values (9,3,25,2,10,'2023-05-23 11:38:17','',0),(12,4,26,5,5,'2023-05-23 11:38:22','',0),(14,5,34,6,30,'2023-05-23 11:38:20','',0),(15,2,37,2,10,'2023-05-09 11:38:25','',0),(16,3,37,1,5,'2023-05-23 11:38:28','测试',0),(17,3,35,2,10,'2024-01-21 23:21:45','',0),(18,3,48,1,5,'2024-01-24 11:25:07','',0),(19,3,48,10,50,'2024-03-27 18:16:53','',1),(20,7,52,10,60,'2024-03-27 18:17:03','',1),(21,5,51,100,500,'2024-03-27 18:17:25','',1),(22,3,48,2,10,'2025-04-21 14:59:47','2瓶百事可乐',0),(23,7,52,20,120,'2025-05-15 23:21:15','测试消费数据',0);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goodsId` int NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `unit` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `unitPrice` double DEFAULT NULL,
  `sellPrice` double DEFAULT NULL,
  `inventory` int DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `del` int DEFAULT NULL,
  PRIMARY KEY (`goodsId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `goods` */

insert  into `goods`(`goodsId`,`goodsName`,`unit`,`unitPrice`,`sellPrice`,`inventory`,`remark`,`del`) values (2,'可口可乐','瓶',2,5,48,'1',1),(3,'百事可乐','瓶',3,6,120,'修改之后的数据',0),(4,'哈根达斯','盒',0.5,1,13,'',1),(5,'压缩饼干','块',2,5,135,'测试1',0),(7,'东方树叶','瓶',3,6,38,'',0),(8,'中性笔','支',10,20,0,'',0),(9,'英语作业本','本',3,5,100,'测试',0),(10,'CD光盘','个',30,50,133,'测试2',1),(11,'水彩笔','盒',23,32,23,'测试',0),(12,'英语故事绘本','本',23,32,23,'测试数据',0),(13,'矿泉水','瓶',2,2,23,'测试数据',0),(14,'冰红茶','瓶',23,32,23,'财务人员1',0),(15,'测试数据1','瓶',11,11,11,'测试数据',1),(16,'商品测试数据','瓶',2,4,22,'商品测试数据',1);

/*Table structure for table `loos` */

DROP TABLE IF EXISTS `loos`;

CREATE TABLE `loos` (
  `loosId` int NOT NULL AUTO_INCREMENT,
  `loosName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `loosImages` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `loosAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `loosjdate` datetime DEFAULT NULL,
  `loosStatus` int DEFAULT NULL,
  `scavenger` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `scavengerPhone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `receiveName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `receivePhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `loosldate` datetime DEFAULT NULL,
  `admin` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `del` int DEFAULT NULL,
  PRIMARY KEY (`loosId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `loos` */

insert  into `loos`(`loosId`,`loosName`,`loosImages`,`loosAddress`,`loosjdate`,`loosStatus`,`scavenger`,`scavengerPhone`,`receiveName`,`receivePhone`,`loosldate`,`admin`,`del`) values (1,'车钥匙','1','教室1','2020-04-10 00:00:00',1,'张三','13355660000','22','25525','2020-04-12 00:00:00','admin',0),(6,'手机','1','前台','2020-05-01 00:00:00',1,'李四','15299952320','张宏','15899965478','2020-05-02 00:00:00','admin',0),(7,'钱包','1','会议室','2020-05-01 00:00:00',1,'小明','15966325478','张三','13345667788','2023-05-23 00:00:00','匿名',0),(8,'书包','1','教室2','2020-05-02 00:00:00',1,'刘伟','15326587548','张三','13345667788','2023-05-23 00:00:00','匿名',0),(9,'苹果手机','1','学校大门口','2023-05-23 00:00:00',1,'小张','13345667788','张三','13345667788','2023-05-23 00:00:00','匿名',0),(10,'耳机','1','教室3','2023-05-23 00:00:00',1,'小李','13345667777','张三','13345667788','2023-05-16 00:00:00','匿名',0),(11,'鼠标','1','2楼餐厅','2023-05-16 00:00:00',0,'小张','13345667788',NULL,NULL,NULL,'匿名',0),(12,'英语书','1','前台','2023-11-21 00:00:00',1,'王五','18023497989','18032232341',NULL,'2024-01-20 00:00:00','匿名',0),(13,'CD光盘','1','教室4','2024-01-11 00:00:00',1,'赵四','18023497989','18032232341',NULL,'2024-01-24 00:00:00','匿名',0),(15,'遗失物品测试数据','1','测试数据地点','2025-05-18 00:00:00',1,'测试人姓名','13456789092','15555555555',NULL,'2025-05-17 00:00:00','匿名',0);

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `memberId` int NOT NULL AUTO_INCREMENT,
  `memberName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `memberPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `memberSex` int DEFAULT NULL,
  `memberAge` int DEFAULT NULL,
  `memberTypes` int DEFAULT NULL,
  `nenberDate` date DEFAULT NULL,
  `birthday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `memberStatic` int DEFAULT NULL,
  `memberbalance` float DEFAULT '0',
  `memberxufei` date DEFAULT NULL,
  `del` int DEFAULT NULL,
  PRIMARY KEY (`memberId`) USING BTREE,
  KEY `fk-member-membertype` (`memberTypes`) USING BTREE,
  CONSTRAINT `fk-member-membertype` FOREIGN KEY (`memberTypes`) REFERENCES `membertype` (`typeId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `member` */

insert  into `member`(`memberId`,`memberName`,`memberPhone`,`memberSex`,`memberAge`,`memberTypes`,`nenberDate`,`birthday`,`memberStatic`,`memberbalance`,`memberxufei`,`del`) values (25,'刘建','13456789087',1,23,5,'2020-04-06','08-24',1,390,'2020-04-13',1),(26,'汤姆','15266668585',1,24,6,'2020-04-06','08-07',2,-5,'2023-05-19',1),(34,'王泽明','17858966255',1,19,5,'2020-04-06','05-08',1,5970,'2020-04-10',1),(35,'小红','18799256874',0,23,6,'2020-04-07','04-08',2,9953,'2023-05-19',1),(37,'王芳','15299950487',0,22,1,'2023-07-01','03-07',2,5082,'2030-09-29',1),(38,'李明','15699588547',1,22,3,'2020-04-07','09-21',2,4000,'2026-01-21',1),(42,'jerry','15266528547',1,25,6,'2020-04-08','04-08',2,-7,'2020-04-09',1),(43,'方蓝','13456789876',0,22,5,'2020-04-10','04-29',2,0,'2020-05-10',1),(44,'赵静','15288888888',0,25,3,'2020-04-12','04-22',2,801,'2021-05-03',1),(45,'oscar','18566584785',1,35,3,'2020-05-03','05-03',2,10288,'2021-05-03',1),(46,'小张','13356781901',0,13,6,'2023-05-18','05-19',1,0,'2023-05-19',1),(47,'小强','13356781908',1,45,3,'2023-05-19','05-21',1,5829,'2024-05-18',1),(48,'小飞','18969898966',1,34,2,'2024-01-22','01-29',1,104883,'2024-02-21',0),(49,'eric','15307082222',1,23,3,'2024-01-24','10-09',1,0,'2025-01-23',0),(50,'黄永孟','15188297532',1,23,2,'2024-01-24','01-03',1,104651,'2024-06-30',0),(51,'王康','15188297532',1,23,6,'2024-01-24','01-01',1,90101,'2024-01-25',0),(52,'小米','15325353255',1,24,7,'2024-03-27','03-04',1,103880,'2024-03-29',0),(53,'小赵','15325353255',1,24,3,'2024-03-27','03-04',1,0,'2025-03-27',1),(54,'蒋万安','13453246455',1,24,1,'2024-03-27','01-11',1,105000,'2026-05-13',0),(55,'赵丽颖','13453246789',1,24,2,'2024-03-27','01-11',1,107150,'2024-04-26',0),(56,'测试数据2','13456098766',1,29,1,'2025-04-15','04-13',1,0,'2025-07-14',0),(57,'学员测试数据','13234567809',1,22,1,'2025-05-13','05-12',1,0,'2025-08-11',0);

/*Table structure for table `membertype` */

DROP TABLE IF EXISTS `membertype`;

CREATE TABLE `membertype` (
  `typeId` int NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `typeciShu` int DEFAULT NULL,
  `typeDay` int DEFAULT NULL,
  `typemoney` float DEFAULT NULL,
  `typeDel` int DEFAULT NULL,
  PRIMARY KEY (`typeId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `membertype` */

insert  into `membertype`(`typeId`,`typeName`,`typeciShu`,`typeDay`,`typemoney`,`typeDel`) values (1,'季卡',10,90,500,0),(2,'月卡',0,30,150,0),(3,'年卡',0,365,1000,0),(5,'周卡',0,7,52,0),(6,'日卡',1,1,21,0),(7,'零时卡',2,2,12,0),(22,'测试数据会员卡11',1001,1001,10001,1),(23,'测试数据会员卡2',1001,1001,10001,1);

/*Table structure for table `privatecoachinfo` */

DROP TABLE IF EXISTS `privatecoachinfo`;

CREATE TABLE `privatecoachinfo` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `memberid` int DEFAULT NULL,
  `coachid` int DEFAULT NULL,
  `subjectid` int DEFAULT NULL,
  `count` int DEFAULT NULL,
  `countprice` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `state` int DEFAULT NULL,
  `remark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `admin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'asdf',
  `del` int DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  KEY `fk_pri_subject` (`subjectid`) USING BTREE,
  KEY `fk_pri_coach` (`coachid`) USING BTREE,
  KEY `fk_pri_member` (`memberid`) USING BTREE,
  CONSTRAINT `fk_pri_coach` FOREIGN KEY (`coachid`) REFERENCES `coach` (`coachId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_pri_member` FOREIGN KEY (`memberid`) REFERENCES `member` (`memberId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_pri_subject` FOREIGN KEY (`subjectid`) REFERENCES `subject` (`subId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `privatecoachinfo` */

insert  into `privatecoachinfo`(`pid`,`memberid`,`coachid`,`subjectid`,`count`,`countprice`,`date`,`state`,`remark`,`admin`,`del`) values (3,25,4,1,1,200,'2020-04-07',1,'fg','匿名',0),(13,26,14,2,22,660,'2020-04-15',1,'','asdf',0),(14,38,14,4,25,16650,'2020-04-16',1,'','asdf',0),(16,34,11,1,5,150,'2020-05-02',1,'','asdf',0),(17,25,4,5,5,100,'2020-05-01',1,'','asdf',0),(18,37,2,5,9,180,'2023-05-09',1,'test','匿名',0),(19,34,2,6,9,450,'2023-05-04',1,'','匿名',1),(20,45,3,1,1,30,'2024-01-22',1,'','匿名',0),(21,35,2,1,1,30,'2024-01-02',1,'','匿名',1),(22,48,18,1,1,30,'2024-01-24',1,'','匿名',0),(23,48,18,4,1,50,'2024-01-24',1,'','匿名',0),(24,51,3,2,10,350,'2024-02-02',1,'','匿名',0),(25,50,4,4,10,500,'2024-03-02',1,'','匿名',0),(26,48,4,4,10,500,'2024-03-06',1,'','匿名',0),(27,52,16,1,10,300,'2024-03-10',1,'','匿名',0),(28,48,18,6,10,500,'2024-03-08',1,'','匿名',0),(29,50,2,2,100,3500,'2024-02-02',1,'','匿名',0),(30,48,18,1,100,3000,'2024-01-04',1,'','匿名',0),(31,51,4,2,50,1750,'2024-02-08',1,'','匿名',0),(32,52,18,4,150,7500,'2024-03-02',1,'','匿名',0),(33,55,11,2,200,7000,'2024-02-09',1,'','匿名',0),(34,55,18,5,100,2000,'2024-02-09',1,'','匿名',0),(35,55,18,2,10,350,'2024-02-08',1,'','匿名',0),(36,48,3,1,100,3000,'2024-02-08',1,'','匿名',0),(37,51,16,2,100,3500,'2024-02-03',1,'','匿名',0),(38,52,18,1,100,3000,'2024-02-10',1,'','匿名',1),(39,50,14,1,100,3000,'2024-03-02',1,'','匿名',0),(40,48,12,2,10,350,'2024-04-02',1,'','匿名',0),(41,48,3,2,20,2400,'2024-04-02',1,'','匿名',0),(42,48,3,2,20,2400,'2024-04-01',1,'','匿名',0),(43,48,3,2,40,4800,'2024-04-01',1,'','匿名',0),(44,50,4,2,100,12000,'2024-04-01',1,'','匿名',1),(45,51,4,4,80,9600,'2024-02-08',0,'','匿名',0),(46,51,12,4,60,7200,'2024-02-09',1,'','匿名',0),(47,55,14,5,90,13500,'2024-01-01',1,'','匿名',0),(48,54,16,1,100,10000,'2024-01-21',1,'','匿名',0),(49,52,2,6,70,10500,'2024-03-07',1,'','匿名',1),(50,51,17,2,100,12000,'2024-02-08',1,'','匿名',0),(51,48,2,1,1,6500,'2025-04-20',1,'缴费充值','匿名',0),(52,52,2,2,1,6000,'2025-05-15',1,'报课考研英语','匿名',0);

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `subId` int NOT NULL AUTO_INCREMENT,
  `subname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sellingPrice` double DEFAULT NULL,
  `del` int DEFAULT NULL,
  PRIMARY KEY (`subId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `subject` */

insert  into `subject`(`subId`,`subname`,`sellingPrice`,`del`) values (1,'基础英语',6500,0),(2,'考研英语',6000,0),(4,'高级英语',8000,0),(5,'商务英语基础',5500,0),(6,'幼儿英语启蒙',4500,0),(7,'日常口语',3800,1),(8,'中级英语',7000,0),(9,'雅思',14000,1),(10,'托福',14000,1),(11,'测试数据',1000,1),(12,'课程测试数据2',1002,1);

/*Table structure for table `systemlog` */

DROP TABLE IF EXISTS `systemlog`;

CREATE TABLE `systemlog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `logtype` int DEFAULT NULL,
  `nowuser` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `methodname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `logdescription` text CHARACTER SET utf8 COLLATE utf8_bin,
  `methodparms` text CHARACTER SET utf8 COLLATE utf8_bin,
  `methodtype` text CHARACTER SET utf8 COLLATE utf8_bin,
  `methodteturn` text CHARACTER SET utf8 COLLATE utf8_bin,
  `exmessage` text CHARACTER SET utf8 COLLATE utf8_bin,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=331 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `systemlog` */

insert  into `systemlog`(`id`,`logtype`,`nowuser`,`createtime`,`methodname`,`logdescription`,`methodparms`,`methodtype`,`methodteturn`,`exmessage`) values (1,0,'admin','2025-06-01 11:00:23','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(2,0,'admin','2025-06-01 11:00:23','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(3,0,'admin','2025-06-01 11:04:16','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(4,0,'admin','2025-06-01 11:04:16','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(5,0,'admin','2025-06-01 11:07:14','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(6,0,'admin','2025-06-01 11:07:14','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(7,0,'admin','2025-06-01 11:08:23','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(8,0,'admin','2025-06-01 11:08:23','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(9,0,'admin','2025-06-01 11:10:35','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(10,0,'admin','2025-06-01 11:10:35','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(11,0,'admin','2025-06-01 11:10:49','add','新增商品方法','传入参数 1 个:[ Goods(goodsId=null, goodsName=98089, unit=个, unitPrice=23.0, sellPrice=32.0, inventory=23, remark=财务人员1, del=null) ]','com.healthclub.utils.DataResults','{\"code\":200,\"msg\":\"请求成功\"}',NULL),(12,0,'admin','2025-06-01 11:12:45','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(13,0,'admin','2025-06-01 11:12:46','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(14,0,'admin','2025-06-01 11:12:56','add','新增商品方法','传入参数 1 个:[ Goods(goodsId=null, goodsName=23, unit=个, unitPrice=23.0, sellPrice=32.0, inventory=23, remark=财务人员1, del=null) ]','com.healthclub.utils.DataResults','{\"code\":200,\"msg\":\"请求成功\"}',NULL),(15,0,'admin','2025-06-01 11:24:40','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(16,0,'admin','2025-06-01 11:24:40','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(17,0,'admin','2025-06-01 11:26:30','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(18,0,'admin','2025-06-01 11:26:30','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(19,0,'admin','2025-06-01 11:26:37','add','新增器材信息','传入参数 1 个:[ Equipment(eqId=null, eqName=11, eqText=3333, del=null) ]','com.healthclub.utils.DataResults','{\"code\":200,\"msg\":\"请求成功\"}',NULL),(20,0,'admin','2025-06-01 11:27:50','add','新增器材信息','传入参数 1 个:[ Equipment(eqId=null, eqName=3451, eqText=3451, del=null) ]','com.healthclub.utils.DataResults','{\"code\":200,\"msg\":\"请求成功\"}',NULL),(21,0,'bruce','2025-06-01 11:30:24','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(22,0,'bruce','2025-06-01 11:30:24','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(23,0,'bruce','2025-06-01 11:32:57','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(24,0,'bruce','2025-06-01 11:32:57','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(25,0,'bruce','2025-06-01 11:34:04','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(26,0,'bruce','2025-06-01 11:34:05','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(27,0,'bruce','2025-06-01 11:36:58','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(28,0,'bruce','2025-06-01 11:36:59','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(29,0,'admin','2025-06-01 11:38:07','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(30,0,'admin','2025-06-01 11:38:07','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(31,0,'admin','2023-06-01 11:38:15','add','新增器材信息','传入参数 1 个:[ Equipment(eqId=null, eqName=43543, eqText=345345, del=null) ]','com.healthclub.utils.DataResults','{\"code\":200,\"msg\":\"请求成功\"}',NULL),(32,0,'admin','2025-06-01 11:41:51','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(33,0,'admin','2025-06-01 11:41:51','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(34,0,'admin','2025-06-01 11:50:30','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(35,0,'admin','2025-06-01 11:50:30','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(36,0,'admin','2025-06-01 11:52:01','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(37,0,'admin','2025-06-01 11:52:02','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(38,0,'admin','2025-06-01 12:00:03','toIndexHtml','跳转index.html方法','','java.lang.String','\"index\"',NULL),(39,0,'admin','2025-06-01 12:00:04','toHomeHtml','跳转home.html方法','','java.lang.String','\"home\"',NULL),(305,0,'admin','2025-06-01 18:29:55','toGoodsListHtml','跳转goods-list方法','','java.lang.String','\"goods-list\"',NULL),(306,0,'admin','2025-06-01 18:33:49','toIndex','跳转index.html方法','','java.lang.String','\"index\"',NULL),(307,0,'admin','2025-06-01 18:33:49','toHome','跳转home.html方法','','java.lang.String','\"home\"',NULL),(308,0,'admin','2025-06-01 18:45:16','toIndex','跳转index.html方法','','java.lang.String','\"index\"',NULL),(309,0,'admin','2025-06-01 18:45:16','toHome','跳转home.html方法','','java.lang.String','\"home\"',NULL),(310,0,'admin','2025-06-01 18:45:18','toGoodsListHtml','跳转goods-list方法','','java.lang.String','\"goods-list\"',NULL),(311,0,'admin','2025-06-01 18:47:34','toIndex','跳转index.html方法','','java.lang.String','\"index\"',NULL),(312,0,'admin','2025-06-01 18:47:35','toHome','跳转home.html方法','','java.lang.String','\"home\"',NULL),(313,1,'admin','2025-06-01 18:47:37','toGoodsListHtml','跳转goods-list方法','','java.lang.String',NULL,'/ by zero'),(314,0,'admin','2025-06-01 18:49:13','toIndex','跳转index.html方法','','java.lang.String','\"index\"',NULL),(315,0,'admin','2025-06-01 18:49:14','toHome','跳转home.html方法','','java.lang.String','\"home\"',NULL),(316,1,'admin','2025-06-01 18:49:17','toGoodsListHtml','跳转goods-list方法','','java.lang.String',NULL,'/ by zero'),(317,0,'admin','2025-06-01 18:50:53','toIndex','跳转index.html方法','','java.lang.String','\"index\"',NULL),(318,0,'admin','2025-06-01 18:50:53','toHome','跳转home.html方法','','java.lang.String','\"home\"',NULL),(319,1,'admin','2025-06-01 18:51:17','toGoodsListHtml','跳转goods-list方法','','java.lang.String',NULL,'/ by zero'),(320,0,'admin','2025-06-01 18:52:45','toIndex','跳转index.html方法','','java.lang.String','\"index\"',NULL),(321,0,'admin','2025-06-01 18:52:45','toHome','跳转home.html方法','','java.lang.String','\"home\"',NULL),(322,0,'admin','2025-06-02 19:17:05','toIndexPage','跳转到首页的方法','','java.lang.String','\"index\"',NULL),(323,0,'admin','2025-06-02 19:19:03','toIndexPage','跳转到首页的方法','','java.lang.String','\"index\"',NULL),(324,0,'admin','2025-06-02 19:19:08','toGoodsListPage','跳转到商品页面的方法','','java.lang.String','\"goods-list\"',NULL),(325,0,'admin','2025-06-02 19:24:18','toIndexPage','跳转到首页的方法','','java.lang.String','\"index\"',NULL),(326,0,'admin','2025-06-02 19:28:12','toIndexPage','跳转到首页的方法','','java.lang.String','\"index\"',NULL),(327,0,'admin','2025-06-02 19:34:11','toIndexPage','跳转到首页的方法','','java.lang.String','\"index\"',NULL),(328,0,'admin','2025-06-02 21:39:50','toIndexPage','跳转到首页的方法','','java.lang.String','\"index\"',NULL),(329,0,'admin','2025-06-02 21:43:14','toGoodsListPage','跳转到商品页面的方法','','java.lang.String','\"goods-list\"',NULL),(330,0,'jack','2025-06-02 21:45:28','toIndexPage','跳转到首页的方法','','java.lang.String','\"index\"',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
