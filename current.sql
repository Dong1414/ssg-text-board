-- MariaDB dump 10.17  Distrib 10.4.14-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: textBoard
-- ------------------------------------------------------
-- Server version	10.4.14-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `textBoard`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `textBoard` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `textBoard`;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `title` char(200) NOT NULL,
  `body` text NOT NULL,
  `memberId` int(10) unsigned NOT NULL,
  `boardId` int(10) unsigned NOT NULL,
  `hit` int(10) unsigned NOT NULL,
  `likesCount` int(10) unsigned NOT NULL,
  `commentsCount` int(10) unsigned NOT NULL,
  `hitCount` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'2020-12-18 09:02:49','2021-01-06 20:47:30','공지사항😀','# 공지사항\r\n안녕하세요.\r\n이 사이트는 저의 글 연재 공간입니다.\r\n\r\n---\r\n\r\n# 이 사이트의 특징\r\n- A\r\n- B\r\n- C',2,1,3,0,1,0),(2,'2020-12-18 09:02:49','2021-01-06 20:47:29','[Java] 기본 문법😀','# 자바기본문법\r\n```java\r\nint a = 10;\r\nint b = 20;\r\nint c = a + b;\r\n```',2,2,2,0,0,0),(3,'2020-12-18 09:02:49','2021-01-06 20:47:29','[Java] 조건문😀','# 조건문 ( if ) 😀\r\n```java\r\nint a = 10;\r\n\r\nif(a == 10){ //a가 10이면 출력\r\n    System.out.println(a); \r\n}\r\n```',2,2,2,0,0,0),(4,'2020-12-18 11:07:44','2021-01-06 20:47:28','[Java] 두 숫자 사이의 합 구하기😀','# 정수 a, b 가 주어질 경우 두 숫자 사이의 합 구하기 😀\r\n```java\r\nMath.min(a,b) //최소값\r\nMath.max(a,b) //최대값\r\n//등차수열\r\nprivate long sumAtob(long a((최대), long b(최소)){ return (b -1 a+ 1) * ( a + b ) / 2; } // \r\n}\r\n```',2,2,1,0,0,0),(5,'2020-12-18 11:10:40','2021-01-06 20:47:28','[Java] 진수 변환😀','# 2진수, 8진수, 16진수,10진수 변환 😀\r\n```java\r\nimport java.util.Scanner;\r\n\r\npublic class Main {\r\n\r\n	public static void main(String[] args) {\r\n		\r\n		Scanner scan = new Scanner(System.in);\r\n		int num = scan.nextInt();\r\n		\r\n		String n2 = Integer.toBinaryString(num); // 2진수\r\n		String n8 = Integer.toOctalString(num); // 8진수\r\n		String n16 = Integer.toHexString(num); // 16진수\r\n		\r\n		\r\n		System.out.println(\"2진수: \" + n2);\r\n		System.out.println(\"8진수: \" + n8);\r\n		System.out.println(\"16진수: \" + n16);\r\n		\r\n        \r\n        String num8 = scan.nextLine(); // 8진수 입력받기\r\n     	int n10 = Integer.parseInt(num, 8);   //8진수 10진수로 변환, 입력에 따라 2,8,16\r\n        System.out.println(n10);\r\n	}\r\n}}\r\n```',2,2,3,0,0,0),(6,'2020-12-18 11:13:05','2021-01-06 20:47:27','[Java] 공백 제거😀','# .trim() 공백제거 ( Scanner 메서드) 😀\r\n```java \r\nScanner scan = new Scanner(System.in);\r\n\r\nString title = scan.nextLine().trim();\r\n\r\n```',2,2,1,0,0,0),(7,'2020-12-29 09:19:19','2021-01-06 20:47:27','요즘 날씨 너무 춥다..😭','코로나 다 물러가라 제발..😭',2,3,0,0,1,0),(8,'2020-12-29 09:34:50','2021-01-06 20:47:26','[Java] 일, 월 입력받아 요일 구하기','# .요일 구하기 😀\r\n```java \r\nclass Solution {\r\n    public String solution(int a, int b) {\r\n        int [] day = {31,29,31,30,31,30,31,31,30,31,30,31};\r\n        String [] month = {\"THU\",\"FRI\", \"SAT\",\"SUN\", \"MON\", \"TUE\", \"WED\"};\r\n        \r\n        int total = 0;\r\n        \r\n        for(int i = 0; i < a-1; i++){\r\n            total += day[i];\r\n        }\r\n        \r\n        total += b;\r\n        System.out.println(total);\r\n        int week = total % 7;\r\n        return month[week];\r\n    }\r\n}\r\n\r\n```\r\n2016년 기준이고, 해당 년도의 월마다 일수를 배열에 저장.\r\n\r\n \r\n\r\n요일은 2016.01.01의 요일 금요일에서 하루 뺀 값으로 배열에 저장. ( 나중에 % 7 했을때 1이 나오면 금요일부터 출력 )\r\n\r\n \r\n\r\n요일을 구하는 공식은 \r\n\r\nex) 2016년 5월 24일\r\n\r\n   - (1~4월 까지의 일 수 + 현재 달의 일 수) % 7 ',2,2,0,0,0,0),(9,'2020-12-29 09:36:57','2021-01-06 20:47:26','[Java]BufferedReader, BufferedWriter 빠른 입출력 클래스','# .입력 BufferedReader 😀\r\n```java \r\nBufferedReader br = new BufferedReader(new InputStreamReader(System.in));        \r\nint n = Integer.parseInt(br.readLine());\r\n\r\nString s = br.readLine();\r\nString[] b = s.split(\" \");\r\nInteger.valueOf(b[0]);  //String으로 받아서 나눠서 바꿔서 출력\r\n\r\n```\r\n무조건 String형으로 들어오기 때문에 형변환 해줘야함\r\n\r\n// 코드업 풀 때 바로 형변환 안됐다 String으로 받아서 변환했음.\r\n\r\n\r\n# .출력 BufferedWriter 😀\r\n```java \r\nBufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); \r\nbw.write(\"exam\\n\");\r\n	bw.flush(); 꼭 입력해야하는듯?\r\n\r\n```\r\n명령어만 좀 다르고 사용 방식은 비슷함.\r\n\r\nSystem.out.println()보다 더 빠르다.',2,2,0,1,2,0),(10,'2021-01-06 20:42:57','2021-01-06 20:47:26','[Java] 소문자, 대문자 변환하기','# .소문자, 대문자 변환하기\r\n```java \r\npublic class Main {\r\n\r\n	public static void main(String[] args) {\r\n\r\n\r\n		String s = \"s\";\r\n		String d = \"D\";\r\n		\r\n		System.out.println(s.toUpperCase()); // 소문자 -> 대문자\r\n		System.out.println(d.toLowerCase()); // 대문자 -> 소문자\r\n		\r\n	}\r\n}\r\n\r\n```\r\n소문자 대문자 변환방법\r\n\r\ntoUpperCase 소 -> 대\r\ntoLowerCase 대 -> 소',2,2,0,0,0,0);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `name` char(20) NOT NULL,
  `code` char(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'2020-12-18 09:02:49','2020-12-18 09:02:49','공지사항','notice'),(2,'2020-12-18 09:02:49','2020-12-18 09:02:49','IT','it'),(3,'2020-12-29 09:15:26','2020-12-29 09:15:29','자유','free');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ga4DataPagePath`
--

DROP TABLE IF EXISTS `ga4DataPagePath`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ga4DataPagePath` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `pagePath` char(100) NOT NULL,
  `hit` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pagePath` (`pagePath`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ga4DataPagePath`
