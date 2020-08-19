--you can add your create table script manually here just like example below
DROP TABLE IF EXISTS EXCHANGE_VALUE;

CREATE TABLE EXCHANGE_VALUE
(
    id                     INT AUTO_INCREMENT PRIMARY KEY,
    transaction_id         NUMBER(50),
    target_currency_amount NUMBER(50),
    transaction_date       DATE(50)
);