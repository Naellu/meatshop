CREATE TABLE whishlist (
	id INT PRIMARY KEY AUTO_INCREMENT,
    member_id VARCHAR(30),
    product_id INT
);

CREATE View wishlistview
AS
SELECT p.product_id, p.product_name, p.country_of_origwishlistin, p.price, p.stock_quantity, p.pub, c.*, f.file_name, wl.member_id
FROM products p JOIN categories c ON p.category_id = c.category_id
LEFT JOIN productfilename f ON p.product_id = f.product_id
LEFT JOIN wishlist wl ON wl.product_id = p.product_id;

SELECT * FROM wishlistview WHERE member_id = 'admin0';

SELECT * FROM wishlist;

INSERT INTO wishlist VALUES ('admin0', 98);



