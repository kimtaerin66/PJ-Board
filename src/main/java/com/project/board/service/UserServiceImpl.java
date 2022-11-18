package com.project.board.service;

import com.project.board.common.BoardErrorCode;
import com.project.board.domain.ResponseVO;
import com.project.board.domain.db.UserDTO;
import com.project.board.mappers.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 사용자 서비스
 *
 * @author kimtaerin66
 */

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper _userMapper;


    /**
     * 사용자 목록 리스트 리턴
     *
     * @return
     */
    @Override
    public ResponseVO getUserList() {
        ResponseVO responseVO = new ResponseVO();

        List<UserDTO.Response> uList = _userMapper.getDBUserList();

        //uList 체크
        if (uList.size() > 0) {
//            BoardDTO.TblResponse tblResponse = new BoardDTO.TblResponse();
//            tblResponse.setList(bList);
//            tblResponse.setPageNo(nPageNo);
//            tblResponse.setPageSize(bList.size());

            //result넣기
            responseVO.setResult(uList);
            responseVO.setBoradErrorCode(BoardErrorCode.B_200);
            return responseVO;
        }
        responseVO.setBoradErrorCode(BoardErrorCode.B_1001);

        return responseVO;
    }

    /**
     * 사용자 상세 정보 리턴
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseVO getUserDetail(String userId) {
        ResponseVO responseVO = new ResponseVO();

        UserDTO.Response result = _userMapper.getDBUserDetail(userId);
        if (result != null) {
            responseVO.setResult(result);
            responseVO.setBoradErrorCode(BoardErrorCode.B_200);
            return responseVO;
        }
        responseVO.setBoradErrorCode(BoardErrorCode.B_3000);

        return responseVO;
    }

    /**
     * 사용자 등록
     *
     * @param request
     */
    @Override
    public ResponseVO registerUser(UserDTO.Request request) {
        ResponseVO responseVO = new ResponseVO();

        if (request != null) {
            _userMapper.setDBUserAdd(request);
            responseVO.setBoradErrorCode(BoardErrorCode.B_200);
            return responseVO;
        }
        responseVO.setBoradErrorCode(BoardErrorCode.B_3000);

        return responseVO;
    }


    /**
     * 사용자 정보 수정
     *
     * @param request
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseVO updateUser(UserDTO.Update request) {
        ResponseVO responseVO = new ResponseVO();

        //userId를 받아서, 입력받은 정보가 존재하는지 확인.
        UserDTO.Response result = _userMapper.getDBUserDetail(request.getUserId());

        if (result != null) {
            _userMapper.setDBUserUpdate(request);
            responseVO.setBoradErrorCode(BoardErrorCode.B_200);
            return responseVO;
        }
        responseVO.setCode(400);
        responseVO.setBoradErrorCode(BoardErrorCode.B_1001);

        return responseVO;
    }

    /**
     * 사용자 삭제
     *
     * @param userId
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseVO deleteUser(String userId) {
        ResponseVO responseVO = new ResponseVO();

        _userMapper.setDBUserDelete(userId);
        responseVO.setBoradErrorCode(BoardErrorCode.B_200);

        return responseVO;
    }

    /**
     * 아이디 중복 확인
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseVO duplicateId(String userId) {
        ResponseVO responseVO = new ResponseVO();

        UserDTO.Response result = _userMapper.getDBUserDuplicate(userId);

        if (result == null) { //아이디 중복이 아니면
            responseVO.setBoradErrorCode(BoardErrorCode.B_200);
            return responseVO;
        }
        responseVO.setBoradErrorCode(BoardErrorCode.B_3000);

        return responseVO;
    }

    /**
     * 비밀번호 초기화
     *
     * @param userId
     * @return
     */
    public ResponseVO pwReset(String userId) {
        ResponseVO responseVO = new ResponseVO();

        UserDTO.Response result = _userMapper.getDBUserDetail(userId);

        if (result != null) { //아이디가 존재하면
            _userMapper.setDBUserPwReset(userId);
            responseVO.setBoradErrorCode(BoardErrorCode.B_200);
            return responseVO;
        }
        responseVO.setBoradErrorCode(BoardErrorCode.B_3000);

        return responseVO;
    }

    /**
     * 사용자 로그인
     *
     * @param request
     * @return
     */
    @Override
    public ResponseVO loginUser(UserDTO.Login request) {
        ResponseVO responseVO = new ResponseVO();

        UserDTO.Response user = _userMapper.getDBUserDetail(request.getUserId());

        if (user != null) {
            UserDTO.Response loginUser = _userMapper.getDBUserLogin(request);
            if (loginUser != null) {
                responseVO.setResult(loginUser);
                responseVO.setExceptionMessage("로그인 성공");
                responseVO.setBoradErrorCode(BoardErrorCode.B_200);
                return responseVO;
            } else {
                responseVO.setExceptionMessage("비밀번호가 일치하지 않습니다.");
                responseVO.setBoradErrorCode(BoardErrorCode.B_1001);
                return responseVO;
            }
        }
        responseVO.setExceptionMessage("존재하지 않는 아이디입니다.");
        responseVO.setBoradErrorCode(BoardErrorCode.B_1001);


        return responseVO;
    }

}
