package com.project.board.mappers;


import com.project.board.domain.PageDTO;
import com.project.board.domain.db.BoardDTO;
import com.project.board.domain.db.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 사용자 Mapper
 * @author kimtaerin66
 *
 * */
@Mapper
public interface UserMapper {

    //사용자 상세 조회
    UserDTO.Response                getDBUserDetail(String userId);
    //사용자 목록 조회
    List<UserDTO.Response>          getDBUserList();
    //아이디 중복 체크
    UserDTO.Response                getDBUserDuplicate(String userId);
    //사용자 등록
    void                            setDBUserAdd(UserDTO.Request request);
    //사용자 수정
    void                            setDBUserUpdate(UserDTO.Update request);
    //사용자 삭제
    void                            setDBUserDelete(String userId);
    //비밀번호 초기화
    void                            setDBUserPwReset(String userId);


}
