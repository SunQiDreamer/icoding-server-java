CREATE TABLE `Course` (
  `id` smallint NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Course_id_uindex` (`id`),
  UNIQUE KEY `Course_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci

CREATE TABLE `Grade` (
  `id` smallint NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8mb4_0900_as_ci NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mainTeacherId` smallint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Grade_id_uindex` (`id`),
  UNIQUE KEY `Grade_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci

CREATE TABLE `Hobby` (
  `id` smallint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Hobby_name_uindex` (`name`),
  UNIQUE KEY `Hobby_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci

CREATE TABLE `Student` (
  `id` smallint NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8mb4_0900_as_ci NOT NULL DEFAULT '',
  `no` smallint NOT NULL DEFAULT '0',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `grade_id` smallint DEFAULT NULL,
  `sex` tinyint DEFAULT '0',
  `hobby_ids` varchar(30) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Student_id_uindex` (`id`),
  UNIQUE KEY `Student_no_uindex` (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci

CREATE TABLE `Teacher` (
  `id` smallint NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `sex` tinyint NOT NULL,
  `grade_ids` varchar(100) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `course_id` tinyint NOT NULL,
  `is_main` tinyint(1) DEFAULT NULL,
  `hobby_ids` varchar(100) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Teacher_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci

CREATE TABLE `_fluent_migrations` (
  `id` varbinary(16) NOT NULL,
  `name` varchar(255) NOT NULL,
  `batch` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `eb3ee69e0c062ede0b815d412472c764ccb82e41` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `sys_resource` (
  `id` smallint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `uri` varchar(50) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `permission` varchar(20) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `type` smallint DEFAULT NULL,
  `icon` varchar(50) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `sn` smallint DEFAULT NULL,
  `parent_id` smallint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci

CREATE TABLE `sys_user_role` (
  `role_id` smallint NOT NULL,
  `user_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci

CREATE TABLE `table_name` (
  `id` smallint NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_0900_as_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci

