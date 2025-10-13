package Login;

import java.time.LocalDateTime;

public class User {

     String userId;
     String password;
     String userName;
    LocalDateTime registeredDate;
    long balance;

   
    public User() {
    }

    
    public User(String userId, String password, String userName, LocalDateTime registeredDate) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.registeredDate = LocalDateTime.now();
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

    
    
    public String toString() {
        return String.format("User(userId = '%s',"
        		+ "userName = '%s',"
        		+ "registeredDate = %s,"
        		+ "balance=%,d원}",
        		userId , userName, registeredDate.toLocalDate(), balance);     		
    }
}
