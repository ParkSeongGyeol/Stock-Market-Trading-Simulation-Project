package service;

import java.util.Date;
import java.time.LocalDateTime;

import model.User;
import repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;
    private final AuthService authService; 


    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }


    public User register(String userId, String password, String userName) {
  
        if (userRepository.findUser(userId) != null) {
            System.out.println("오류: 아이디 '" + userId + "'는 이미 존재합니다.");
            return null;
        }

   
        User newUser = new User(userId, password, userName);
        newUser.setBalance(10_000_000L); // 초기 자금 1000만원
        userRepository.addUser(newUser);

        System.out.println("'" + userName + "'님, 회원가입이 완료되었습니다.");


        String authCode = authService.requestAuthCode();
        System.out.println("[시스템] 회원가입을 축하합니다. 본인 확인용 인증번호가 발급되었습니다.");
       

        System.out.println("발급된 인증번호: " + authCode);

        return newUser;
    }


    public boolean login(String userId, String password) {
        User user = userRepository.findUser(userId);
        if (user != null && user.getPassword().equals(password)) {
            authService.setLoggedInUser(user);
            System.out.println(user.getUserName() + "님, 로그인되었습니다.");
            return true;
        }
        System.out.println("오류: 아이디 또는 비밀번호가 일치하지 않습니다.");
        return false;
    }
   
    public void logout() {
        User currentUser = authService.getCurrentUser();
        if (currentUser != null) {
            System.out.println(currentUser.getUserName() + "님, 로그아웃되었습니다.");
        }
        authService.resetSession();
        System.out.println("메인 메뉴로 이동합니다.");
    }
}