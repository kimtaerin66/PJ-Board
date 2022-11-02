package com.project.board.service;

import com.project.board.common.BoradErrorCode;
import com.project.board.domain.ResponseVO;
import com.project.board.repository.BoardRepository;
import com.project.board.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 게시판 서비스
 *
 * @author kimtaerin66
 */

@Service
public class BoardService {

    @Autowired
    private BoardRepository repository;

    /**
     * 게시판 목록 리스트 리턴
     *
     * @return
     */
    public ResponseVO getBoardList() {
        ResponseVO responseVO = new ResponseVO();

        List<Board.Response> bList = repository.getBoardDBList();

        if (bList.size() > 0) {
            responseVO.setResult(bList);
            responseVO.setBoradErrorCode(BoradErrorCode.B_200);
            return responseVO;
        }
        responseVO.setBoradErrorCode(BoradErrorCode.B_1001);

        return responseVO;
    }

    /**
     * 게시물 상세 정보 리턴
     *
     * @param boardSeq
     * @return
     */
    public ResponseVO getBoard(int boardSeq) {
        ResponseVO responseVO = new ResponseVO();

        Board.Response result = repository.getDBBoard(boardSeq);
        if (result != null) {
            responseVO.setResult(result);
            responseVO.setBoradErrorCode(BoradErrorCode.B_200);
            return responseVO;
        }
        responseVO.setBoradErrorCode(BoradErrorCode.B_3000);

        return responseVO;
    }

    /**
     * 게시물 등록
     *
     * @param request
     */
    public ResponseVO saveBoard(Board.Request request) {
        ResponseVO responseVO = new ResponseVO();

        if (request != null) {
            repository.saveDBBoard(request);
            responseVO.setBoradErrorCode(BoradErrorCode.B_200);
            return responseVO;
        }
        responseVO.setBoradErrorCode(BoradErrorCode.B_3000);

        return responseVO;
    }

    /**
     * 게시물 수정
     *
     * @param request
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseVO updateBoard(Board.Request request) {
        ResponseVO responseVO = new ResponseVO();

        Board.Response result = repository.getDBBoard(request.getBoardSeq());

        if (result != null) {
            repository.updateDBBoard(request);
            responseVO.setBoradErrorCode(BoradErrorCode.B_200);
            return responseVO;
        }
        responseVO.setCode(400);
        responseVO.setBoradErrorCode(BoradErrorCode.B_1001);

        return responseVO;
    }

    /**
     * 게시물 삭제
     *
     * @param boardSeq
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseVO deleteBoard(int boardSeq) {
        ResponseVO responseVO = new ResponseVO();

        repository.deleteDBBoard(boardSeq);
        responseVO.setBoradErrorCode(BoradErrorCode.B_200);

        return responseVO;
    }
}
