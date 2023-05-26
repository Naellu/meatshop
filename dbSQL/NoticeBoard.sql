## 공지사항

CREATE TABLE noticeboard(
	id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    writer VARCHAR(50) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    inserted DATETIME NOT NULL DEFAULT now()
);

INSERT INTO noticeboard (writer, title, content)
VALUES ('관리자1', '배송 지연', 'ㅈㅅㅈㅅ');

INSERT INTO noticeboard (writer, title, content)
VALUES ('관리자2', '배송 안내', 'ㄱㄷㄱㄷ');

INSERT INTO noticeboard (writer, title, content)
VALUES ('관리자3', '배송 문의', 'ㅇㅋㅇㅋ');

INSERT INTO noticeboard (writer, title, content)
VALUES ('관리자1', '배송 언제되는지 그만 물어봐', '배송 언제되는지 그만 물어봐 제발');
SELECT * FROM noticeboard;

UPDATE noticeboard
			SET
				title = '배송 언제되는지 그만 물어봐22ㅊㅊ',
				content = '배송 언제되는지 그만 물어봐 제발22ㅊㅊ'
			WHERE
				id = 4;
                
CREATE TABLE FileName (
	Id INT PRIMARY KEY AUTO_INCREMENT,
    boardId INT NOT NULL,
    fileName VARCHAR(300) NOT NULL,
    FOREIGN KEY (boardId) REFERENCES Board(id)
);



CREATE TABLE noticeboardfile(
	id INT PRIMARY KEY AUTO_INCREMENT,
    notice_board_id INT NOT NULL,
    file_name VARCHAR(300) NOT NULL,
    FOREIGN KEY (notice_board_id) REFERENCES noticeboard(id)
);

SELECT * FROM noticeboardfile;
SELECT * FROM noticeboard;

DELETE FROM noticeboardfile
			WHERE id = 1;

INSERT INTO noticeboardfile (notice_board_id, file_name)
VALUES (3, 'liverpool.png');

SELECT 
				nb.title,
				nb.content,
				nb.inserted,
				nbf.file_name
			FROM noticeboard nb LEFT JOIN noticeboardfile nbf 
			ON nb.id = nbf.notice_board_id
			WHERE
				nb.id = 23;