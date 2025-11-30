package model;

import java.time.LocalDateTime;

public class User {

    private String userId;
    private String password;
    private String userName;
    private LocalDateTime registeredDate;
    private long balance;

    public User() {
    }

    public User(String userId, String password, String userName) {
        this(userId, password, userName, LocalDateTime.now());
    }

    public User(String userId, String password, String userName, LocalDateTime registeredDate) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.registeredDate = registeredDate;
        this.balance = 10_000_000L; // Default balance
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("User(userId = '%s', userName = '%s', registeredDate = %s, balance=%,d원}",
                userId, userName, (registeredDate != null ? registeredDate.toLocalDate() : "N/A"), balance);
    }
}