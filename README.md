# ⏰ studywork

## 디렉토리 구조
<details>
<summary> <strong> 구조도 </strong> </summary>
<div markdown="1">
  
```
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── partimestudy
    │   │           └── assignment
    │   │               ├── AssignmentApplication.java
    │   │               ├── application
    │   │               │   ├── order
    │   │               │   │   └── OrderFacade.java
    │   │               │   └── user
    │   │               │       └── UserFacade.java
    │   │               ├── domain
    │   │               │   ├── challenge
    │   │               │   │   ├── Challenge.java
    │   │               │   │   ├── ChallengeReader.java
    │   │               │   │   ├── ChallengeService.java
    │   │               │   │   └── ChallengeServiceImpl.java
    │   │               │   ├── encrypt
    │   │               │   │   └── PasswordEncoder.java
    │   │               │   ├── exception
    │   │               │   │   ├── BadRequestException.java
    │   │               │   │   ├── BaseException.java
    │   │               │   │   ├── ErrorCode.java
    │   │               │   │   ├── ErrorResponse.java
    │   │               │   │   ├── ForbiddenException.java
    │   │               │   │   ├── InternalServerException.java
    │   │               │   │   ├── NotFoundException.java
    │   │               │   │   ├── UnAuthorizedException.java
    │   │               │   │   └── handler
    │   │               │   │       └── GlobalExceptionHandler.java
    │   │               │   ├── order
    │   │               │   │   ├── Order.java
    │   │               │   │   ├── OrderCommand.java
    │   │               │   │   ├── OrderInfo.java
    │   │               │   │   ├── OrderReader.java
    │   │               │   │   ├── OrderService.java
    │   │               │   │   ├── OrderServiceImpl.java
    │   │               │   │   ├── OrderStore.java
    │   │               │   │   ├── docs
    │   │               │   │   │   └── OrderApiControllerDocs.java
    │   │               │   │   └── payment
    │   │               │   │       ├── PayMethod.java
    │   │               │   │       └── PaymentProcessor.java
    │   │               │   ├── token
    │   │               │   │   ├── TokenInfo.java
    │   │               │   │   ├── TokenProvider.java
    │   │               │   │   ├── TokenService.java
    │   │               │   │   └── TokenServiceImpl.java
    │   │               │   └── user
    │   │               │       ├── EncryptedPassword.java
    │   │               │       ├── User.java
    │   │               │       ├── UserCommand.java
    │   │               │       ├── UserInfo.java
    │   │               │       ├── UserReader.java
    │   │               │       ├── UserService.java
    │   │               │       ├── UserServiceImpl.java
    │   │               │       ├── UserStore.java
    │   │               │       └── docs
    │   │               │           └── UserApiControllerDocs.java
    │   │               ├── infrastructure
    │   │               │   ├── challenge
    │   │               │   │   ├── ChallengeReaderImpl.java
    │   │               │   │   └── ChallengeRepository.java
    │   │               │   ├── common
    │   │               │   │   ├── properties
    │   │               │   │   │   └── JwtProperties.java
    │   │               │   │   └── util
    │   │               │   │       ├── Sha256Encoder.java
    │   │               │   │       └── TokenGenerator.java
    │   │               │   ├── config
    │   │               │   │   ├── FilterConfig.java
    │   │               │   │   ├── JwtConfig.java
    │   │               │   │   ├── QueryDslConfig.java
    │   │               │   │   ├── SwaggerConfig.java
    │   │               │   │   └── WebConfig.java
    │   │               │   ├── jwt
    │   │               │   │   └── JwtProvider.java
    │   │               │   ├── order
    │   │               │   │   ├── OrderReaderImpl.java
    │   │               │   │   ├── OrderRepository.java
    │   │               │   │   ├── OrderStoreImpl.java
    │   │               │   │   └── payment
    │   │               │   │       ├── NaverPayApiCaller.java
    │   │               │   │       ├── PaymentApiCaller.java
    │   │               │   │       ├── PaymentProcessorImpl.java
    │   │               │   │       └── PgCardApiCaller.java
    │   │               │   ├── querydsl
    │   │               │   │   └── OrderQueryDslRepository.java
    │   │               │   └── user
    │   │               │       ├── UserReaderImpl.java
    │   │               │       ├── UserRepository.java
    │   │               │       └── UserStoreImpl.java
    │   │               └── interfaces
    │   │                   ├── filter
    │   │                   │   ├── AuthExceptionHandlerFilter.java
    │   │                   │   └── JwtFilter.java
    │   │                   ├── order
    │   │                   │   ├── OrderApiController.java
    │   │                   │   ├── OrderDto.java
    │   │                   │   └── OrderDtoMapper.java
    │   │                   ├── support
    │   │                   │   ├── Auth.java
    │   │                   │   ├── AuthArgumentResolver.java
    │   │                   │   └── AuthenticationContext.java
    │   │                   └── user
    │   │                       ├── UserApiController.java
    │   │                       ├── UserDto.java
    │   │                       └── UserDtoMapper.java
    │   └── resources
    │       ├── application-secret.yml
    │       └── application.yml
    └── test
        └── java
            └── com
                └── partimestudy
                    └── assignment
                        ├── domain
                        │   ├── order
                        │   │   └── OrderServiceImplTest.java
                        │   └── user
                        │       └── UserServiceImplTest.java
                        └── infrastructure
                            ├── common
                            │   └── util
                            │       └── Sha256EncoderTest.java
                            └── jwt
                                └── JwtProviderTest.java
```

