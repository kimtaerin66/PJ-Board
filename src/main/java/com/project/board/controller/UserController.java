package com.project.board.controller;

import com.project.board.common.BoardErrorCode;
import com.project.board.domain.PageDTO;
import com.project.board.domain.ResponseVO;
import com.project.board.domain.db.BoardDTO;
import com.project.board.domain.db.UserDTO;
import com.project.board.service.BoardService;
import com.project.board.service.UserService;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 사용자 컨트롤러
 *
 * @author kimtaerin66
 */

@Api(tags = "[/user] 사용자 API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService _userService;

    /**
     * 사용자 목록 조회
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "사용자 목록 조회", notes = "등록된 사용자 목록을 조회할 수 있습니다.")
    public ResponseEntity<Object> getBoardList() {
        ResponseVO res = new ResponseVO();

        try {
            res = _userService.getUserList();

        } catch (Exception e) {
            res = new ResponseVO();
            res.setBoradErrorCode(BoardErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("목록 조회 실패");
        }
        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 사용자 상세 정보 리턴
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    @ApiOperation(value = "사용자 상세 조회", notes = "해당 아이디의 사용자 정보를 조회합니다.")
    public ResponseEntity<Object> getUser(@ApiParam(value = "사용자 id", required = true)
            @PathVariable @RequestParam String userId) {
        ResponseVO res = new ResponseVO();

        try {
            res = _userService.getUserDetail(userId);
        } catch (Exception e) {
            res = new ResponseVO();
            res.setBoradErrorCode(BoardErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("상세 조회 실패");
        }
        return new ResponseEntity(res, HttpStatus.OK);
    }




    /**
     * 사용자 등록
     *
     * @param request
     */
    @PostMapping(path = "/register",  produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "신규 사용자 등록", notes = "신규 사용자 등록이 가능합니다.")
    public ResponseEntity<Object> saveBoard(@ApiParam(value = "사용자 등록 DTO", required = true)
            @RequestBody UserDTO.Request request)throws Exception  {
        ResponseVO res = new ResponseVO();

        try {
            res = _userService.registerUser(request);
        } catch (DataIntegrityViolationException e) {
            res.setBoradErrorCode(BoardErrorCode.B_1000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("사용자 등록 실패");
        } catch (Exception e) {
            res.setBoradErrorCode(BoardErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("사용자 등록 실패");
        }

        return new ResponseEntity(res, HttpStatus.OK);
    }
//    /**
//     * 게시물 여러개 등록
//     *
//     * @param param
//     */
//    @PostMapping("/saveGroup")
//    @ApiOperation(value = "대량 게시물 등록", notes = "대량 게시물 등록이 가능합니다.")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "boardType", value = "게시물 종류", example = "NOTICE/FAQ/INQUIRY"),
//            @ApiImplicitParam(name = "title", value = "제목", example = "게시물 제목"),
//            @ApiImplicitParam(name = "contents", value = "내용", example = "게시물 내용"),
//    })
//    public ResponseEntity<Object> saveBoardGroup(List<BoardDTO.Request> param) {
//        ResponseVO res = new ResponseVO();
//
//        try {
//            res = boardService.saveBoardGroup(param);
//        } catch (DataIntegrityViolationException e) {
//            res.setBoradErrorCode(BoardErrorCode.B_1000);
//            res.setMessage(e.getMessage());
//            res.setExceptionMessage("게시물 등록 실패");
//        } catch (Exception e) {
//            res.setBoradErrorCode(BoardErrorCode.B_6000);
//            res.setMessage(e.getMessage());
//            res.setExceptionMessage("게시물 등록 실패");
//        }
//
//        return new ResponseEntity(res, HttpStatus.OK);
//    }

    /**
     * 사용자 정보 수정
     *
     * @param request
     */
    @PutMapping(path = "/update",  produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "사용자 정보 수정", notes = "사용자 정보 수정이 가능합니다.")
    public ResponseEntity<Object> updateUser(@ApiParam(value = "사용자 DTO", required = true)
            @RequestBody UserDTO.Update request) throws Exception {
        ResponseVO res = new ResponseVO();

        try {
            res = _userService.updateUser(request);
        } catch (DataIntegrityViolationException e) {
            res.setBoradErrorCode(BoardErrorCode.B_1000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("사용자 수정 실패");
        } catch (Exception e) {
            res.setBoradErrorCode(BoardErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("사용자 등록 실패");
        }


        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 비밀번호 초기화
     * 0000
     * @param userId
     */
    @PutMapping(path = "/pwReset",  produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "사용자 정보 수정", notes = "사용자 정보 수정이 가능합니다.")
    public ResponseEntity<Object> pwReset(@ApiParam(value = "사용자 Id", required = true)
                                             @RequestParam String userId) throws Exception {
        ResponseVO res = new ResponseVO();

        try {
            _userService.pwReset(userId);
        } catch (DataIntegrityViolationException e) {
            res.setBoradErrorCode(BoardErrorCode.B_1000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("비밀번호 초기화 실패");
        } catch (Exception e) {
            res.setBoradErrorCode(BoardErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("비밀번호 초기화 실패");
        }


        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 사용자 삭제
     *
     * @param userId
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "사용자 삭제", notes = "해당하는 아이디의 사용자를 삭제합니다.")
    public ResponseEntity<Object> deleteUser(@ApiParam(value = "사용자 Id", required = true)
                                              @RequestParam(value = "userId", required = true) String userId) {
        ResponseVO res = new ResponseVO();

        try {
            ResponseVO user = _userService.getUserDetail(userId);
            if (user != null) { //해당 사용자가 있으면
                _userService.deleteUser(userId);
            }
        } catch (Exception e) {
            res.setBoradErrorCode(BoardErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("사용자 삭제 실패");
        }


        return new ResponseEntity(res, HttpStatus.OK);
    }
}
