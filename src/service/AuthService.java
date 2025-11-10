package service;

import java.util.Random;

import model.User;

public class AuthService {

    private static User loggedInUser = null;
    private String currentAuthCode = null;
    private final Random random = new Random();


    public String getTestOnlyAuthCode() {
        return this.currentAuthCode;
    }


   
    public String requestAuthCode() {
        int code = random.nextInt(900000) + 100000;
        this.currentAuthCode = String.valueOf(code);
       
        System.out.println("[시스템] 인증번호가 발급되었습니다.");
        return this.currentAuthCode;
    }

    public boolean verifyAuthCode(String inputCode) {
        if (currentAuthCode == null) {
            System.out.println("오류: 먼저 인증번호를 요청해야 합니다.");
            return false;
        }
        boolean isSuccess = currentAuthCode.equals(inputCode);
        this.currentAuthCode = null;

        if (isSuccess) {
            System.out.println("성공: 본인 인증이 완료되었습니다.");
            return true;
        } else {
            System.out.println("오류: 인증번호가 일치하지 않습니다.");
            return false;
        }
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public void setLoggedInUser(User user) {
        loggedInUser = user;
    }
   
    public void resetSession() {
        loggedInUser = null;
    }

    public User getCurrentUser() {
        return loggedInUser;
    }

    public boolean checkAccess() {
        if (!isLoggedIn()) {
            System.out.println("미로그인 처리: 로그인이 필요한 기능입니다.");
            return false;
        }
        return true;
    }
}