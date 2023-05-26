## 고객센터

CREATE TABLE faq(
	id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    content VARCHAR(1000) NOT NULL
);

INSERT INTO faq (title, category, content)
VALUES ('[공통] 이력번호는 어떻게 확인하나요', '상품관련', '각 상품의 이력번호는 개별 상품 라벨에 표시되어 있으며 축산물이력제 페이지에 (http://aunit.mtrace.go.kr/) 이력번호를 입력하시면 해당 상품의 이력을 쉽게 조회하실 수 있습니다.');

INSERT INTO faq (title, category, content)
VALUES ('[공통] 그램(g) 수나 두께를 옵션 외의 것으로 변경해 주세요', '상품관련', '현재의 생산 시스템 하에서는 홈페이지/앱에 안내된 기준 무게 및 옵션(두께)의 형태 이외의 개별 맞춤 생산이 어려운 점 이용에 양해 부탁드립니다.
향후 보다 다양한 무게 및 옵션(두께) 형태를 제공해드릴 수 있도록 노력하겠습니다');


CREATE TABLE question(
	id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    content VARCHAR(1000) NOT NULL,
    inserted DATETIME NOT NULL DEFAULT now(),
    answered BOOLEAN
);

CREATE TABLE qfile(
	id INT PRIMARY KEY AUTO_INCREMENT,
    q_id INT NOT NULL,
    file_name VARCHAR (300) NOT NULL,
    FOREIGN KEY (q_id) REFERENCES question(id)
);

CREATE TABLE answer(
	id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(1000),
    admin_id VARCHAR(100),
    inserted DATETIME NOT NULL DEFAULT now()
);

SELECT * FROM faq;

DESC faq;

INSERT INTO faq (title, category, content)
VALUES ('서비스이용1', '서비스이용', '서비스이용1');

INSERT INTO faq (title, category, content)
VALUES ('서비스이용2', '서비스이용', '서비스이용2');

INSERT INTO faq (title, category, content)
VALUES ('서비스이용3', '서비스이용', '서비스이용3');

INSERT INTO faq (title, category, content)
VALUES ('서비스이용4', '서비스이용', '서비스이용4');

INSERT INTO faq (title, category, content)
VALUES ('서비스이용5', '서비스이용', '서비스이용5');