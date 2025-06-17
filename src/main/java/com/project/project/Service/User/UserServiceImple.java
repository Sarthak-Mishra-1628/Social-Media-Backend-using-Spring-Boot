package com.project.project.Service.User;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.project.Config.JWTProvider;
import com.project.project.Entity.User;
import com.project.project.Exceptions.ResourceNotFoundException;
import com.project.project.Repository.UserRepo;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User CreateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public boolean delete(int id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User GetByID(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User GetByEmail(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User with email " + email + " not found");
        }
        return user;
    }

    @Override
    public User UpdateUser(int id, User user) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPass(user.getPass());

        return userRepo.save(existingUser);
    }

    @Override
    public User FollowUser(Integer id1, Integer id2) {
        if (id1.equals(id2)) throw new RuntimeException("You can't follow yourself");

        User user1 = GetByID(id1);
        User user2 = GetByID(id2);

        if (user2.getFollowers() == null) user2.setFollowers(new HashSet<>());
        if (user1.getFollowings() == null) user1.setFollowings(new HashSet<>());

        if (user1.getFollowings().contains(user2.getId())) {
            throw new RuntimeException("Already following this user");
        }

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepo.save(user1);
        userRepo.save(user2);

        return user1;
    }

    @Override
    public User unfollowUser(Integer id1, Integer id2) {
        User user1 = GetByID(id1);
        User user2 = GetByID(id2);

        user2.getFollowers().remove(user1.getId());
        user1.getFollowings().remove(user2.getId());

        userRepo.save(user1);
        userRepo.save(user2);

        return user1;
    }

    @Override
    public List<User> SearchUsers(String query) {
        return userRepo.searchUser(query);
    }

    @Override
    public User findUserbyJWT(String jwt) {
        String email = JWTProvider.getEmailFromJWTToken(jwt);
        return GetByEmail(email);
    }
}