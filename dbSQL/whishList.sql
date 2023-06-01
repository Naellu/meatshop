CREATE TABLE wishlist (
	id INT PRIMARY KEY AUTO_INCREMENT,
    member_id VARCHAR(30),
    product_id INT,
    added_date DATE DEFAULT NOW()
);

CREATE View wishlistview
AS
SELECT wl.id, wl.added_date, p.product_id, p.product_name, p.country_of_origin, p.price, p.stock_quantity, p.pub, c.*, f.file_name, wl.member_id
FROM products p JOIN categories c ON p.category_id = c.category_id
LEFT JOIN productfilename f ON p.product_id = f.product_id
LEFT JOIN wishlist wl ON wl.product_id = p.product_id;

SELECT * FROM wishlistview WHERE member_id = 'admin0' ORDER BY added_date DESC, id DESC;

SELECT *
FROM wishlistview
WHERE member_id = 'admin0' AND added_date >= DATE_SUB(NOW(), INTERVAL 6 MONTH)
ORDER BY added_date DESC, id DESC;

SELECT * FROM wishlist;

INSERT INTO wishlist (member_id, product_id) VALUES ('admin0', 98);
INSERT INTO wishlist (member_id, product_id) VALUES ('admin0', 97);
INSERT INTO wishlist (member_id, product_id) VALUES ('admin0', 96);

SELECT wl.id, p.product_id, p.product_name, p.country_of_origin, p.price, p.stock_quantity, p.pub, c.*, f.file_name, wl.member_id
FROM products p JOIN categories c ON p.category_id = c.category_id
LEFT JOIN productfilename f ON p.product_id = f.product_id
LEFT JOIN wishlist wl ON wl.product_id = p.product_id;
