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
 - [ ] 프로필 수정 기능
   - [ ] password 확인 후 일치할 경우 수정가능
   - [ ] nickname, userinfo 를 입력받아 회원정보 수정
 - [ ] 로그아웃 기능
   - [ ] 전달받은 JWT를 blackList에 등록해 로그아웃 처리

 -[x] 관심상품 기능
   -[x] 유저와 상품을 저장
 - [x] 관심상품 조회
   - [x] productId, title, productInfo를 반환