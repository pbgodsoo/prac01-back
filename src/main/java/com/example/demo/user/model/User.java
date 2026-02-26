package com.example.demo.user.model;

import com.example.demo.board.model.Board;
import com.example.demo.likes.model.Likes;
import com.example.demo.reply.model.Reply;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String email;
    private String name;
    @Setter
    private String password;
    @Setter
    private boolean enable;
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boardList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reply> replyList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Likes> likesList;
}
