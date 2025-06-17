package com.project.project.Repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.project.Entity.Message;

public interface MessageRepo extends JpaRepository<Message , Integer>{

    public List<Message> findByChatId(Integer id);
    
}
