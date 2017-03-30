ALTER TABLE `myShoppingSite`.`Order_item` 
DROP FOREIGN KEY `orderToProduct`;
ALTER TABLE `myShoppingSite`.`Order_item` 
ADD CONSTRAINT `orderToProduct`
  FOREIGN KEY (`Order_order_id`)
  REFERENCES `myShoppingSite`.`orderTable` (`order_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `myShoppingSite`.`usertable` 
DROP FOREIGN KEY `FK_e9f6f748525744c8b1d5353f6a3`;
ALTER TABLE `myShoppingSite`.`usertable` 
ADD CONSTRAINT `FK_e9f6f748525744c8b1d5353f6a3`
  FOREIGN KEY (`address_id`)
  REFERENCES `myShoppingSite`.`addressTable` (`id`)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;

  
ALTER TABLE `myShoppingSite`.`comment_table` 
DROP FOREIGN KEY `FK_73c96a9a2b1e497581b0a91f69e`;
ALTER TABLE `myShoppingSite`.`comment_table` 
ADD CONSTRAINT `FK_73c96a9a2b1e497581b0a91f69e`
  FOREIGN KEY (`productID`)
  REFERENCES `myShoppingSite`.`productTable` (`productID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `myShoppingSite`.`comment_table` 
DROP FOREIGN KEY `FK_a28409f5eb014ad880da9a54797`;
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

