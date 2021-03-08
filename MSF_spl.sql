DROP DATABASE  IF EXISTS `MSF`;

CREATE DATABASE  IF NOT EXISTS `MSF`;
USE `MSF`;

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level` varchar(10) DEFAULT NULL ,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
INSERT INTO `users` (level,username,email,password)
VALUES 
('Bronze','john','john@luv2code.com','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K'),
('Silver','mary','mary@luv2code.com','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K'),
('Gold','susan','susan@luv2code.com','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K');

Drop table if exists `role`;

Create Table `role`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `role` (name)
VALUES 
('ROLE_USER'),('ROLE_ADMIN');

Drop table if exists `users_roles`;

Create table `users_roles` (
     `user_id` bigint(20) NOT NULL,
     `role_id` int(11) NOT NULL,
	
	CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
	REFERENCES `users` (`id`),

	CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
	REFERENCES `role` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2);


Drop table if exists `files`;

Create table `files` (
     `id` bigint(20) NOT NULL auto_increment,
     `user_id` bigint(20),
     `name` varchar(50) NOT NULL,
     `type` varchar(20) NOT NULL,
     `data` longblob,
	primary key(`id`),
     CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
	REFERENCES `users` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
