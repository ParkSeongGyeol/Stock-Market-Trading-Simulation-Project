# 📈 Stock Market Simulation Project

## 📌 프로젝트 개요
**모의 주식 투자 시스템** - 가상 자금으로 주식 거래를 체험할 수 있는 자바 기반 시뮬레이션 프로그램

### 🎯 MVP 목표
사용자가 가입 후 1,000만원의 가상 자금으로 주식을 매수/매도할 수 있는 최소 기능 시스템 구현

### 📅 개발 기간
- **총 기간**: 10주 (2025.09 - 2025.11)
- **MVP 완성**: 4주차
- **기능 고도화**: 5-8주차
- **테스트 및 문서화**: 9-10주차

---

## 👥 팀원 및 역할 분담

| 이름 | 담당 영역 | 주요 개발 항목 |
|------|-----------|----------------|
| **박성결** | 거래 엔진 | • Order, Transaction 모델<br>• OrderService, TradeService<br>• 주문 처리 및 체결 로직 |
| **윤진석** | 포트폴리오 관리 | • Portfolio, Holding 모델<br>• PortfolioService<br>• 자산 관리 및 수익률 계산 |
| **김민성** | 시장 데이터 | • Stock 모델<br>• StockService, PriceService<br>• 실시간 가격 시뮬레이션 |
| **김예지** | 사용자 인터페이스 | • 모든 View 클래스<br>• MainController<br>• 사용자 입출력 처리 |
| **김정현** | 사용자 시스템 | • User 모델<br>• UserService, AuthService<br>• 회원가입/로그인 기능 |

---

## 🛠 기술 스택

- **Language**: Java 18
- **IDE**: Eclipse IDE
- **Version Control**: Git / GitHub
- **Build Tool**: Eclipse Default Builder
- **UI**: Console Application

---

## 📁 프로젝트 구조

```
StockMarketProject/
│
├── src/
│   ├── main/
│   │   ├── Main.java                    # 프로그램 시작점
│   │   └── DemoMain.java                 # 시연용 메인
│   │
│   ├── model/                           # 데이터 모델 (엔티티)
│   │   ├── User.java                    # 사용자 정보
│   │   ├── Stock.java                   # 주식 종목 정보
│   │   ├── Order.java                   # 주문 정보
│   │   ├── Portfolio.java               # 포트폴리오 정보
│   │   ├── Holding.java                 # 보유 주식 정보
│   │   └── Transaction.java             # 거래 내역
│   │
│   ├── repository/                      # 데이터 저장소
│   │   ├── UserRepository.java          # 사용자 데이터 관리
│   │   ├── StockRepository.java         # 종목 데이터 관리
│   │   ├── OrderRepository.java         # 주문 데이터 관리
│   │   └── PortfolioRepository.java     # 포트폴리오 데이터 관리
│   │
│   ├── service/                         # 비즈니스 로직
│   │   ├── UserService.java             # 사용자 관련 서비스
│   │   ├── AuthService.java             # 인증 서비스
│   │   ├── StockService.java            # 종목 관련 서비스
│   │   ├── OrderService.java            # 거래 주문 서비스
│   │   ├── PortfolioService.java        # 포트폴리오 서비스
│   │   └── PriceService.java            # 가격 관리 서비스
│   │
│   ├── view/                            # 화면 (콘솔 UI)
│   │   ├── MainView.java                # 메인 메뉴 화면
│   │   ├── LoginView.java               # 로그인 화면
│   │   ├── StockListView.java           # 종목 리스트 화면
│   │   ├── TradeView.java               # 매수/매도 화면
│   │   └── PortfolioView.java           # 포트폴리오 화면
│   │
│   ├── controller/                      # 컨트롤러
│   │   ├── MainController.java          # 메인 컨트롤러
│   │   ├── TradeController.java         # 거래 컨트롤러
│   │   └── UserController.java          # 사용자 컨트롤러
│   │
│   ├── util/                            # 유틸리티
│   │   ├── InputValidator.java          # 입력값 검증
│   │   ├── PriceUpdateThread.java       # 가격 업데이트 스레드
│   │   └── SessionManager.java          # 세션 관리
│   │
│   └── exception/                       # 예외 클래스
│       ├── InsufficientBalanceException.java
│       └── InvalidOrderException.java
│
├── resources/                            # 리소스 파일
├── doc/                                  # 문서
├── .gitignore                           # Git ignore 파일
└── README.md                            # 프로젝트 설명서
```

