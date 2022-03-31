# 빈자리를 찾아줘!  
  

- 코로나19 방지를 위한 카페, 음식점 혼잡도 안내 서비스   

- 진행기간 : 2021년 5월 1일 → 2021년 12월 31일  

- 팀 구성 : Android 개발자 1, Server 개발자 1, 디자인 개발자 1 

- 직책 : 팀장  
  
- 사용 기술 : AWS EC2, AWS IVS, AWS RDS, Android, Apache, Figma, JAVA, Kakao Maps API, MySQL, OpenCV, PHP, Python, YOLO, Zeplin, 서울시공공데이터 API  

- 한국정보과학회[KSC] 인공지능 부문 학부생 논문 제출  






---

## 📖 상세 내용

<img src="https://user-images.githubusercontent.com/77629920/161027948-748d30d2-fb09-4d81-9ca5-8774b0163fee.png" width="200" height="400"/> <img src="https://user-images.githubusercontent.com/77629920/161027974-e71244ce-5914-4c27-8daa-42505776228d.png" width="200" height="400"/>


- **개발 목적**
    - 코로나19로 인한 사회적 거리두기 정책 시행, 카페 내 착석 시 띄어앉기 및 수용인원 제한
    - 카페 방문 전 카페 내 혼잡도를 확인하고 여유좌석을 확보하기위한 방안 필요
    - 유사 애플리케이션의 혼잡도 측정방법 한계 인식
    - 현장에 설치된 cctv나 부착된 카메라를 사용하여 추가 단말기 설치 부담 해소

- **솔루션**
    - 카페 내 cctv를 실시간으로 서버로 전송해 입/퇴장 인원을 측정한다.
    - 측정된 인원을 카페 내 수용면적과 비교하여 혼잡도를 계산한다.
    - 계산된 혼잡도를 4단계로 나누고, 각 단계별 아이콘 색상을 달리하여 사용자의 화면에 표시한다.
    - 사용자는 거리순, 혼잡순으로 카페 정보를 조회할 수 있다.
    

## ⚒️ 사용 기술 및 라이브러리

- Android, Java
- PHP, MySQL, Apache
- AWS Interactive Video Service, AWS EC2, AWS RDS
- Kakao Map API
- 서울시 공공 데이터 - 용산구 음식점 API
- YOLO, OpenCV
- Figma, Zeplin

## 📱 담당한 기능

### Server(80%)

- AWS EC2 - RDS MySQL 연동 및 데이터베이스 관리
- 실시간 전송 영상 SQL문 처리
- 클라이언트-서버 PHP 전송

### YOLO 객체인식 시스템 개발(70%)

- YOLO를 사용한 객체인식과 기준선, 방향변수 설정 연구 진행
- 수용인원 산정식 구현
- 객체인식 시연 및 테스트 진행

### Android(50%)

- Kakao Map API 연동
- 거리순/혼잡순 조회 기능 구현
- 전체 레이아웃 Recycler View 구현
- 카페 이미지 랜덤 팝업 구현
- 상세정보 페이지 구현  


  
