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
