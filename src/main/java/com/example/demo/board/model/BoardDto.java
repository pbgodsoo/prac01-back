package com.example.demo.board.model;

import com.example.demo.reply.model.ReplyDto;
import com.example.demo.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

public class BoardDto {
    @Getter
    public static class RegReq {
        @Schema(description = "제목, 제목은 50글자까지만 가능합니다", required = true, example = "제목01")
        private String title;
        private String contents;

        public Board toEntity(Long userIdx) {
            return Board.builder()
                    .title(this.title)
                    .contents(this.contents)
                    .user(User.builder()
                            .idx(userIdx)
                            .build())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class RegRes {
        private Long idx;
        private String title;
        private String contents;

        public static RegRes from(Board entity) {
            return RegRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ListRes {
        private Long idx;
        private String title;
        private String writer;
        private int replyCount;
        private int likesCount;

        public static ListRes from(Board entity) {
            return ListRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .writer(entity.getUser().getName())
                    .replyCount(entity.getReplyList().size())
                    .likesCount(entity.getLikesList().size())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ReadRes {
        private Long idx;
        private String title;
        private String contents;
        private String writer;
        private List<ReplyDto.ReplyRes> replyList;
        private int likesCount;

        public static ReadRes from(Board entity) {
            return ReadRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .writer(entity.getUser().getName())
                    .replyList(entity.getReplyList().stream().map(ReplyDto.ReplyRes::from).toList())
                    .likesCount(entity.getLikesList().size())
                    .build();
        }
    }
}
