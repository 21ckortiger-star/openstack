INSERT INTO member (username, password, name)
SELECT 'admin', '1234', '관리자'
WHERE NOT EXISTS (SELECT 1 FROM member WHERE username = 'admin');

INSERT INTO course (title, instructor_name, description)
SELECT '스프링부트 기초', '홍길동', 'Spring Boot와 MVC 기본기를 배우는 강의입니다.'
WHERE NOT EXISTS (SELECT 1 FROM course WHERE title = '스프링부트 기초');
