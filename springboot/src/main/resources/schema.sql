drop table IF EXISTS customers CASCADE;
drop table IF EXISTS accounts CASCADE;
drop table IF EXISTS customers_accounts CASCADE;
CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ;

CREATE TABLE `accounts` (
  `account_number` int NOT NULL AUTO_INCREMENT,
  `account_type` varchar(255) DEFAULT NULL,
  `balance` int NOT NULL,
  PRIMARY KEY (`account_number`)
) ;

CREATE TABLE `customers_accounts` (
  `account_number` int  NOT NULL,
  `customer_id`   int NOT NULL,
   PRIMARY KEY (customer_id,account_number),
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    FOREIGN KEY (account_number) REFERENCES accounts (account_number)
 
);

