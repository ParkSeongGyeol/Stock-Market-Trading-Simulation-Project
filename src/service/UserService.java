package service;

import java.util.Date;

import model.User;
import repository.UserRepository;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    private static User loggedInUser = null;


    public boolean register(String userId, String password, String userName) {

        if (userRepository.findUser(userId) != null) {
            System.out.println("회원가입 실패: 이미 존재하는 ID입니다.");
            return false;
        }

        User newUser = new User();
        userRepository.addUser(newUser);
        System.out.println("회원가입 성공: " + userName + "님 환영합니다.");
        return true;
    }


    public boolean login(String userId, String password) {
   
        if (loggedInUser != null) {
            System.out.println("로그인 실패: 이미 " + loggedInUser.getUserName() + "님이 로그인 중입니다.");
            return false;
        }

    
        User user = userRepository.findUser(userId);

  
        if (user == null) {
            System.out.println("로그인 실패: 존재하지 않는 ID입니다.");
            return false;
        }

  
        if (user.getPassword().equals(password)) {

            loggedInUser = user;
            System.out.println("로그인 성공: " + user.getUserName() + "님 환영합니다!");
            return true;
        } else {
    
            System.out.println("로그인 실패: 비밀번호가 일치하지 않습니다.");
            return false;
        }
    }

    public void logout() {
        if (loggedInUser != null) {
            System.out.println(loggedInUser.getUserName() + "님이 로그아웃하셨습니다.");
            loggedInUser = null; 
        } else {
            System.out.println("로그인 상태가 아닙니다.");
        }
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
}