# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Stock Market Simulation Project - A Java-based console application for simulating stock trading with virtual funds. This is a team project (5 members) with a 10-week development timeline, currently in MVP phase.

**Team Responsibilities:**
- Park Seong-gyeol: Trade engine (Order, Transaction models; OrderService, TradeService)
- Yoon Jin-seok: Portfolio management (Portfolio, Holding models; PortfolioService)
- Kim Min-seok: Market data (Stock model; StockService, PriceService)
- Kim Ye-ji: User interface (All View classes; MainController)
- Kim Jeong-hyeon: User system (User model; UserService, AuthService)

## Technology Stack

- **Language**: Java 18
- **IDE**: Eclipse IDE
- **Build**: Eclipse Default Builder (no Maven/Gradle)
- **Runtime**: Console application

## Running the Project

Since this is an Eclipse project without a build tool:

1. **Run main application**:
   - Right-click `src/main/Main.java` → Run As → Java Application
   - Note: MainController initialization is currently commented out in Main.java

2. **Run demo/test**:
   - Right-click `src/main/DemoMain.java` → Run As → Java Application

3. **Test individual components**:
   - Many classes (e.g., `StockRepository.java`) have main() methods for standalone testing
   - Right-click the specific file → Run As → Java Application

## Architecture

### MVC Pattern Implementation

**Models** (`src/model/`):
- Core entities: User, Stock, Order, Portfolio, Holding, Transaction
- POJOs with getters/setters representing business domain

**Views** (`src/view/`):
- Console UI classes: MainView, LoginView, RegisterView, StockListView, TradeView, PortfolioView, OrderHistoryView
- Handle user input/output formatting
- Keep views simple - no business logic

**Controllers** (`src/controller/`):
- MainController: Application entry point and main menu routing
- TradeController: Handles buy/sell order flow
- UserController: Manages user registration/authentication
- PortfolioController: Portfolio display and analysis

### Data Layer

**Repositories** (`src/repository/`):
- In-memory data storage using HashMap/ArrayList
- No external database - all data is volatile
- Example pattern from UserRepository:
  ```java
  private final Map<String, User> userStore = new HashMap<>();
  ```
- StockRepository loads from `stock_data.txt` file (minimum 10 stocks required)

**Services** (`src/service/`):
- Business logic layer between controllers and repositories
- Key services:
  - **AuthService**: User authentication
  - **UserService**: User account management
  - **StockService**: Stock information queries
  - **OrderService**: Order creation and validation
  - **TradeService**: Order execution and matching
  - **PortfolioService**: Asset calculations and P&L
  - **PriceService**: Price updates and simulation

### Utilities (`src/util/`)

- **SessionManager**: Tracks currently logged-in user
- **PriceUpdateThread**: Background thread for simulating real-time price changes (30-second intervals, ±5% variance)
- **InputValidator**: Console input validation
- **ConsoleUtils**: Console formatting utilities
- **DataInitializer**: Bootstraps initial data
- **FileManager**: File I/O operations

### Configuration (`src/config/`)

- **AppConfig**: Application-wide configuration
- **Constants**: System constants (initial balance: 10,000,000 KRW, price update intervals, etc.)

### Custom Exceptions (`src/exception/`)

- InsufficientBalanceException: Not enough funds for order
- InvalidOrderException: Invalid order parameters
- StockNotFoundException: Stock code not found
- UserNotFoundException: User account not found

## Key Technical Details

### Price Simulation System
- Background thread (PriceUpdateThread) updates stock prices every 30 seconds
- Price fluctuation range: ±5% of current price
- Must be started when application launches
- Thread management is critical for proper shutdown

### Order Processing Flow
1. User input → TradeView
2. TradeController validates and forwards
3. OrderService creates Order object
4. TradeService executes if market order (matches immediately)
5. Transaction record created
6. Portfolio/Holding updated via PortfolioService
7. User balance adjusted

### Data Persistence
- Stock data loaded from `stock_data.txt` at startup
- Format: `code,name,currentPrice,openingPrice`
- File must contain at least 10 stocks or initialization fails
- All other data (users, orders, portfolios) is in-memory only

### Session Management
- Single-user session via SessionManager singleton
- Stores currently logged-in User object
- Check session state before executing trades or viewing portfolio

## Development Guidelines

### Code Organization
- Follow existing package structure strictly
- Models should be pure data objects (minimal logic)
- Business logic belongs in services, not controllers or views
- Views handle only presentation logic

### Team Collaboration
- Branch strategy: `main` → `develop` → `feature/기능명`
- Commit format: `[Name] Description` (e.g., `[박성결] Order 클래스 생성`)
- PRs require at least 1 reviewer before merge

### Common Patterns
- Repository pattern for data access
- Service layer for business logic
- Exception-based error handling
- Console-based input validation

## Current Development Status

**MVP Features (Completed):**
- User registration and login
- Initial balance allocation (10,000,000 KRW)
- Stock list viewing (10-20 major stocks)
- Market order buy/sell
- Real-time price simulation
- Portfolio viewing (total assets, P&L)
- Transaction history

**Planned Features (Weeks 5-8):**
- Limit orders
- Per-stock P&L breakdown
- Trading volume data
- Simple charts
- Friend system
- Real-time notifications

## Important Notes

- Most implementation files are currently empty stubs (classes exist but have no methods)
- Core architecture is defined but many components need implementation
- StockRepository is the most complete implementation (can be used as reference)
- The project is in early development phase - expect many TODO items
- When implementing features, coordinate with team member responsibilities