</details>

## 애플리케이션 DB 초기화 및 실행
- docker-compose.yml 파일이 존재하는 경로에서 아래 명령어를 수행해 DB 초기화를 수행합니다.
```
docker-compose up -d
```

## 구현방향
- 기본적으로 Layered Architecture를 사용해서 구현했습니다. 각 레이어의 역할은 아래와 같습니다.


| Layer | Description | 주요 객체 |
|------|--------|------|
| interfaces | 사용자에게 정보를 보여주고 사용자의 명령을 해석하는 레이어 | Controller, Dto, Mapper |
| application | 수행할 작업을 정의하고 도메인 객체가 문제를 해결하도록 조합시키는 레이어 | Facade |
| domain | 비즈니스 개념과 비즈니스 상황에 대한 정보, 비즈니스 규칙을 표현하는 레이어 | Entity, Service, Command, Info, Reader, Store, ServiceImpl  |
| infrastructure | 상위 계층을 지원하는 일반화된 기술적 기능을 제공하는 레이어 | low level 구현체 (ex. ReaderImpl, StoreImpl, Repository) |

- 각 레이어간의 참조관계에서 `application`, `infrastructure` 레이어에는 `domain` 레이어를 바라보게 하며 **양방향 참조는 허용하지 않습니다**.
  - 이를 위해 대부분의 주요 로직은 추상화 시켰으며, 런타임시에는 DIP를 이용해 실제 구현체가 동작하도록 했습니다.
- 레이어별 구현은 아래와 같습니다.
  - `domain` 레이어에서의 XxxService 클래스는 해당 도메인의 기능을 명시했습니다.
    - 이때 도메인 로직에서는 어떤 기술을 사용했는지는 중요하지 않고, 어떤 업무를 수행하는지가 중요합니다.
    - 따라서 interface를 통해 업무를 추상화하고, 구체적인 구현은 infrastructure 레이어에 위임합니다.
  - `infrastucture` 레이어에서는 domain 레이어에서 선언되는 interface의 구현체들을 가지고 있으며, runtime시에 실제 로직이 동작하게 합니다.
  - `application` 레이어에서는 수행할 작업을 정의합니다.
    - 도메인 객체들이 문제를 해결하도록 지시하며 비즈니스 규칙을 가지지 않습니다. 그렇기 때문에 해당 레이어는 얇게 유지됩니다.
    - transactional 묶는 도메인 로직과 그 외의 로직을 묶는 역할로 한정짓습니다.
  - `interfaces` 레이어는 사용자에게 정보를 보여주고 사용자의 명령을 해석하는 역할을 합니다.
    - 요구하는 request & response는 필요한 만큼 최소한으로 가져가려 노력합니다. 요구하는 request & response parameter가 많다는 것은
      해당 API가 많은 역할을 하고 있다는 것을 의미할 수 있기 때문입니다.
    - API는 한번 오픈하게 되면 바꾸는데 많은 노력이 들어가며 처음부터 제한적으로 설계하려 하고 있습니다.     

