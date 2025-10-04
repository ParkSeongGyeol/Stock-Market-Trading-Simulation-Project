package Login;

import java.time.LocalDate;

public class User {

    
    private String userId;

    
    private String password;

    
    private String userName;

    
    private LocalDate registeredDate;

   
    public User() {
    }

    
    public User(String userId, String password, String userName, LocalDate registeredDate) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.registeredDate = registeredDate;
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

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    
    
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", registeredDate=" + registeredDate +
                '}';
    }
}