package com.project.project.Entity;
import java.time.*;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    private String chat_name , chat_img;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "chat")
    private List<Message> messages= new ArrayList<>();

    private LocalDateTime timeStamp;
}