package com.example.demo.board;

import com.example.demo.board.model.Board;
import com.example.demo.board.model.BoardDto;
import com.example.demo.reply.ReplyRepository;
import com.example.demo.user.model.AuthUserDetails;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class BoardServiceTest {
    @Mock // 가짜 객체 생성
    private BoardRepository boardRepository;
    @Mock
    private ReplyRepository replyRepository;

    @InjectMocks // 가짜 객체로 의존성 주입
    private BoardService boardService;

    @Test
    void boardService_게시글등록_성공() {
        // given : 주어져야 하는 데이터를 여기서 지정
        AuthUserDetails user = AuthUserDetails.builder()
                .idx(1L)
                .role("ROLE_USER")
                .username("test01@test.com")
                .name("test01")
                .enable(true)
                .build();
        BoardDto.RegReq dto = new BoardDto.RegReq("제목01", "내용01");

        Board returnEntity = Board.builder()
                .idx(1L)
                .title("제목01")
                .contents("내용01")
                .build();

        given(boardRepository.save(any(Board.class))).willReturn(returnEntity);

        // when : 실제 실행을 테스트할 코드
        BoardDto.RegRes result = boardService.register(user.getIdx(), dto);

        // then : 실행 결과
        assertNotNull(result);
        assertEquals(1L, result.getIdx()); // 성공
//        assertEquals(2L, result.getIdx()); // 실패
        assertEquals("내용01", result.getContents());
    }

    @Test
    void list() {
    }

    @Test
    void read() {
    }
}
