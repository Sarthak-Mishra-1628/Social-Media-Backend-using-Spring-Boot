package com.project.project.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;

import com.project.project.Entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{
    public User findByEmail(String email);


    @Query("select u from User u where u.name like %:query% or u.email like %:query% ")
    public List<User> searchUser(@Param("query") String query);
}