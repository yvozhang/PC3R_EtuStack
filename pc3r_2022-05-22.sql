# ************************************************************
# Sequel Ace SQL dump
# 版本号： 20033
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# 主机: localhost (MySQL 8.0.29)
# 数据库: pc3r
# 生成时间: 2022-05-23 11:26:02 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# 转储表 commentaire
# ------------------------------------------------------------

CREATE DATABASE pc3r;

Use pc3r;

DROP TABLE IF EXISTS `commentaire`;


CREATE TABLE `commentaire` (
  `idC` int unsigned NOT NULL AUTO_INCREMENT,
  `contenu` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `idU` int unsigned DEFAULT NULL,
  `idP` int unsigned DEFAULT NULL,
  PRIMARY KEY (`idC`),
  KEY `com_user` (`idU`),
  KEY `com_post` (`idP`),
  CONSTRAINT `com_post` FOREIGN KEY (`idP`) REFERENCES `posts` (`idP`),
  CONSTRAINT `com_user` FOREIGN KEY (`idU`) REFERENCES `users` (`idU`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `commentaire` WRITE;
/*!40000 ALTER TABLE `commentaire` DISABLE KEYS */;

INSERT INTO `commentaire` (`idC`, `contenu`, `date`, `idU`, `idP`)
VALUES
	(1,'I\'m the first person who reply you!!!!!!','2022-03-11',4,5),
	(2,'i agree with u!!!!!!','2022-05-20',1,9),
	(3,'what else','2022-05-20',1,9),
	(4,'hqhqhqh','2022-05-21',1,2),
	(5,'the first comments of this post','2022-05-22',1,16),
	(6,'un commentaire from postman','2022-05-22',13,2);

/*!40000 ALTER TABLE `commentaire` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 groupes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `groupes`;

CREATE TABLE `groupes` (
  `idG` int unsigned NOT NULL AUTO_INCREMENT,
  `nomG` varchar(128) DEFAULT NULL,
  `category` varchar(128) DEFAULT NULL,
  `id_owner` int unsigned DEFAULT NULL,
  PRIMARY KEY (`idG`),
  KEY `user_groupes` (`id_owner`),
  CONSTRAINT `user_groupes` FOREIGN KEY (`id_owner`) REFERENCES `users` (`idU`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `groupes` WRITE;
/*!40000 ALTER TABLE `groupes` DISABLE KEYS */;

INSERT INTO `groupes` (`idG`, `nomG`, `category`, `id_owner`)
VALUES
	(1,'weloveChimie','Chimie',1),
	(2,'weloveCS','Informatique',1),
	(3,'weloveHistory','Histoire',2),
	(4,'welovePhilosophie','Philosophie',1),
	(5,'CenterInfirmiers','Infirmiers',4),
	(6,'CenterErgothérapie','Etudes Ergothérapie',5),
	(7,'A group for ..','Histoire',1),
	(10,'check for philo','Philosophie',1),
	(11,'TestPostmanGroup','Chimie',1),
	(12,'postman create a group for philo','Histoire',13);

/*!40000 ALTER TABLE `groupes` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 posts
# ------------------------------------------------------------

DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts` (
  `idP` int unsigned NOT NULL AUTO_INCREMENT,
  `titre` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `contenu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `idU` int unsigned DEFAULT NULL,
  `idG` int unsigned DEFAULT NULL,
  `category` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`idP`),
  KEY `post_user` (`idU`),
  KEY `post_groupes` (`idG`),
  CONSTRAINT `post_groupes` FOREIGN KEY (`idG`) REFERENCES `groupes` (`idG`),
  CONSTRAINT `post_user` FOREIGN KEY (`idU`) REFERENCES `users` (`idU`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;

INSERT INTO `posts` (`idP`, `titre`, `contenu`, `date`, `idU`, `idG`, `category`)
VALUES
	(2,'TestInfor','the first post of group we love CS','2022-03-22',1,2,'Informatique'),
	(5,'TestHistoire','the first post of group we love Histoire','2022-01-22',2,3,'Histoire'),
	(6,'TestInfirmiers','the first post of group center infirmiers','2022-02-21',4,5,'Infirmiers'),
	(8,'TestErgothérapie','the first post of group center center Etudes d\'ergothérapie','2022-03-11',5,6,'Etudes Ergothérapie'),
	(9,'TestChimie2','thie second post of category Chimie','2022-03-12',1,1,'Chimie'),
	(11,'jdfksdjfldsjfkls','jfkljsdklfjdsflk','2022-05-12',5,2,'Informatique'),
	(13,'haha','hahah','2022-05-12',5,3,'Histoire'),
	(14,'the third post of weloveHistory','zjkjdkjfkozkdjfozkd','2022-05-12',5,3,'Histoire'),
	(15,'Test 20/05','TEST pour postuler le post','2022-05-20',1,3,'Histoire'),
	(16,'hahhahha','hfehfkzejfkzejfzk','2022-05-22',1,1,'Chimie'),
	(17,'The first post!','haha','2022-05-22',1,7,'Histoire'),
	(18,'hiahia','hiahia','2022-05-22',1,4,'Philosophie'),
	(20,'PostmanCreatePost','the first post created by postman','2022-05-22',1,1,'Chimie'),
	(21,'first post of this group!','wow','2022-05-22',13,12,'Histoire');

/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `idU` int unsigned NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`idU`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`idU`, `pseudo`, `email`, `password`)
VALUES
	(1,'111','123@gmail.com','111'),
	(2,'1234','zimeng@gmail.com','1234'),
	(4,'1111','111@gmail.com','1111'),
	(5,'yvo','yvo@gmail.com','123'),
	(6,'yajie','yajie@gmail.com','1234'),
	(10,'Abc','1111111@gmail.com','111'),
	(11,'test1','test1@gmail.com','111'),
	(13,'postman','postmantest@gmail.com','1234');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
