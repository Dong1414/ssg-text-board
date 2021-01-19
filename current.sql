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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'2020-12-18 09:02:49','2021-01-14 20:45:12','공지사항😀','# 공지사항\r\n안녕하세요.\r\n이 사이트는 저의 글 연재 공간입니다.\r\n\r\n---\r\n\r\n# 이 사이트의 특징\r\n- A\r\n- B\r\n- C',2,1,3,0,1,0),(2,'2020-12-18 09:02:49','2021-01-14 20:45:12','[Java] 기본 문법😀','# 자바기본문법\r\n```java\r\nint a = 10;\r\nint b = 20;\r\nint c = a + b;\r\n```',2,2,2,0,0,0),(3,'2020-12-18 09:02:49','2021-01-14 20:45:11','[Java] 조건문😀','# 조건문 ( if ) 😀\r\n```java\r\nint a = 10;\r\n\r\nif(a == 10){ //a가 10이면 출력\r\n    System.out.println(a); \r\n}\r\n```',2,2,2,0,0,0),(4,'2020-12-18 11:07:44','2021-01-14 20:45:10','[Java] 두 숫자 사이의 합 구하기😀','# 정수 a, b 가 주어질 경우 두 숫자 사이의 합 구하기 😀\r\n```java\r\nMath.min(a,b) //최소값\r\nMath.max(a,b) //최대값\r\n//등차수열\r\nprivate long sumAtob(long a((최대), long b(최소)){ return (b -1 a+ 1) * ( a + b ) / 2; } // \r\n}\r\n```',2,2,1,0,0,0),(5,'2020-12-18 11:10:40','2021-01-14 20:45:10','[Java] 진수 변환😀','# 2진수, 8진수, 16진수,10진수 변환 😀\r\n```java\r\nimport java.util.Scanner;\r\n\r\npublic class Main {\r\n\r\n	public static void main(String[] args) {\r\n		\r\n		Scanner scan = new Scanner(System.in);\r\n		int num = scan.nextInt();\r\n		\r\n		String n2 = Integer.toBinaryString(num); // 2진수\r\n		String n8 = Integer.toOctalString(num); // 8진수\r\n		String n16 = Integer.toHexString(num); // 16진수\r\n		\r\n		\r\n		System.out.println(\"2진수: \" + n2);\r\n		System.out.println(\"8진수: \" + n8);\r\n		System.out.println(\"16진수: \" + n16);\r\n		\r\n        \r\n        String num8 = scan.nextLine(); // 8진수 입력받기\r\n     	int n10 = Integer.parseInt(num, 8);   //8진수 10진수로 변환, 입력에 따라 2,8,16\r\n        System.out.println(n10);\r\n	}\r\n}}\r\n```',2,2,3,0,0,0),(6,'2020-12-18 11:13:05','2021-01-14 20:45:09','[Java] 공백 제거😀','# .trim() 공백제거 ( Scanner 메서드) 😀\r\n```java \r\nScanner scan = new Scanner(System.in);\r\n\r\nString title = scan.nextLine().trim();\r\n\r\n```',2,2,1,0,0,0),(7,'2020-12-29 09:19:19','2021-01-14 20:45:09','요즘 날씨 너무 춥다..😭','코로나 다 물러가라 제발..😭',2,3,0,0,1,0),(8,'2020-12-29 09:34:50','2021-01-14 20:45:08','[Java] 일, 월 입력받아 요일 구하기','# .요일 구하기 😀\r\n```java \r\nclass Solution {\r\n    public String solution(int a, int b) {\r\n        int [] day = {31,29,31,30,31,30,31,31,30,31,30,31};\r\n        String [] month = {\"THU\",\"FRI\", \"SAT\",\"SUN\", \"MON\", \"TUE\", \"WED\"};\r\n        \r\n        int total = 0;\r\n        \r\n        for(int i = 0; i < a-1; i++){\r\n            total += day[i];\r\n        }\r\n        \r\n        total += b;\r\n        System.out.println(total);\r\n        int week = total % 7;\r\n        return month[week];\r\n    }\r\n}\r\n\r\n```\r\n2016년 기준이고, 해당 년도의 월마다 일수를 배열에 저장.\r\n\r\n \r\n\r\n요일은 2016.01.01의 요일 금요일에서 하루 뺀 값으로 배열에 저장. ( 나중에 % 7 했을때 1이 나오면 금요일부터 출력 )\r\n\r\n \r\n\r\n요일을 구하는 공식은 \r\n\r\nex) 2016년 5월 24일\r\n\r\n   - (1~4월 까지의 일 수 + 현재 달의 일 수) % 7 ',2,2,0,0,0,0),(9,'2020-12-29 09:36:57','2021-01-14 20:45:08','[Java]BufferedReader, BufferedWriter 빠른 입출력 클래스','# .입력 BufferedReader 😀\r\n```java \r\nBufferedReader br = new BufferedReader(new InputStreamReader(System.in));        \r\nint n = Integer.parseInt(br.readLine());\r\n\r\nString s = br.readLine();\r\nString[] b = s.split(\" \");\r\nInteger.valueOf(b[0]);  //String으로 받아서 나눠서 바꿔서 출력\r\n\r\n```\r\n무조건 String형으로 들어오기 때문에 형변환 해줘야함\r\n\r\n// 코드업 풀 때 바로 형변환 안됐다 String으로 받아서 변환했음.\r\n\r\n\r\n# .출력 BufferedWriter 😀\r\n```java \r\nBufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); \r\nbw.write(\"exam\\n\");\r\n	bw.flush(); 꼭 입력해야하는듯?\r\n\r\n```\r\n명령어만 좀 다르고 사용 방식은 비슷함.\r\n\r\nSystem.out.println()보다 더 빠르다.',2,2,0,1,2,0),(10,'2021-01-06 20:42:57','2021-01-14 20:45:07','[Java] 소문자, 대문자 변환하기','# .소문자, 대문자 변환하기\r\n```java \r\npublic class Main {\r\n\r\n	public static void main(String[] args) {\r\n\r\n\r\n		String s = \"s\";\r\n		String d = \"D\";\r\n		\r\n		System.out.println(s.toUpperCase()); // 소문자 -> 대문자\r\n		System.out.println(d.toLowerCase()); // 대문자 -> 소문자\r\n		\r\n	}\r\n}\r\n\r\n```\r\n소문자 대문자 변환방법\r\n\r\ntoUpperCase 소 ->
 대\r\ntoLowerCase 대 -> 소',2,2,0,0,0,0),(11,'2021-01-14 20:41:19','2021-01-14 20:45:06','[java}[programmers] 모의고사','# .완전탐색\r\n\r\n문제 \r\n\r\n수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.\r\n\r\n1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...\r\n2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...\r\n3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...\r\n\r\n1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.\r\n\r\n제한 조건\r\n시험은 최대 10,000 문제로 구성되어있습니다.\r\n문제의 정답은 1, 2, 3, 4, 5중 하나입니다.\r\n가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.\r\n입출력 예\r\nanswers	return\r\n[1,2,3,4,5]	[1]\r\n[1,3,2,4,2]	[1,2,3]\r\n입출력 예 설명\r\n입출력 예 #1\r\n\r\n수포자 1은 모든 문제를 맞혔습니다.\r\n수포자 2는 모든 문제를 틀렸습니다.\r\n수포자 3은 모든 문제를 틀렸습니다.\r\n따라서 가장 문제를 많이 맞힌 사람은 수포자 1입니다.\r\n\r\n입출력 예 #2\r\n\r\n모든 사람이 2문제씩을 맞췄습니다.\r\n\r\n\r\n# .정답\r\n```java \r\nclass Solution {\r\n    public int[] solution(int[] answers) {\r\n        int[] answer = {};\r\n        int[] one = {1,2,3,4,5};\r\n        int[] two = {2,1,2,3,2,4,2,5};\r\n        int[] three = {3,3,1,1,2,2,4,4,5,5};\r\n        int[] sum = {0, 0, 0};\r\n        \r\n        int max = 0;\r\n        \r\n        for(int i = 0; i < answers.length ; i++){\r\n            \r\n            if(one[i%5] == answers[i]){\r\n                sum[0] += 1;\r\n            }\r\n            if(two[i%8] == answers[i]){\r\n                sum[1] += 1;\r\n            }\r\n            if(three[i%10] == answers[i]){\r\n                sum[2] += 1;\r\n            }       \r\n      }\r\n               \r\n        for(int i = 0; i < sum.length ; i++){\r\n            if(max < sum[i]){\r\n                max = sum[i];\r\n            }\r\n        }\r\n        \r\n        if(max == sum[0] && max == sum[1] && max == sum[2]){\r\n            answer = new int[3];\r\n            answer[0] = 1;\r\n            answer[1] = 2;\r\n            answer[2] = 3;\r\n        }\r\n        else if(max == sum[0] && max == sum[1]){\r\n            answer = new int[2];\r\n            answer[0] = 1;\r\n            answer[1] = 2;\r\n        }\r\n        else if(max == sum[0] && max == sum[2]){\r\n            answer = new int[2];\r\n            answer[0] = 1;\r\n            answer[1] = 3;\r\n        }\r\n        else if(max == sum[1] && max == sum[2]){\r\n            answer = new int[2];\r\n            answer[0] = 2;\r\n            answer[1] = 3;\r\n        }\r\n        else if(max == sum[0]){\r\n            answer = new int[1];\r\n            answer[0] = 1;\r\n        }\r\n        else if(max == sum[1]){\r\n            answer = new int[1];\r\n            answer[0] = 2;\r\n        }\r\n        else if(max == sum[2]){\r\n            answer = new int[1];\r\n            answer[0] = 3;\r\n        }\r\n        \r\n      \r\n        return answer;\r\n    }\r\n}\r\n\r\n```\r\n\r\n# .설명\r\n```\r\n        int[] answer = {}; // 정답\r\n        int[] one = {1,2,3,4,5};\r\n        int[] two = {2,1,2,3,2,4,2,5};\r\n        int[] three = {3,3,1,1,2,2,4,4,5,5};\r\n        int[] sum = {0, 0, 0}; // 점수\r\n```\r\n\r\none, two, three 각각의 수포자의 정답 패턴을 배열에 저장합니다.\r\n\r\n```\r\n      for(int i = 0; i < answers.length ; i++){\r\n            \r\n            if(one[i%5] == answers[i]){ //나머지를 계산하여 0~5 순서로 반복 계산\r\n                sum[0] += 1;\r\n            }\r\n            if(two[i%8] == answers[i]){\r\n                sum[1] += 1;\r\n            }\r\n            if(three[i%10] == answers[i]){\r\n                sum[2] += 1;\r\n            }       \r\n      }\r\n        for(int i = 0; i < sum.length ; i++){\r\n            if(max < sum[i]){\r\n                max = sum[i];\r\n            }\r\n        }\r\n```\r\n\r\n정답이 들어있는 answeres의 크기만큼 for문을 돕니다. \r\n\r\n도는 중 if문으로 one, two, three의 정답을 계산하여 sum에 각각 저장합니다.\r\n\r\n그 후 가장 큰 점수를 max에 넣습니다.\r\n\r\n```\r\nif(max == sum[0] && max == sum[1] && max == sum[2]){\r\n            answer = new int[3];\r\n            answer[0] = 1;\r\n            answer[1] = 2;\r\n            answer[2] = 3;\r\n        }\r\n        else if(max == sum[0] && max == sum[1]){\r\n            answer = new int[2];\r\n            answer[0] = 1;\r\n            answer[1] = 2;\r\n        }\r\n        else if(max == sum[0] && max == sum[2]){\r\n            answer = new int[2];\r\n            answer[0] = 1;\r\n            answer[1] = 3;\r\n        }\r\n        else if(max == sum[1] && max == sum[2]){\r\n            answer = new int[2];\r\n            answer[0] = 2;\r\n            answer[1] = 3;\r\n        }\r\n        else if(max == sum[0]){\r\n            answer = new int[1];\r\n            answer[0] = 1;\r\n        }\r\n        else if(max == sum[1]){\r\n            answer = new int[1];\r\n            answer[0] = 2;\r\n        }\r\n        else if(max == sum[2]){\r\n            answer = new int[1];\r\n            answer[0] = 3;\r\n        }\r\n   \r\n        return answer;\r\n    }\r\n}\r\n```\r\n\r\n공동 순위기 있을경우 오름차순으로 리턴해야하기 때문에 else if문으로 경우의 수를 모두 적어줍니다.\r\n\r\n되는데로 작성한거라 그닥 효율적이지는 않은거같습니다.\r\n\r\n',2,2,0,0,0,0),(12,'2021-01-19 17:29:43','2021-01-19 17:29:46','[java][programmers] 두 개 뽑아서 더하기','# 두 개 뽑아서 더하기\r\n문제\r\n\r\n정수 배열 numbers가 주어집니다. numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를 배열에 오름차순으로 담아 return 하도록 solution 함수를 완성해주세요.\r\n\r\n제한사항\r\nnumbers의 길이는 2 이상 100 이하입니다.\r\nnumbers의 모든 수는 0 이상 100 이하입니다.\r\n입출력 예\r\nnumbers result\r\n[2,1,3,4,1] [2,3,4,5,6,7]\r\n[5,0,2,7] [2,5,7,9,12]\r\n입출력 예 설명\r\n입출력 예 #1\r\n\r\n2 = 1 + 1 입니다. (1이 numbers에 두 개 있습니다.)\r\n3 = 2 + 1 입니다.\r\n4 = 1 + 3 입니다.\r\n5 = 1 + 4 = 2 + 3 입니다.\r\n6 = 2 + 4 입니다.\r\n7 = 3 + 4 입니다.\r\n따라서 [2,3,4,5,6,7] 을 return 해야 합니다.\r\n입출력 예 #2\r\n\r\n2 = 0 + 2 입니다.\r\n5 = 5 + 0 입니다.\r\n7 = 0 + 7 = 5 + 2 입니다.\r\n9 = 2 + 7 입니다.\r\n12 = 5 + 7 입니다.\r\n따라서 [2,5,7,9,12] 를 return 해야 합니다.\r\n\r\n\r\n# .정답\r\n```java \r\nimport java.util.List;\r\nimport java.util.ArrayList;\r\nimport java.util.Arrays;\r\n\r\n\r\nclass Solution {\r\n    public int[] solution(int[] numbers) {\r\n        List＜Integer＞ array = new ArrayList＜＞();\r\n        \r\n        int n = numbers.length;\r\n        \r\n        for(int i = 0; i ＜ n; i++){\r\n            for(int j = i+1 ; j ＜ n ; j++){\r\n                int sum = numbers[i]+numbers[j];\r\n                if(!array.contains(sum)){\r\n                    array.add(sum);\r\n                }\r\n            }\r\n        }\r\n        \r\n        int[] answer = new int[array.size()];\r\n        for(int i = 0; i ＜ array.size(); i++){\r\n            answer[i] = array.get(i);\r\n        }\r\n        \r\n        Arrays.sort(answer);\r\n        \r\n        \r\n        return answer;\r\n    }\r\n}\r\n\r\n```\r\n\r\n# .설명\r\n```\r\n       List＜Integer＞ array = new ArrayList＜＞();\r\n        \r\n       int n = numbers.length;\r\n```\r\n\r\n정답을 저장할 array를 ArrayList로 선언하고\r\n\r\n매개변수 numbers의 길이만큼 변수 n에 저장합니다..\r\n\r\n```\r\n for(int i = 0; i ＜ n; i++){\r\n            for(int j = i+1 ; j ＜ n ; j++){\r\n                int sum = numbers[i]+numbers[j];\r\n                if(!array.contains(sum)){\r\n                    array.add(sum);\r\n                }\r\n            }\r\n        }\r\n```\r\n\r\n이중포문으로 numbers의 값을 돌려가며 sum을 구하는데\r\n\r\ncontains를 사용해 현재의 sum값이 이미 들어가있는지 검사하고 array에 넣어줍니다.\r\n\r\n```\r\nint[] answer = new int[array.size()];\r\n        for(int i = 0; i ＜ array.size(); i++){\r\n            answer[i] = array.get(i);\r\n        }\r\n        \r\n        Arrays.sort(answer);\r\n        \r\n        \r\n        return answer;\r\n    }\r\n```\r\n\r\n함수 return 값이 int[]형으로 되어있기 때문에 정답을 옮겨담기 위해 만들어주고 옮겨줍니다.\r\n\r\n그리고 sort(정렬함수) 함수를 사용해 정렬해주고 리턴합니다.\r\n\r\n',2,2,0,0,0,0);
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

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `relTypeCode` char(20) NOT NULL,
  `relId` int(10) unsigned NOT NULL,
  `body` char(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `relTypeCode_2` (`relTypeCode`,`relId`,`body`),
  UNIQUE KEY `relTypeCode_4` (`relTypeCode`,`relId`,`body`),
  KEY `relTypeCode` (`relTypeCode`,`body`),
  KEY `relTypeCode_3` (`relTypeCode`,`body`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'2021-01-14 19:06:05','2021-01-14 19:06:05','article',2,'SQL'),(2,'2021-01-14 19:06:05','2021-01-14 19:06:05','article',2,'INSERT'),(3,'2021-01-14 19:06:05','2021-01-14 19:06:05','article',2,'DB');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-19 17:42:18
