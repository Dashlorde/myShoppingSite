
CREATE TABLE `addressTable` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `comment_table` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` longblob,
  `productID` bigint(20) DEFAULT NULL,
  `id` bigint(20) DEFAULT NULL,
  `comment_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_73c96a9a2b1e497581b0a91f69e` (`productID`),
  KEY `FK_a28409f5eb014ad880da9a54797` (`id`)
 
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

CREATE TABLE `emailtable` (
  `id` bigint(20) NOT NULL,
  `emailId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Order_item` (
  `Order_order_id` bigint(20) NOT NULL,
  `item` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  KEY `orderToProduct` (`Order_order_id`),
  KEY `productId_idx` (`item`)
  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `orderTable` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `buyer_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `seller_id` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` float DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=latin1;

CREATE TABLE `productTable` (
  `productID` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `description` longblob,
  `productName` varchar(255) DEFAULT NULL,
  `productPrice` float DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `imageName` varchar(255) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;

CREATE TABLE `usertable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `paypal_password` varchar(255) DEFAULT NULL,
  `paypal_username` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e9f6f748525744c8b1d5353f6a3` (`address_id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

CREATE TABLE `wishlist` (
  `user_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `myShoppingSite`.`Order_item` 
ADD CONSTRAINT `orderToProduct`
  FOREIGN KEY (`Order_order_id`)
  REFERENCES `myShoppingSite`.`orderTable` (`order_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


ALTER TABLE `myShoppingSite`.`usertable` 
ADD CONSTRAINT `FK_e9f6f748525744c8b1d5353f6a3`
  FOREIGN KEY (`address_id`)
  REFERENCES `myShoppingSite`.`addressTable` (`id`)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;

  

ALTER TABLE `myShoppingSite`.`comment_table` 
ADD CONSTRAINT `FK_73c96a9a2b1e497581b0a91f69e`
  FOREIGN KEY (`productID`)
  REFERENCES `myShoppingSite`.`productTable` (`productID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


ALTER TABLE `myShoppingSite`.`comment_table` 
ADD CONSTRAINT `FK_a28409f5eb014ad880da9a54797`
  FOREIGN KEY (`id`)
  REFERENCES `myShoppingSite`.`usertable` (`id`)
  ON DELETE CASCADE
  ON UPDATE RESTRICT;



USE `myShoppingSite`;

DELIMITER $$

DROP TRIGGER IF EXISTS myShoppingSite.productTable_AFTER_DELETE$$
USE `myShoppingSite`$$
CREATE DEFINER = CURRENT_USER TRIGGER `myShoppingSite`.`productTable_AFTER_DELETE` AFTER DELETE ON `productTable` FOR EACH ROW
BEGIN
SET SQL_SAFE_UPDATES = 0;
delete from Order_item where item not in (select productID from productTable);
delete from wishlist where product_id not in (select productID from productTable);
END$$
DELIMITER ;



USE `myShoppingSite`;

DELIMITER $$

DROP TRIGGER IF EXISTS myShoppingSite.usertable_AFTER_DELETE$$
USE `myShoppingSite`$$
CREATE DEFINER = CURRENT_USER TRIGGER `myShoppingSite`.`usertable_AFTER_DELETE` AFTER DELETE ON `usertable` FOR EACH ROW
BEGIN
SET SQL_SAFE_UPDATES = 0;
delete from emailtable where id not in (select id from usertable);
END$$
DELIMITER ;

