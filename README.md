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