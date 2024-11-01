# Handong Feed Prototype

https://feed.handong.app/

**한동대학교의 소식을 한 번에 볼 수 있게 도와줄 Handong Feed의 프로토타입 버전입니다.**

분산되어 있어 보기 어려운 한동대학교의 소식을 한 곳에서 쉽고 빠르게 볼 수 있는 서비스를 기획하고 있습니다.

인증 방식(로그인) 구현 이후 실명 카톡 방에 올라오는 내용을 추려 피드에 추가할 계획입니다.

## MVP 계획

### 게시글

- [x] 게시글 쓰기 / 읽기 기능
- [ ] 게시글 추천
- [ ] 게시글 댓글
- [ ] 이미지 업로드

### 인증

- [ ] 한동대 구글 로그인

## 향후 방향

![HandongApp](https://github.com/user-attachments/assets/8708b75a-3e82-4976-95e1-466138e6aa5a)

1. 기본적인 MVP 를 만들어 한동대학교 구성원 대상 유저 테스트 및 피드백 시작.
2. 일반화(generalize) 작업을 하고 다른 커뮤니티에서도 사용할 수 있도록 HSF (Handong Software Foundation)에 지원.
3. [Handong Newsletter](https://github.com/junglesub/handong-newsletter) 서비스와 연동하여 이메일로 피드 내용을 보내줄 수 있도록 개발.

## 개발 스택

- Spring Boot?
- React
- MYSQL

## 개발환경 - 실행 방법

### IntelliJ 사용

gradle dependencies 설치 후에 `RealsprApplication.java` 실행.

### 직접 실행

```sh
./gradlew bootRun
```

### 프론트 실행 방법

front 에서 `npm start` 이후 http://localhost:3000 바로 접속.

## 배포용 JAR 다운로드 방법

```sh
wget -O handong-feed-0.0.1-SNAPSHOT.jar https://github.com/junglesub/handong-feed-prototype/releases/latest/download/handong-feed-0.0.1-SNAPSHOT.jar
```

#2024-하계 웹캠프  
#2024-1 코딩클리닉 - 실전코딩/서버편
