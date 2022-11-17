package com.project.board.controller;

import com.project.board.common.BoardErrorCode;
import com.project.board.domain.db.BoardDTO;
import com.project.board.domain.ResponseVO;
import com.project.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 게시판 컨트롤러
 *
 * @author kimtaerin66
 */

@Api(tags = "게시판 API")
@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private BoardService _boardService;

    /**
     * 게시판 목록 리스트 리턴
     *
     * @return
     */
    @ApiOperation(value = "목록 조회", notes = "게시판 목록을 조회할 수 있습니다.")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBoardList(BoardDTO.Search searchParam) {
        ResponseVO res = null;

        try {
            res = _boardService.getBoardList(searchParam);
        } catch (Exception e) {
            res = new ResponseVO();
            res.setBoradErrorCode(BoardErrorCode.B_6000);
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
    @ApiOperation(value = "게시물 상세 조회", notes = "해당하는 번호의 게시물을 상세하게 조회할 수 있습니다.")
    @GetMapping(value = "/{boardSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")})
    public ResponseEntity<Object> get(@PathVariable int boardSeq) {
        ResponseVO res = new ResponseVO();

        try {
            res = _boardService.getBoard(boardSeq);
        } catch (Exception e) {
            res = new ResponseVO();
            res.setBoradErrorCode(BoardErrorCode.B_6000);
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
    @ApiOperation(value = "신규 게시물 등록", notes = "신규 게시물 등록이 가능합니다.")
    @PostMapping(value = "/save",  produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
            @ApiImplicitParam(name = "title", value = "제목", example = "게시물 제목"),
            @ApiImplicitParam(name = "contents", value = "내용", example = "게시물 내용"),
    })
    public ResponseEntity<Object> saveBoard(BoardDTO.Request request) {
        ResponseVO res = new ResponseVO();

        try {
            res = _boardService.saveBoard(request);
        } catch (DataIntegrityViolationException e) {
            res.setBoradErrorCode(BoardErrorCode.B_1000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("게시물 등록 실패");
        } catch (Exception e) {
            res.setBoradErrorCode(BoardErrorCode.B_6000);
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
    @ApiOperation(value = "게시물 수정", notes = "게시물 수정이 가능합니다.")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
            @ApiImplicitParam(name = "title", value = "제목", example = "게시물 제목"),
            @ApiImplicitParam(name = "contents", value = "내용", example = "게시물 내용"),
    })
    public ResponseEntity<Object> updateBoard(BoardDTO.Request request) {
        ResponseVO res = new ResponseVO();

        try {
            res = _boardService.updateBoard(request);
        } catch (DataIntegrityViolationException e) {
            res.setBoradErrorCode(BoardErrorCode.B_1000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("게시물 수정 실패");
        } catch (Exception e) {
            res.setBoradErrorCode(BoardErrorCode.B_6000);
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
    @ApiOperation(value = "게시물 삭제", notes = "해당하는 번호의 게시물을 삭제합니다.")
    @DeleteMapping(value = "/delete/{boardSeq}",  produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
    })
    public ResponseEntity<Object> delete(@PathVariable int boardSeq) {
        ResponseVO res = new ResponseVO();

        try {
            ResponseVO board = _boardService.getBoard(boardSeq);
            if (board != null) { //해당 게시물이 없으면
                _boardService.deleteBoard(boardSeq);
            }
        } catch (Exception e) {
            res.setBoradErrorCode(BoardErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("게시물 삭제 실패");
        }


        return new ResponseEntity(res, HttpStatus.OK);
    }
}
