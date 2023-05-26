SELECT * FROM products ORDER BY product_id DESC LIMIT 0, 10;
SELECT * FROM productview ORDER BY  category_id ASC, product_id DESC;

SELECT * FROM productview WHERE category_id = 1;


-- 글 총 개수
SELECT count(product_id) FROM products;

-- 검색
SELECT * FROM productview;

-- 검색 글총개수
SELECT count(product_id) FROM productview WHERE product_name LIKE '%4%' OR country_of_origin LIKE '%4%' OR category_name LIKE '%4%';

SELECT * FROM productview WHERE stock_quantity > '456';

SELECT count(product_id) FROM (SELECT * FROM productview WHERE stock_quantity > '456') F WHERE product_name LIKE '%4%' OR country_of_origin LIKE '%4%' OR category_name LIKE '%4%';


SELECT count(product_id) FROM productview WHERE product_name LIKE '%4%' OR country_of_origin LIKE '%4%' OR category_name LIKE '%4%' and stock_quantity > '456';

SELECT count(product_id) FROM productview
WHERE (product_name LIKE '%4%' OR country_of_origin LIKE '%4%' OR category_name LIKE '%4%');
