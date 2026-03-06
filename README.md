# LMS 프로젝트 수행계획서 (Spring Boot)

## 1. 프로젝트 개요
- **프로젝트명:** LMS (Learning Management System)
- **기술 스택:** Spring Boot, Spring Data JPA, Spring Security, PostgreSQL(또는 MySQL), Redis(선택), Docker
- **목표:** 학습자/강사/관리자 역할 기반으로 강의, 수강, 과제, 평가, 공지, 진도 추적 기능을 제공하는 웹 기반 LMS 구축
- **프로젝트 경로:** `/home/ubuntu/projects/lms` (요청 경로 `/projects/lms`는 권한 제한으로 대체)

---

## 2. 범위 정의

### 2.1 핵심 기능 (MVP)
1. 회원가입/로그인 (역할: STUDENT, INSTRUCTOR, ADMIN)
2. 강의(코스) 생성/수정/조회/삭제
3. 수강 신청 및 수강 취소
4. 강의 콘텐츠(챕터/자료) 등록 및 조회
5. 과제 등록/제출/채점
6. 공지사항 작성/조회
7. 학습 진도(수강률) 조회

### 2.2 확장 기능 (Phase 2)
- 퀴즈/시험(객관식/주관식)
- 실시간 알림 (메일/웹소켓)
- 결제 연동(유료 강의)
- 통계 대시보드(학습시간, 완료율)
- 파일 스토리지 분리(S3 등)

---

## 3. 아키텍처/설계

### 3.1 시스템 아키텍처
- **Backend:** Spring Boot (REST API)
- **DB:** RDBMS (PostgreSQL 권장)
- **Auth:** Spring Security + JWT
- **배포:** Docker Compose (app + db + redis)

### 3.2 패키지 구조(예시)
```text
com.lms
 ├─ common
 ├─ auth
 ├─ user
 ├─ course
 ├─ enrollment
 ├─ content
 ├─ assignment
 ├─ notice
 ├─ progress
 └─ admin
```

### 3.3 도메인 초안
- **User**(id, email, password, name, role, status)
- **Course**(id, instructorId, title, description, category, status)
- **Enrollment**(id, courseId, studentId, enrolledAt, status)
- **Content**(id, courseId, title, type, url/body, orderNo)
- **Assignment**(id, courseId, title, description, dueAt)
- **Submission**(id, assignmentId, studentId, content, submittedAt, score)
- **Notice**(id, courseId, title, body, createdAt)
- **Progress**(id, courseId, studentId, completionRate, lastAccessedAt)

---

## 4. 개발 일정 (6주 기준)

### 1주차: 기획/초기세팅
- 요구사항 상세화
- ERD/도메인 모델 설계
- Spring Boot 프로젝트 생성
- 공통 응답 포맷/예외 처리/로깅 세팅

### 2주차: 인증/권한
- 회원가입/로그인 API
- JWT 발급/검증
- 역할 기반 인가 정책 적용

### 3주차: 강의/수강
- 강의 CRUD
- 수강 신청/취소
- 강의 목록/검색/상세 조회

### 4주차: 콘텐츠/공지
- 강의 콘텐츠 등록/조회
- 공지사항 CRUD
- 파일 업로드(로컬 또는 S3 Mock)

### 5주차: 과제/진도
- 과제 등록/제출/채점
- 진도율 계산 로직
- 기본 통계 API

### 6주차: 안정화/배포
- 통합 테스트/버그 수정
- 성능 점검(쿼리 튜닝, 인덱스)
- Docker 배포 및 운영 문서화

---

## 5. 개발 규칙
- 브랜치 전략: `main`, `develop`, `feature/*`, `hotfix/*`
- 커밋 컨벤션: Conventional Commits (`feat:`, `fix:`, `refactor:` 등)
- 코드 스타일: Checkstyle/Spotless 적용
- API 문서: Swagger(OpenAPI)
- 테스트 기준: 서비스 계층 단위 테스트 + 핵심 API 통합 테스트

---

## 6. 품질/리스크 관리

### 6.1 품질 목표
- 주요 API 응답시간 평균 300ms 이하(개발환경 기준)
- 핵심 비즈니스 로직 테스트 커버리지 70% 이상
- 장애 발생 시 원인 추적 가능한 로깅 체계 확보

### 6.2 주요 리스크 및 대응
1. **요구사항 변경 빈도 증가** → 스프린트 단위 백로그 관리
2. **권한 처리 복잡도 증가** → 초기에 권한 매트릭스 문서화
3. **DB 성능 저하** → 인덱스 전략/쿼리 프로파일링 조기 적용
4. **파일 업로드 이슈** → 저장소 인터페이스 추상화로 교체 용이성 확보

---

## 7. 산출물
- 요구사항 명세서
- ERD 및 API 명세서
- 소스코드 저장소
- 테스트 보고서
- 배포 가이드
- 운영/장애 대응 가이드

---

## 8. 다음 액션 (즉시 실행)
1. Spring Initializr로 기본 프로젝트 생성
2. DB/Redis 포함한 `docker-compose.yml` 작성
3. 공통 모듈(`common`) 및 인증 모듈(`auth`)부터 구현 시작
4. 1차 API 스펙 확정 후 개발 착수

---

## 9. 실행 명령 예시
```bash
# 프로젝트 이동
cd /home/ubuntu/projects/lms

# (예시) Spring Boot 실행
./gradlew bootRun

# 테스트 실행
./gradlew test
```

---

## 10. 현재 적용된 초기 구조 (요청 반영)
- 화면: **Thymeleaf**
- DB: **MySQL**
- 데이터 접근: **JdbcTemplate** (Repository에서 SQL 직접 확인 가능)
- JPA: **테이블 검증 전용** (`spring.jpa.hibernate.ddl-auto=validate`)

### 코드 구조
```text
com.example.lms
 ├─ controller
 │   └─ HomeController
 └─ course
     ├─ controller
     │   ├─ CourseController
     │   └─ CourseForm
     ├─ service
     │   └─ CourseService
     ├─ repository
     │   └─ CourseRepository   (JdbcTemplate)
     ├─ domain
     │   └─ Course
     └─ entity
         └─ CourseTable        (JPA 검증용)
```

### 확인 포인트
1. `application.yaml`의 MySQL 계정/비밀번호를 로컬 환경에 맞게 수정
2. 애플리케이션 실행 후 `http://localhost:8080/courses` 접속
3. 로그에서 JdbcTemplate SQL 출력 확인
