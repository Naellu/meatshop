DESC orders;
DESC orderitems;

SELECT * FROM orders;
SELECT * FROM orderitems;

SELECT p.product_id, p.product_name, p.country_of_origin, p.price, p.stock_quantity, c.*, f.file_name, o.sold
FROM products p JOIN categories c ON p.category_id = c.category_id
LEFT JOIN productfilename f ON p.product_id = f.product_id 
LEFT JOIN (SELECT product_id, COUNT(id) sold FROM orderitems GROUP BY product_id ORDER BY sold DESC) o ON  p.product_id = o.product_id
ORDER BY product_id;

SELECT p.*, COUNT(product_id) sold FROM products p JOIN orderitems o ON p.product_id = o.product_id GROUP BY product_id;
SELECT product_id, COUNT(id) sold FROM orderitems GROUP BY product_id ORDER BY sold DESC;

CREATE View mainitemview
AS
SELECT p.*, COUNT(o.product_id) AS sold, c.category_name
FROM products p
JOIN categories c ON p.category_id = c.category_id
LEFT JOIN orderitems o ON p.product_id = o.product_id
WHERE pub = 1
GROUP BY p.product_id
ORDER BY sold DESC, price DESC
LIMIT 3;

SELECT * FROM mainitemview;


SELECT p.*, COUNT(o.product_id) sold, c.category_name
FROM products p
JOIN categories c ON p.category_id = c.category_id
LEFT JOIN orderitems o ON p.product_id = o.product_id
GROUP BY p.product_id ORDER BY sold DESC LIMIT 3;

SELECT p.*, COUNT(o.product_id) AS sold, c.category_name
FROM products p
JOIN categories c ON p.category_id = c.category_id
LEFT JOIN orderitems o ON p.product_id = o.product_id
WHERE pub = 1
GROUP BY p.product_id
ORDER BY sold DESC, price DESC LIMIT 3;