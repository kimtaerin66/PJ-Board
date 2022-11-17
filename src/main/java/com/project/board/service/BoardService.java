package com.project.board.service;

import com.project.board.domain.ResponseVO;
import com.project.board.domain.db.BoardDTO;

public interface BoardService {

    ResponseVO getBoardList(BoardDTO.Search searchParam);

    ResponseVO getBoard(int boardSeq);


    ResponseVO  saveBoard(BoardDTO.Request request);

    ResponseVO updateBoard(BoardDTO.Request request);


    ResponseVO deleteBoard(int boardSeq);
}