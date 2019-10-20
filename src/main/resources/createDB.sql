CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL,
  `FIRST_NAME` varchar(48) COLLATE utf8mb4_general_ci NOT NULL,
  `EMAIL_ID` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `PASSWORD` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `LAST_NAME` varchar(48) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CONTACT_NO` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_DATE` timestamp NOT NULL,
  `ROLE` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `EMAIL_ID_UNIQUE` (`EMAIL_ID`)
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
  `NAME` varchar(128) COLLATE utf8mb4_general_ci NOT NULL,
  `VENDOR` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `COST_PRICE` decimal(10,0) NOT NULL,
  `SELLING_PRICE` decimal(10,0) NOT NULL,
  `GST` decimal(10,0) NOT NULL,
  `QUANTITY` bigint(20) NOT NULL,
  `EXTRA_DETAILS` varchar(2096) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `MODIFIED_DATE` timestamp NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `bill` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(128) COLLATE utf8mb4_general_ci NOT NULL,
  `ADDRESS` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CONTACT_NO` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `INVOICE_NO` bigint(20) NOT NULL,
  `GSTIN` bigint(20) NOT NULL,
  `BILL_DATE` timestamp NOT NULL,
  `TOTAL_AMOUNT` decimal(10,0) NOT NULL,
  `DISCOUNT_AMOUNT` decimal(10,0) NOT NULL,
  `PAYMENT_TYPE` int(2) NOT NULL DEFAULT '1',
  `EXTRA_DETAILS` varchar(2096) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `billed_product` (
  `ID` bigint(20) NOT NULL,
  `BILL_ID` bigint(20) NOT NULL,
  `PRODUCT_ID` bigint(20) NOT NULL,
  `DISCOUNT` float NOT NULL DEFAULT '0',
  `UNITS` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `FK_BILL_ID_idx` (`BILL_ID`),
  KEY `FK_PRODUCT_ID_idx` (`PRODUCT_ID`),
  CONSTRAINT `FK_BILL_ID` FOREIGN KEY (`BILL_ID`) REFERENCES `bill` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PRODUCT_ID` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;