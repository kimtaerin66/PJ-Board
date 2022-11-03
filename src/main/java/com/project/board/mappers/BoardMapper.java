package com.project.board.mappers;


import com.project.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 게시판 Repository
 * @author kimtaerin66
 *
 * */
@Mapper
public interface BoardMapper {


    //게시판 전체 조회
    List<Board.Response> getBoardDBList(Board.Search searchParam);

    //게시물 하나(상세) 조회
    Board.Response getDBBoard(int boardSeq);


    //게시물 추가
    void saveDBBoard(Board.Request board);

    //게시물 여러개 추가
    int saveDBGroups(List<Board.Request> param);


    //게시물 수정
    void updateDBBoard(Board.Request board);

    //게시물 삭제
    void deleteDBBoard(int boardSeq);
}
