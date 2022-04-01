DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS customer_transaction;
CREATE TABLE customer_transaction (
	id INT AUTO_INCREMENT PRIMARY KEY,
	item_desc VARCHAR(250),
	total DOUBLE,
	purchase_date TIMESTAMP,
	customer_id INT NOT NULL,
	foreign key (customer_id) references customer(id)
);