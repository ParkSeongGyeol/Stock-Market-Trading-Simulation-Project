package Login;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    
    private final Map<String, User> userStore = new HashMap<>();

    
    public void addUser(User user) {
        userStore.put(user.getUserId(), user);
        System.out.println(user.getUserName() + "님의 정보가 추가되었습니다.");
    }

    
    public User findUser(String userId) {
        return userStore.get(userId);
    }
 }