## 클래스별 역할
| 클래스명 | 역할 |
|------|------|
| XxxCommand | input 파라미터, Service 메서드의 처리와 조회를 위한 파라미터 |
| XxxInfo | Output 파라미터, DB에서 조회하여 가져온 Entity를 그대로 리턴하지 않기 위함 |
| XxxReader | DB에서 데이터를 읽어다가 객체로 만드는 역할 |
| XxxStore | 객체를 직렬화해서 DB에 넣는 역할 |

### [회원가입](http://localhost:8080/swagger-ui/index.html#/%EC%82%AC%EC%9A%A9%EC%9E%90%20API/signup)
- 회원가입 기능을 구현했습니다.
  - 사용자의 비밀번호를 암호화했습니다.
  - user의 정보로 취급되는 id를 사용하는 것보다 userToken으로 user에 대한 정보를 다루었습니다.
- 회원가입 기능을 테스트 했습니다.
  - 회원가입 기능에 대한 테스트를 작성했습니다.
  - 암호화에 대한 테스트를 작성했습니다.

### [로그인](http://localhost:8080/swagger-ui/index.html#/%EC%82%AC%EC%9A%A9%EC%9E%90%20API/login)
- 로그인 기능을 구현했습니다.
  - 저장해둔 salt값으로 사용자가 입력한 비밀번호와 일치하는지 검증했습니다.
- 로그인 기능을 테스트했습니다.
  - 로그인 기능에 대한 테스트를 작성했습니다.

### [내 정보 조회](http://localhost:8080/swagger-ui/index.html#/%EC%82%AC%EC%9A%A9%EC%9E%90%20API/userDetails)
- 내 정보 조회 기능을 구현했습니다.
  - 잘못된 jwt 인증 토큰을 보낼 시, Exception이 발생하도록 했습니다.
- 내 정보 조회 기능을 테스트했습니다.

### [챌린지 주문(신청)](http://localhost:8080/swagger-ui/index.html#/%EC%B1%8C%EB%A6%B0%EC%A7%80%20%EC%A3%BC%EB%AC%B8(%EC%8B%A0%EC%B2%AD)%20API/register)
- 챌린지 주문(신청) 기능을 구현했습니다.
  - 이미 신청한 챌린지를 주문(신청)시, Exception이 발생하도록 했습니다.
  - 이미 종료된 챌린지를 신청하면 Exception이 발생하도록 했습니다.
  - 보증금이 최소 보증금 보다 적거나, 최대 보증금 보다 많다면 Exception이 발생하도록 했습니다.
- 챌린지 주문(신청) 결제 기능을 구현했습니다.
  - 결제 기능은 별도의 API를 두어 분리하려 했지만, 한번에 결제까지 성공하는 해피 케이스가 아닌, 결제 실패 케이스도 고려하였을 때
    결제가 실패한다면 챌린지 주문(신청) 또한 롤백되어야 한다 생각하여 챌린지 주문(신청) 기능에 함께 두었습니다.
- 챌린지 주문(신청) 기능을 테스트 했습니다.

### [챌린지 주문(신청) 내역 조회](http://localhost:8080/swagger-ui/index.html#/%EC%B1%8C%EB%A6%B0%EC%A7%80%20%EC%A3%BC%EB%AC%B8(%EC%8B%A0%EC%B2%AD)%20API/retrieve)
- 챌린지의 주문(신청) 내역을 조회하는 기능을 구현했습니다.
  - 주문(신청) 내역 조회 시, order 테이블에 저장한 userToken과 accessToken으로 가져온 UserToken이 다르다면 Exception이 발생하도록 했습니다.
- 챌린지의 주문(신청) 내역을 조회하는 기능을 테스트 했습니다.

### [챌린지 주문(신청) 내역 전체 조회](http://localhost:8080/swagger-ui/index.html#/%EC%B1%8C%EB%A6%B0%EC%A7%80%20%EC%A3%BC%EB%AC%B8(%EC%8B%A0%EC%B2%AD)%20API/retrieveAll)
- 챌린지 주문(신청) 내역을 전체 조회하는 기능을 구현했습니다.
  - querydsl을 이용하여 조건에 맞추어 검색이 가능하도록 구현했습니다.
- 챌린지 주문(신청) 내역을 전체 조회 기능을 테스트했습니다.
