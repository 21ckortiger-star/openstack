INSERT INTO course (title, instructor_name, description)
SELECT '스프링부트 기초', '홍길동', 'Spring Boot와 MVC 기본기를 배우는 강의입니다.'
WHERE NOT EXISTS (SELECT 1 FROM course WHERE title = '스프링부트 기초');
