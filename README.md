# 🚀 goonggeum Homepage Project

## 1) 프로젝트 소개 (한 줄 요약)
우주 테마 감성의 **개인 블로그형 미니 홈페이지**로, 로그인 기반 프로젝트 열람 기능까지 포함한 Spring Boot 웹 프로젝트입니다.

---

## 2) 기술 스택
- **Backend**: Spring Boot 4, Spring MVC, JdbcTemplate
- **Frontend**: Thymeleaf, HTML/CSS/JavaScript
- **Database**: MySQL (Local, Port 3308)
- **Build Tool**: Gradle
- **Auth**: Session 기반 로그인 + BCrypt 비밀번호 암호화

---

## 3) 실행 방법
### 3-1. 프로젝트 이동
```bash
cd /home/ubuntu/projects/lms
```

### 3-2. DB 설정 확인
`src/main/resources/application.properties`
- `spring.datasource.url=jdbc:mysql://localhost:3308/lms?...`
- `spring.datasource.username=abcde`
- `spring.datasource.password=12345`

### 3-3. 앱 실행
```bash
./gradlew bootRun
```

### 3-4. 접속 주소
- 메인: `http://localhost:8080/`
- 로그인: `http://localhost:8080/login`
- 회원가입: `http://localhost:8080/signup`

---

## 4) 주요 기능
- **메인 홈페이지 구성**
  - About / Projects / Contact 섹션
  - 우주 이미지 카드 기반 프로젝트 소개
- **인증 기능**
  - 회원가입 / 로그인 / 로그아웃
  - 세션 로그인 상태 유지
- **프로젝트 보호 기능**
  - 프로젝트 상세(1,2,3)는 로그인 후 접근 가능
  - 비로그인 상태에서 카드 잠금(🔒) UI 표시
- **UI/UX 개선**
  - 메인 우측 로그인/회원가입 버튼
  - 로그인 페이지 중앙 정렬 + 세련된 카드형 스타일
  - 로그인 아이콘/입력창 hover 힌트(데모용)

---

## 5) 디렉토리 구조
```text
src/main
├── java/com/example/lms
│   ├── controller
│   │   ├── HomeController.java
│   │   ├── AuthController.java
│   │   └── ProjectController.java
│   ├── service
│   │   └── AuthService.java
│   ├── repository
│   │   └── AuthRepository.java
│   └── domain
│       └── Member.java
└── resources
    ├── application.properties
    ├── schema.sql
    ├── data.sql
    ├── templates
    │   ├── index.html
    │   ├── login.html
    │   ├── signup.html
    │   ├── project1.html
    │   ├── project2.html
    │   └── project3.html
    └── static
        ├── css/style.css
        ├── js/main.js
        └── images/space/*
```

---

## 6) API 또는 화면 사용 방법
### 화면 라우팅
- `GET /` : 메인 홈
- `GET /login` : 로그인 페이지
- `GET /signup` : 회원가입 페이지
- `GET /project/1` : SPACE ONE 상세 (로그인 필요)
- `GET /project/2` : SPACE TWO 상세 (로그인 필요)
- `GET /project/3` : SPACE THREE 상세 (로그인 필요)

### 인증 처리
- `POST /signup` : 회원가입 처리
- `POST /login` : 로그인 처리
- `GET /logout` : 로그아웃 후 메인으로 이동

### 데모 계정
- 아이디: `admin`
- 비밀번호: `1234`

> 참고: 본 프로젝트는 LMS 플랫폼 구축이 아닌, 개인 블로그형/포트폴리오형 홈페이지 구현에 초점을 맞춘 과제용 프로젝트입니다.
