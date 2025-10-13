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
| **김민석** | 시장 데이터 | • Stock 모델<br>• StockService, PriceService<br>• 실시간 가격 시뮬레이션 |
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

### Week 1-2: 기초 구조 설정
- 프로젝트 환경 구성
- 기본 클래스 설계
- Repository 패턴 구현

### Week 3-4: MVP 핵심 기능
- 사용자 시스템 구현
- 거래 엔진 개발
- 가격 시뮬레이션 로직

### Week 5-6: 통합 및 연동
- MVC 패턴 적용
- 컴포넌트 간 연동
- 기본 UI 완성

### Week 7-8: 기능 고도화
- 추가 기능 개발
- 성능 최적화
- 예외 처리 강화

### Week 9-10: 테스트 및 문서화
- 통합 테스트
- 버그 수정
- 최종 발표 준비

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
- 김민석 - 시장 데이터 담당
- 김예지 - UI 담당
- 김정현 - 사용자 시스템 담당


---

**Last Updated**: 2025.10.13