---

## 🚀 시작하기

### Prerequisites
- JDK 18 이상
- Eclipse IDE
- Git

### 설치 및 실행

1. **Repository Clone**
```bash
git clone https://github.com/[username]/StockMarketProject.git
cd StockMarketProject
```

2. **Eclipse에서 프로젝트 Import**
```
File → Import → General → Existing Projects into Workspace
→ Select root directory → Browse → StockMarketProject 선택
→ Finish
```

3. **프로젝트 실행**
```
src/main/Main.java 우클릭 → Run As → Java Application
```

---

## 💡 주요 기능

### ✅ MVP 기능 (1-4주차)
- [x] 회원가입 및 로그인
- [x] 초기 자금 1,000만원 자동 부여
- [x] 종목 리스트 조회 (10-20개 주요 종목)
- [x] 시장가 매수/매도 주문
- [x] 실시간 가격 변동 시뮬레이션 (30초 간격, ±5%)
- [x] 포트폴리오 조회 (총 자산, 수익률)
- [x] 거래 내역 조회

### 🔄 추가 예정 기능 (5-8주차)
- [ ] 지정가 주문
- [ ] 종목별 상세 수익률
- [ ] 거래량 데이터
- [ ] 간단한 차트
- [ ] 친구 시스템
- [ ] 실시간 알림

---

## 📝 Git 협업 규칙

### Branch 전략
- `main`: 배포 가능한 안정적인 코드
- `develop`: 개발 중인 코드 통합
- `feature/기능명`: 개별 기능 개발

### Commit Message Convention
```
[이름] 작업내용
예: [박성결] Order 클래스 생성 및 기본 메서드 구현
```

### Pull Request 규칙
1. 기능 개발 완료 후 PR 생성
2. 최소 1명 이상의 리뷰 필요
3. 충돌 해결 후 Merge

---

## 📊 개발 일정

### 1주차: 기본 설계
| 거래 엔진 (박성결) | 포트폴리오 (윤진석) | 시장 데이터 (김민석) | UI (김예지) | 사용자 시스템 (김정현) |
|---|---|---|---|---|
| **Order.java**<br>- orderId, userId, stockCode<br>- orderType, quantity, price<br>- getter/setter 작성 | **Portfolio.java**<br>- userId, cashBalance<br>- totalAssets, totalProfit<br>- getter/setter 작성 | **Stock.java**<br>- stockCode, stockName<br>- currentPrice, previousPrice<br>- getter/setter 작성 | **MainFrame.java**<br>- 메뉴 출력 메서드<br>- "1.로그인 2.회원가입 3.종료"<br>- Scanner 입력 연결 | **User.java**<br>- userId, password, userName<br>- registeredDate<br>- getter/setter 작성 |

### 2주차: 저장소 구현
| 거래 엔진 (박성결) | 포트폴리오 (윤진석) | 시장 데이터 (김민석) | UI (김예지) | 사용자 시스템 (김정현) |
|---|---|---|---|---|
| **OrderRepository.java**<br>- ArrayList<br>- addOrder()<br>- getOrdersByUserId() | **PortfolioRepository.java**<br>- HashMap<String, Portfolio><br>- createPortfolio()<br>- getPortfolio() | **StockRepository.java**<br>- ArrayList<br>- 10개 종목 초기화<br>- getAllStocks() | **LoginView.java**<br>- showLoginMenu()<br>- ID/PW 입력받기<br>- 입력값 검증 | **UserRepository.java**<br>- HashMap<String, User><br>- addUser()<br>- findUser() |

