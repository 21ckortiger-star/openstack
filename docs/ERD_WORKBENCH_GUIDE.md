# MySQL Workbench로 ERD 만드는 방법 (홈페이지 프로젝트)

## 1) SQL 실행
1. Workbench 접속
2. 새 Query 탭 열기
3. `docs/erd-homepage.sql` 내용 전체 복붙
4. 실행(번개 아이콘)

## 2) ERD 다이어그램 생성
1. 상단 메뉴: **Database → Reverse Engineer...**
2. 연결 선택 후 Next
3. 스키마에서 `goonggeum_homepage` 체크
4. Import Objects에서 전체 테이블 선택
5. Finish
6. Model 화면에서 **Add Diagram** 선택
7. 오른쪽 상단 **Arrange → Autolayout** 눌러 자동 정렬

## 3) 이미지/PDF로 내보내기
- 메뉴: **File → Export → as PNG/SVG/PDF**
- 발표용은 A4 가로 권장

## 4) 발표자료용 테이블 추천
- users, posts, categories, tags, post_tags, comments, projects, project_images, contact_messages
(복잡하면 일부 테이블만 표시해도 됨)
