package com.project.project.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.project.Entity.Chat;
import com.project.project.Entity.User;

public interface ChatRepo extends JpaRepository<Chat,Integer> {
    public List<Chat> findByUsersId(Integer userId);

    @Query("select c from Chat c where :user1 member of c.users and :user2 member of c.users")
    public Chat findChatbyUsersId(@Param("user1") User user1 , @Param("user2") User user2);
}