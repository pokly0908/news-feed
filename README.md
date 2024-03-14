### 스프링 심화코드 개선과제

- [ ] Controller Advice로 예외 공통화 처리
- [x] Service Interface화
    - 모든 Service를 Interface화
- [x] Custom Exception 및 Spring AOP 적용
    - 각 Controller 실행시 실행되는 메소드명 출력
    - NotFoundException, PermissionException, NotEqualException 추가
- [x] QueryDSL 사용
    - UserRepository 분리 후 QueryDSL사용
- [x] Pageable 사용
    - 상품조회하기 Page화
      (ex)[GET] http://localhost:8080/api/product?page=1&size=5
- [ ] Test 코드 작성
- [ ] 배포하기

### 기능명세서

- [x] 회원가입 기능
    - [x] email, nickname, password, userinfo 를 입력받아 저장
    - [x] password 암호화 저장
    - [x] 중복된 email, nickname 일 경우 예외처리
- [x] 로그인 기능
    - [x] email, password 를 입력받아 확인 후 로그인성공여부
    - [x] Header에 JWT를 담아 반환
- [x] 프로필 조회 기능
    - [x] Header 의 JWT를 통해 유저정보를 확인후, 유저의 정보를 반환
- [x] 프로필 수정 기능
    - [x] password 확인 후 일치할 경우 수정가능
    - [x] nickname, userinfo 를 입력받아 회원정보 수정
- [x] 로그아웃 기능
    - [x] 전달받은 JWT를 blackList에 등록해 로그아웃 처리

- [x] 관심상품 기능
    - [x] 유저와 상품을 저장
- [x] 관심상품 조회
    - [x] productId, title, productInfo를 반환

- [x] 댓글 CRUD 기능
    - [x] 특정 상품의 댓글 조회 기능
    - [x] 특정 유저가 작성한 댓글 조회 기능
    - [x] 특정 상품에 댓글 작성 기능
    - [x] 댓글 수정 기능
    - [x] 댓글 삭제 기능

- [x] Test 코드
    - [x] Entity, DTO Test 코드
    - [x] Controller Test 코드
    - [x] Service Test 코드
    - [x] Repository Test 코드
