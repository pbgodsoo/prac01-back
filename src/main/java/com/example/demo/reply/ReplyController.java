package com.example.demo.reply;

import com.example.demo.common.model.BaseResponse;
import com.example.demo.reply.model.ReplyDto;
import com.example.demo.user.model.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reply")
@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/reg/{boardIdx}")
    public ResponseEntity reg(
            @AuthenticationPrincipal AuthUserDetails user,
            @PathVariable Long boardIdx,
            @RequestBody ReplyDto.ReplyReq dto
    ) {
        Long userIdx = user.getIdx();
        ReplyDto.ReplyRes result = replyService.reg(userIdx, boardIdx, dto);
        return ResponseEntity.ok(
                BaseResponse.success(result)
        );
    }
}