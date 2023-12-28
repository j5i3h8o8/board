package com.example.demo.service;

import com.example.demo.model.BoardStatus;
import com.example.demo.model.entity.Board;
import com.example.demo.model.response.BoardListResponse;
import com.example.demo.model.response.BoardResponse;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.BoardRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardRepositoryCustom boardRepositoryCustom;

    public List<BoardListResponse> searchBoardList(int page, int pageSize, Sort.Direction direction) {
        return boardRepository.findAllByBoardStatus(BoardStatus.ACTIVE, PageRequest.of(page, pageSize, Sort.by(direction, "boardNo"))).map(BoardListResponse::from).toList();
    }

    public List<BoardListResponse> searchBoardList(Long boardNo) {
        return (boardNo == 0L ? boardRepository.findTop10ByBoardStatusOrderByBoardNoDesc(BoardStatus.ACTIVE) : boardRepository.findTop10ByBoardStatusAndBoardNoLessThanOrderByBoardNoDesc(BoardStatus.ACTIVE, boardNo)).stream().map(BoardListResponse::from).collect(Collectors.toList());
    }

    public BoardResponse getBoard(Long boardNo) {
        return boardRepositoryCustom.find(boardNo).map(BoardResponse::from).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));

//    Board board = boardRepositoryCustom.find(boardNo).orElseGet(null);
//    for (Comment comment: board.getComments()) {
//      comment.getCommentNo();
//    }
    }

    public BoardResponse writeBoard(String title, String body) {
        Board board = new Board();
        board.setTitle(title);
        board.setBody(body);
        board.setBoardStatus(BoardStatus.ACTIVE);
        return BoardResponse.from(boardRepository.save(board));
    }

    public BoardResponse editBoard(Long boardNo, String body) {
        return boardRepository.findById(boardNo).filter(Board::isActive).map(board -> {
            board.setBody(body);
            return board;
        }).map(BoardResponse::from).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }

    public BoardResponse deleteBoard(Long boardNo) {
        return boardRepository.findById(boardNo).filter(Board::isActive).map(board -> {
            board.doClose();
            return board;
        }).map(BoardResponse::from).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }
}