### 3주차: 핵심 기능 1
| 거래 엔진 (박성결) | 포트폴리오 (윤진석) | 시장 데이터 (김민석) | UI (김예지) | 사용자 시스템 (김정현) |
|---|---|---|---|---|
| **OrderService.java**<br>- validateBuyOrder()<br>- 잔액 확인 로직<br>- 수량 검증 로직 | **PortfolioService.java**<br>- updateCashBalance()<br>- 입금/출금 처리<br>- 잔액 복구 체크 | **PriceService.java**<br>- updatePrice()<br>- Random 클래스 사용<br>- -5% ~ +5% 계산 | **StockListView.java**<br>- displayStockList()<br>- 종목 번호, 가격<br>- 선택 메뉴 구현 | **UserService.java**<br>- register() 구현<br>- ID 중복 체크<br>- 초기자금 1000만원 |

### 4주차: 핵심 기능 2
| 거래 엔진 (박성결) | 포트폴리오 (윤진석) | 시장 데이터 (김민석) | UI (김예지) | 사용자 시스템 (김정현) |
|---|---|---|---|---|
| **매수 주문 처리**<br>- processBuyOrder()<br>- 주문 생성 및 저장<br>- 체결 완료 메시지 | **Holding.java**<br>- stockCode, quantity<br>- avgPrice 변수<br>- addHolding() | **StockService.java**<br>- getStockByCode()<br>- getStockPrice()<br>- 종목 검색 | **TradeView.java**<br>- showBuyMenu()<br>- 종목 선택, 수량 입력<br>- 주문 확인 메시지 | **로그인 기능**<br>- login() 구현<br>- 비밀번호 검증<br>- 세션 관리(static) |

### 5주차: 거래 완성
| 거래 엔진 (박성결) | 포트폴리오 (윤진석) | 시장 데이터 (김민석) | UI (김예지) | 사용자 시스템 (김정현) |
|---|---|---|---|---|
| **매도 주문 처리**<br>- processSellOrder()<br>- 보유 수량 확인<br>- 체드 체결 처리 | **수익률 계산**<br>- calculateProfit()<br>- 평가손익 계산<br>- 수익률(%) 계산 | **PriceUpdateThread.java**<br>- Thread 상속 클래스<br>- 30초마다 업데이트<br>- run() 메서드 | **PortfolioView.java**<br>- displayPortfolio()<br>- 종자산, 현금, 수익률<br>- 보유 종목 리스트 | **로그아웃 기능**<br>- logout() 구현<br>- 세션 정보 초기화<br>- 메인 메뉴 이동 |

### 6주차: 통합 테스트
| 거래 엔진 (박성결) | 포트폴리오 (윤진석) | 시장 데이터 (김민석) | UI (김예지) | 사용자 시스템 (김정현) |
|---|---|---|---|---|
| **거래 내역 조회**<br>- getOrderHistory()<br>- 최근 거래 10건<br>- 거래 내역 포맷팅 | **포트폴리오 업데이트**<br>- updateAfterTrade()<br>- 매수/매도 후 갱신<br>- 보유 종목 갱신 | **종목 정보 갱신**<br>- refreshAllPrices()<br>- 전체 가격 업데이트<br>- 변동률 계산 | **MainController.java**<br>- 화면 전환 처리<br>- 사용자 입력 처리<br>- 각 기능 연결 | **AuthService.java**<br>- isLoggedIn()<br>- 각 기능별 권한<br>- 미로그인 처리 |

