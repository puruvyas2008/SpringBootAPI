INSERT INTO `customers`
(`customer_id`,
`last_name`,
`email`,
`first_name`)
VALUES
(1,
   'kumar',
'aj@gmail.com',
'amit');

INSERT INTO `accounts`
(`account_number`,
`account_type`,
`balance`)
VALUES
(1,
'saving',
200);

INSERT INTO  `customers_accounts`
(`account_number`,
`customer_id`)
VALUES
(1,
1);

INSERT INTO `accounts`
(`account_number`,
`account_type`,
`balance`)
VALUES
(2,
'current',
100);
INSERT INTO  `customers_accounts`
(`account_number`,
`customer_id`)
VALUES
(2,
1);



