package com.example.demo.reply;

import com.example.demo.reply.model.Reply;
import com.example.demo.reply.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyDto.ReplyRes reg(Long userIdx, Long boardIdx, ReplyDto.ReplyReq dto) {
        Reply reply = dto.toEntity(userIdx, boardIdx);
        reply = replyRepository.save(reply);

        return ReplyDto.ReplyRes.from(reply);
    }
}
