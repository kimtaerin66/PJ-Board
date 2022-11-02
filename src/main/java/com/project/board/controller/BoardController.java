package com.project.board.controller;

import com.project.board.common.BoradErrorCode;
import com.project.board.domain.Board;
import com.project.board.domain.ResponseVO;
import com.project.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시판 컨트롤러
 *
 * @author kimtaerin66
 */

@Api(tags = "게시판 API")
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * 게시판 목록 리스트 리턴
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "목록 조회", notes = "게시판 목록을 조회할 수 있습니다.")
    public ResponseEntity<Object> getBoardList() {
        ResponseVO res = null;

        try {
            res = boardService.getBoardList();
        } catch (Exception e) {
            res = new ResponseVO();
            res.setBoradErrorCode(BoradErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("목록 조회 실패");
        }
        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 게시물 상세 정보 리턴
     *
     * @param boardSeq
     * @return
     */
    @GetMapping("/{boardSeq}")
    @ApiOperation(value = "게시물 상세 조회", notes = "해당하는 번호의 게시물을 상세하게 조회할 수 있습니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")})
    public ResponseEntity<Object> get(@PathVariable int boardSeq) {
        ResponseVO res = new ResponseVO();

        try {
            res = boardService.getBoard(boardSeq);
        } catch (Exception e) {
            res = new ResponseVO();
            res.setBoradErrorCode(BoradErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("상세 조회 실패");
        }
        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 게시물 등록
     *
     * @param request
     */
    @PostMapping("/save")
    @ApiOperation(value = "신규 게시물 등록", notes = "신규 게시물 등록이 가능합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
            @ApiImplicitParam(name = "title", value = "제목", example = "게시물 제목"),
            @ApiImplicitParam(name = "contents", value = "내용", example = "게시물 내용"),
    })
    public ResponseEntity<Object> saveBoard(Board.Request request) {
        ResponseVO res = new ResponseVO();

        try {
            res = boardService.saveBoard(request);
        } catch (DataIntegrityViolationException e) {
            res.setBoradErrorCode(BoradErrorCode.B_1000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("게시물 등록 실패");
        } catch (Exception e) {
            res.setBoradErrorCode(BoradErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("게시물 등록 실패");
        }

        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 게시물 수정
     *
     * @param request
     */
    @PutMapping("/update")
    @ApiOperation(value = "게시물 수정", notes = "게시물 수정이 가능합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
            @ApiImplicitParam(name = "title", value = "제목", example = "게시물 제목"),
            @ApiImplicitParam(name = "contents", value = "내용", example = "게시물 내용"),
    })
    public ResponseEntity<Object> updateBoard(Board.Request request) {
        ResponseVO res = new ResponseVO();

        try {
            res = boardService.updateBoard(request);
        } catch (DataIntegrityViolationException e) {
            res.setBoradErrorCode(BoradErrorCode.B_1000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("게시물 수정 실패");
        } catch (Exception e) {
            res.setBoradErrorCode(BoradErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("게시물 등록 실패");
        }


        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 게시물 삭제
     *
     * @param boardSeq
     */
    @DeleteMapping("/delete/{boardSeq}")
    @ApiOperation(value = "게시물 삭제", notes = "해당하는 번호의 게시물을 삭제합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
    })
    public ResponseEntity<Object> delete(@PathVariable int boardSeq) {
        ResponseVO res = new ResponseVO();

        try {
            ResponseVO board = boardService.getBoard(boardSeq);
            if (board != null) { //해당 게시물이 없으면
                boardService.deleteBoard(boardSeq);
            }
        } catch (Exception e) {
            res.setBoradErrorCode(BoradErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("게시물 삭제 실패");
        }


        return new ResponseEntity(res, HttpStatus.OK);
    }
}
