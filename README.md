### 기능명세서
 - [ ] 회원가입 기능
   - [ ] email, nickname, password, userinfo 를 입력받아 저장
   - [ ] password 암호화 저장
   - [ ] 중복된 email, nickname 일 경우 예외처리
 - [ ] 로그인 기능
   - [ ] email, password 를 입력받아 확인 후 로그인성공여부
   - [ ] Header에 JWT를 담아 반환
 - [ ] 프로필 조회 기능
   - [ ] Header 의 JWT를 통해 유저정보를 확인후, 유저의 정보를 반환

 -[ ] 위시리스트 기능
   -[ ] 유저와 상품을 저장
 - [ ] 위시리스트 조회
   - [ ] productId, title, productInfo를 반환