--

LOCK TABLES `ga4DataPagePath` WRITE;
/*!40000 ALTER TABLE `ga4DataPagePath` DISABLE KEYS */;
INSERT INTO `ga4DataPagePath` VALUES (111,'2021-01-06 19:40:23','2021-01-06 19:40:23','/',50),(112,'2021-01-06 19:40:23','2021-01-06 19:40:23','/index.html',13),(113,'2021-01-06 19:40:23','2021-01-06 19:40:23','/it-article-detail-6.html',12),(114,'2021-01-06 19:40:23','2021-01-06 19:40:23','/stat.html',10),(115,'2021-01-06 19:40:23','2021-01-06 19:40:23','/article-list-it-1.html',8),(116,'2021-01-06 19:40:23','2021-01-06 19:40:23','/article-list-notice-1.html',6),(117,'2021-01-06 19:40:23','2021-01-06 19:40:23','/it-article-detail-4.html',3),(118,'2021-01-06 19:40:23','2021-01-06 19:40:23','/it-article-detail-5.html',3),(119,'2021-01-06 19:40:23','2021-01-06 19:40:23','/it-article-detail-9.html',3),(120,'2021-01-06 19:40:23','2021-01-06 19:40:23','/notice-article-detail-1.html',3);
/*!40000 ALTER TABLE `ga4DataPagePath` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `loginId` char(30) NOT NULL,
  `loginPw` varchar(50) NOT NULL,
  `name` char(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2020-12-18 09:02:49','2020-12-18 09:02:49','test1','test1','테스터1'),(2,'2020-12-18 09:02:49','2020-12-18 09:02:49','1','1','호말');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reple`
--

DROP TABLE IF EXISTS `reple`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reple` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `write` char(100) NOT NULL,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `articleId` int(100) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reple`
--

LOCK TABLES `reple` WRITE;
/*!40000 ALTER TABLE `reple` DISABLE KEYS */;
/*!40000 ALTER TABLE `reple` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-06 20:49:29