### 7주차: 예외 처리
| 거래 엔진 (박성결) | 포트폴리오 (윤진석) | 시장 데이터 (김민석) | UI (김예지) | 사용자 시스템 (김정현) |
|---|---|---|---|---|
| **OrderException.java**<br>- try-catch 추가<br>- 잔액 부족 처리<br>- 예러 메시지 개선 | **데이터 검증**<br>- 음수 잔액 방지<br>- 보유 수량 검증<br>- 계산 오류 제크 | **가격 데이터 검증**<br>- 0원 당지 로직<br>- 급등/급락 제한<br>- 데이터 무결성 | **InputValidator.java**<br>- 숫자 입력 검증<br>- 메뉴 선택 범위<br>- 빈값 체크 구현 | **로그인 보안**<br>- 최소 길이 체크<br>- 로그인 실패 처리<br>- 중복 로그인 방지 |

### 8주차: 기능 개선
| 거래 엔진 (박성결) | 포트폴리오 (윤진석) | 시장 데이터 (김민석) | UI (김예지) | 사용자 시스템 (김정현) |
|---|---|---|---|---|
| **거래 로그 파일**<br>- FileWriter 사용<br>- 날짜별 로그 생성<br>- 거래 기록 저장 | **종목별 수익률**<br>- 개별 종목 손익<br>- 수익 TOP3 조회<br>- 종목 순위 표시 | **실시간 알림**<br>- 5% 이상 변동 알림<br>- 목표가 도달 알림<br>- 콘솔 메시지 표시 | **UI 개선**<br>- 테이블 형태 출력<br>- 색상 코드(ANSI)<br>- 화면 clear 기능 | **UserProfile.java**<br>- 가입일 표시<br>- 총 거래 횟수<br>- 최고 수익률 기록 |

### 9주차: 테스트
| 거래 엔진 (박성결) | 포트폴리오 (윤진석) | 시장 데이터 (김민석) | UI (김예지) | 사용자 시스템 (김정현) |
|---|---|---|---|---|
| **거래 테스트**<br>- 정상 거래 테스트<br>- 잔액 부족 테스트<br>- 수량 초과 테스트 | **수익률 테스트**<br>- 단일 종목 테스트<br>- 복수 종목 테스트<br>- 손실 상황 테스트 | **가격 변동 테스트**<br>- 랜덤 변경 테스트<br>- 스레드 동작 확인<br>- 데이터 동기화 | **UI 통합 테스트**<br>- 화면 전환 테스트<br>- 잘못된 입력 처리<br>- 종료 처리 확인 | **사용자 테스트**<br>- 중복 가입 테스트<br>- 세션 유지 테스트<br>- 동시 사용자 테스트 |

### 10주차: 최종 마무리
| 거래 엔진 (박성결) | 포트폴리오 (윤진석) | 시장 데이터 (김민석) | UI (김예지) | 사용자 시스템 (김정현) |
|---|---|---|---|---|
| **README.md 작성**<br>- 전체 시스템 설명<br>- API 문서 작성<br>- 주요 기능 설명 | **성능 측정**<br>- 응답 시간 측정<br>- 메모리 사용량<br>- 병목 구간 개선 | **DemoDataGenerator**<br>- 샘플 시용 생성<br>- 거래 내역 생성<br>- 시연 시나리오 | **DemoMain.java**<br>- 자동 시연 모드<br>- 주요 기능 하이라이트<br>- 에러 상황 대처 | **최종 테스트**<br>- 전체 기능 테스트<br>- 버그 리스트 작성<br>- 발표 자료 준비 |

---

## 🧪 테스트

### 테스트 시나리오
```
1. 회원가입 → 2. 로그인 → 3. 종목 조회 → 4. 매수 주문
→ 5. 포트폴리오 확인 → 6. 매도 주문 → 7. 수익률 확인
```

---


## 🤝 Contributing

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m '[이름] Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📞 Contact

프로젝트 관련 문의사항이 있으시면 아래 팀원들에게 연락 주세요:

- 박성결 - 거래 엔진 담당
- 윤진석 - 포트폴리오 담당
- 김민성 - 시장 데이터 담당
- 김예지 - UI 담당
- 김정현 - 사용자 시스템 담당


---

**Last Updated**: 2025.10.13
