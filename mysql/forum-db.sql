
-- Create a database
CREATE DATABASE IF NOT EXISTS forum_db;

CREATE USER IF NOT EXISTS 'forumdb_user'@'%' IDENTIFIED BY 'forumdbpassword';

-- Grant privileges to the user on your database
GRANT ALL PRIVILEGES ON `forum_db`.* TO 'forumdb_user'@'%';

-- Apply changes
FLUSH PRIVILEGES;

-- Use the database
USE forum_db;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL,
  `email` varchar(254) NOT NULL,
  `picture` varchar(2000) DEFAULT NULL,
  `about` mediumtext,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `follow_users`
--

DROP TABLE IF EXISTS `follow_users`;

CREATE TABLE `follow_users` (
  `user_id` int NOT NULL,
  `follower_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`follower_id`),
  KEY `FK_FOLLOWER_idx` (`follower_id`),
  CONSTRAINT `FK_FOLLOWER` FOREIGN KEY (`follower_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;

CREATE TABLE `notifications` (
  `notification_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `actor_id` int DEFAULT NULL,
  `content` tinytext NOT NULL,
  `type` enum('FOLLOW','RESPONSE') DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `FK_USER_NOT_idx` (`user_id`),
  KEY `FK_ACTOR_NOTI_idx` (`actor_id`),
  CONSTRAINT `FK_ACTOR_NOTI` FOREIGN KEY (`actor_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FK_USER_NOTI` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(60) NOT NULL,
  `content` mediumtext NOT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` int NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `POST_USER_idx` (`created_by`),
  CONSTRAINT `POST_USER` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `post_comments`
--

DROP TABLE IF EXISTS `post_comments`;

CREATE TABLE `post_comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `root_post` int NOT NULL,
  `comment_post` int DEFAULT NULL,
  `content` mediumtext NOT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` int NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_ROOT_POST_idx` (`root_post`),
  KEY `FK_CREATED_BY_idx` (`created_by`),
  KEY `FK_COMMENT_POST_idx` (`comment_post`),
  CONSTRAINT `FK_COMMENT_POST` FOREIGN KEY (`comment_post`) REFERENCES `post_comments` (`comment_id`),
  CONSTRAINT `FK_CREATED_BY` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FK_ROOT_POST` FOREIGN KEY (`root_post`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `post_reactions`
--

DROP TABLE IF EXISTS `post_reactions`;

CREATE TABLE `post_reactions` (
  `post_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type` enum('LIKE','DISLIKE') NOT NULL,
  PRIMARY KEY (`post_id`,`user_id`),
  KEY `FK_USER_idx` (`user_id`),
  CONSTRAINT `FK_POST_REACTION` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`),
  CONSTRAINT `FK_USER_REACTION` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `post_tag`
--

DROP TABLE IF EXISTS `post_tag`;

CREATE TABLE `post_tag` (
  `post_id` int NOT NULL,
  `tag` enum('VIDEOGAMES','MOVIE','SCIENCE','LITERATURE','TV_SERIES','TECHNOLOGIES') NOT NULL,
  PRIMARY KEY (`post_id`,`tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


