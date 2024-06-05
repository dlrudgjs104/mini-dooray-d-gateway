# mini-dooray-d-gateway

> gateway 서버는 모든 서비스 요청을 받으며 프레젠테이션 기능을 담당합니다.

<br/>

![img.png](img.png)

- TemplateEngine(Thymeleaf) 사용하여 화면을 표시합니다.

- 데이터는 AccountApi, TaskApi 를 RestTemplate 으로 호출하여 받아 옵니다.

- 화면정보를 표시할때 AccountApi, TaskApi 를 조합해서 제공할 수 있어야 합니다.

- gateway 는 사용자의 인증을 담당합니다.

  - 인증 세션은 gateway 서버에서 redis 를 사용하여 관리합니다.

  - 인증 데이터는 Account-Api 를 사용합니다.

- AccountApi 는 멤버의 정보를 관리합니다.

- ProjectApi 는 Project, Task, Comment, Tag 를 관리 합니다.

<br/>


## 요구사항 정리 

### Auth
- [ ] 로그인 기능  `POST /login`
  - ID / PW
  - GitHub OAuth (추가)
- [ ] 로그아웃 기능   
- [x] 게이트웨이 서버에서 Redis를 사용한 **세션 관리 기능**
  - spring security + spring session
    - security 가 로그인한 사용자 정보( `Authentication` )를 SecurityContext에 저장하고, 
    - SecurityContextPersistenceFilter가 SecurityContext를 현재 HTTP 세션에 저장한다. 
    - spring-session-redis는 현재 세션을 Redis에 저장한다. 


### Routing
- [x] 클라이언트 요청을 Account Api 및 Task Api로 라우팅
- [x] URL 매핑 설정
<br/>
-> FeignClient로 데이터 호출 및 조합 




### Test