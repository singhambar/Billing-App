CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL,
  `FIRST_NAME` varchar(48) COLLATE utf8mb4_general_ci NOT NULL,
  `EMAIL_ID` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `PASSWORD` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `LAST_NAME` varchar(48) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `MOBILE` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_DATE` timestamp NOT NULL,
  `ROLE` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`FIRST_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `auth_token` (
  `ID` bigint(20) NOT NULL,
  `TOKEN` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `VALIDATOR` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `USER_ID_idx` (`USER_ID`),
  CONSTRAINT `USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `product` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `VENDOR` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `COST_PRICE` bigint(20) NOT NULL,
  `SELLING_PRICE` bigint(20) NOT NULL,
  `GST` int(11) NOT NULL,
  `QUANTITY` bigint(20) NOT NULL DEFAULT '0',
  `MODIFIED_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci