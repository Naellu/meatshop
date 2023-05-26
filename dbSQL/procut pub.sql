-- pub추가
ALTER TABLE products
ADD COLUMN pub INT NOT NULL DEFAULT 1;

-- view 다시생성
CREATE View productview
AS
SELECT p.product_id, p.product_name, p.country_of_origin, p.price, p.stock_quantity, p.pub, c.*, f.file_name
FROM products p JOIN categories c ON p.category_id = c.category_id
LEFT JOIN productfilename f ON p.product_id = f.product_id ORDER BY product_id;

SELECT * FROM products ORDER BY product_id DESC ;

UPDATE products SET pub = 1 WHERE product_id IN ('100', '99');

UPDATE products SET pub = 1 ;


UPDATE productview
SET pub = CASE 
WHEN product_id IN (100,99,98) THEN 1
ELSE 0 END
WHERE product_id IN (100,99,98, 97, 96, 95);

