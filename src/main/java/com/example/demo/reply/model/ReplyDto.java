package com.example.demo.reply.model;

import com.example.demo.board.model.Board;
import com.example.demo.user.model.User;
import lombok.Builder;
import lombok.Getter;


public class ReplyDto {

    @Getter
    public static class ReplyReq {
        private String contents;

        public Reply toEntity(Long userIdx, Long boardIdx) {
            return Reply.builder()
                    .contents(this.contents)
                    .user(User.builder()
                            .idx(userIdx)
                            .build())
                    .board(Board.builder()
                            .idx(boardIdx)
                            .build())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ReplyRes {
        private Long idx;
        private String contents;
        private String writer;

        public static ReplyDto.ReplyRes from(Reply entity) {
            return ReplyRes.builder()
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .writer(entity.getUser().getName())
                    .build();
        }
    }
}