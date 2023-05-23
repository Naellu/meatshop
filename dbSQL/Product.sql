CREATE TABLE products (
	product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
	country_of_origin VARCHAR(255) NOT NULL,
	category_id INT NOT NULL,
	price DECIMAL(10,2) NOT NULL
);

SELECT * FROM products ORDER BY product_id DESC;

DESC products;

CREATE TABLE categories (
	category_id INT PRIMARY KEY,
	category_name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

INSERT INTO categories (category_id, category_name) VALUES (1, '소');
INSERT INTO categories (category_id, category_name) VALUES (2, '돼지');
INSERT INTO categories (category_id, category_name) VALUES (3, '닭');

SELECT * FROM categories;

CREATE TABLE productfilename (
	id INT PRIMARY KEY AUTO_INCREMENT,
	product_id VARCHAR(255) NOT NULL,
	file_name VARCHAR(255) NOT NULL
);


SELECT * FROM products;


DESC productview;


CREATE View productview
AS
SELECT p.product_id, p.product_name, p.country_of_origin, p.price, p.stock_quantity, c.*, f.file_name
FROM products p JOIN categories c ON p.category_id = c.category_id
LEFT JOIN productfilename f ON p.product_id = f.product_id ORDER BY product_id;

SELECT * FROM productview ORDER BY product_Id;

SELECT p.product_id, p.product_name, p.country_of_origin, p.price, p.stock_quantity, c.*, f.file_name
FROM products p JOIN categories c ON p.category_id = c.category_id
LEFT JOIN productfilename f ON p.product_id = f.product_id ORDER BY product_id;

INSERT INTO productfilename (product_id, file_name) VALUES (1, '1.png');
INSERT INTO productfilename (product_id, file_name) VALUES (1, '2.png');
INSERT INTO productfilename (product_id, file_name) VALUES (1, '3.png');
INSERT INTO productfilename (product_id, file_name) VALUES (2, '1.png');
INSERT INTO productfilename (product_id, file_name) VALUES (4, '1.png');
INSERT INTO productfilename (product_id, file_name) VALUES (5, '1.png');

TRUNCATE TABLE products;