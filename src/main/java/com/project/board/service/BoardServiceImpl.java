package com.project.board.service;

import com.project.board.common.BoardErrorCode;
import com.project.board.domain.ResponseVO;
import com.project.board.domain.db.BoardDTO;
import com.project.board.mappers.BoardMapper;
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
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper _boarderMapper;

    /**
     * 게시판 목록 리스트 리턴
     *
     * @return
     */
    @Override
    public ResponseVO getBoardList(BoardDTO.Search searchParam) {
        ResponseVO responseVO = new ResponseVO();

        List<BoardDTO.Response> bList = _boarderMapper.getBoardDBList(searchParam);

        if (bList.size() > 0) {
            responseVO.setResult(bList);
            responseVO.setBoradErrorCode(BoardErrorCode.B_200);
            return responseVO;
        }
        responseVO.setBoradErrorCode(BoardErrorCode.B_1001);

        return responseVO;
    }

    /**
     * 게시물 상세 정보 리턴
     *
     * @param boardSeq
     * @return
     */
    @Override
    public ResponseVO getBoard(int boardSeq) {
        ResponseVO responseVO = new ResponseVO();

        BoardDTO.Response result = _boarderMapper.getDBBoard(boardSeq);
        if (result != null) {
            responseVO.setResult(result);
            responseVO.setBoradErrorCode(BoardErrorCode.B_200);
            return responseVO;
        }
        responseVO.setBoradErrorCode(BoardErrorCode.B_3000);

        return responseVO;
    }

    /**
     * 게시물 등록
     *
     * @param request
     */
    @Override
    public ResponseVO saveBoard(BoardDTO.Request request) {
        ResponseVO responseVO = new ResponseVO();

        if (request != null) {
            _boarderMapper.saveDBBoard(request);
            responseVO.setBoradErrorCode(BoardErrorCode.B_200);
            return responseVO;
        }
        responseVO.setBoradErrorCode(BoardErrorCode.B_3000);

        return responseVO;
    }

    /**
     * 게시물 수정
     *
     * @param request
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseVO updateBoard(BoardDTO.Request request) {
        ResponseVO responseVO = new ResponseVO();

        BoardDTO.Response result = _boarderMapper.getDBBoard(request.getBoardSeq());

        if (result != null) {
            _boarderMapper.updateDBBoard(request);
            responseVO.setBoradErrorCode(BoardErrorCode.B_200);
            return responseVO;
        }
        responseVO.setCode(400);
        responseVO.setBoradErrorCode(BoardErrorCode.B_1001);

        return responseVO;
    }

    /**
     * 게시물 삭제
     *
     * @param boardSeq
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseVO deleteBoard(int boardSeq) {
        ResponseVO responseVO = new ResponseVO();

        _boarderMapper.deleteDBBoard(boardSeq);
        responseVO.setBoradErrorCode(BoardErrorCode.B_200);

        return responseVO;
    }
}
