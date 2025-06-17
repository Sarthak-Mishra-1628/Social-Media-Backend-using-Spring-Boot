package com.project.project.Service.User;
import java.util.*;
import com.project.project.Entity.User;

public interface UserService {
    List<User> getAll();
    boolean delete(int id);
    
    User CreateUser(User user);
    User GetByID(int id);
    User GetByEmail(String email);
    User UpdateUser(int id, User user);

    User FollowUser(Integer id1 , Integer id2);
    User unfollowUser(Integer id1, Integer id2);
    
    public List<User> SearchUsers(String query);

    public User findUserbyJWT(String jwt);    
}
