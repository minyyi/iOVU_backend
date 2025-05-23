## 📌 iOVU-BACK

> iOVU-BACK는 여행 일정 관리 서비스인 iOVU의 백엔드 프로젝트입니다.  
> Spring Boot 기반으로 사용자 인증, 소셜 로그인, 여행 일정 및 장소 정보 제공 등의 RESTful API를 제공합니다.

---

### 🛠 기술 스택

- **Framework**: Spring Boot
- **ORM**: MyBatis
- **Database**: Oracle
- **Build Tool**: Maven
- **Security**: Spring Security + JWT
- **API 문서화**: Swagger

---

### 📁 프로젝트 구조 (일부)

```
iOVU-BACK/
├── pom.xml                   # Maven 설정 파일
├── mvnw / mvnw.cmd           # Maven Wrapper
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...           # Java 소스코드
│   │   └── resources/
│   │       ├── application.yml
│   │       └── mapper/       # MyBatis 매퍼 XML
│   └── test/                 # 테스트 코드
```

---

### 🚀 실행 방법

```bash
# 1. 의존성 설치
./mvnw clean install

# 2. 애플리케이션 실행
./mvnw spring-boot:run
```

---

### 🔐 환경 설정

`src/main/resources/application.yml` 또는 `application.properties`에서 DB 및 JWT 설정을 구성해야 합니다:

```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@localhost:1521:xe
    username: YOUR_DB_USER
    password: YOUR_DB_PASSWORD

jwt:
  secret: your_jwt_secret
```

---

### 📚 API 문서

[SwaggerHub 문서](https://app.swaggerhub.com/apis-docs/iouv/iOVU/3.0.0)에서 전체 API 명세를 확인할 수 있습니다.
