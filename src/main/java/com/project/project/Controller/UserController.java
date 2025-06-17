package com.project.project.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.project.Entity.User;
import com.project.project.Service.User.UserService;

import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/premium/Users")
    public List<User> getAll(){
        return userService.getAll();
    }

    @DeleteMapping("User/delete/{id}")
    public String delete(@PathVariable int id){
        if(userService.delete(id)) return "Delete Successful";
        return "Not";
    }

    @GetMapping("User/id/{id}")
    public User GetByID(@PathVariable int id){
        return userService.GetByID(id);
    }


    @GetMapping("User/email/{email}")
    public User GetByEmail(@PathVariable String email){
        return userService.GetByEmail(email);
    }


    @PutMapping("premium/User/update")
    public User update(@RequestHeader("Authorization") String jwt , @RequestBody User user){

        User user1=userService.findUserbyJWT(jwt);

        return userService.UpdateUser(user1.getId() , user);
    }



    @PutMapping("premium/User/follower/following/{id2}")
    public User Follow(@RequestHeader("Authorization") String jwt , @PathVariable int id2){

        User user1=userService.findUserbyJWT(jwt);
        User user2=userService.GetByID(id2);

        System.out.println("User1: " + user1);
        System.out.println("User2: " + user2);
        System.out.println("Followers Before: " + user2.getFollowers());
        System.out.println("Followings Before: " + user1.getFollowings());

        return userService.FollowUser(user1.getId() , id2);
    }


    @PutMapping("premium/User/unfollow/{id2}")
    public User unfollow(@RequestHeader("Authorization") String jwt, @PathVariable int id2) {
        User user1 = userService.findUserbyJWT(jwt);
        return userService.unfollowUser(user1.getId(), id2);
    }


    @GetMapping("Users/search/{query}")
    public List<User> searchUsers(@PathVariable String query){
        return userService.SearchUsers(query);
    }


    @GetMapping("/premium/User/token")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        return userService.findUserbyJWT(jwt);
    }
}