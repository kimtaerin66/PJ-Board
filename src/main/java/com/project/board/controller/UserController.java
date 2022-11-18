package com.project.board.controller;

import com.project.board.common.BoardErrorCode;
import com.project.board.domain.ResponseVO;
import com.project.board.domain.db.UserDTO;
import com.project.board.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 사용자 컨트롤러
 *
 * @author kimtaerin66
 */

@CrossOrigin
@Api(tags = "[/user] 사용자 API")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService _userService;

    /**
     * 사용자 목록 조회
     *
     * @return
     */
    @ApiOperation(value = "사용자 목록 조회", response = ResponseVO.class)
    @GetMapping("/list")
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
    @GetMapping("/detail")
    @ApiOperation(value = "사용자 상세 조회", response = ResponseVO.class)
    public ResponseEntity<Object> getUser(@ApiParam(value = "userId", required = true)
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
    @ApiOperation(value = "신규 사용자 등록", response = ResponseVO.class)
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

    /**
     * 사용자 정보 수정
     *
     * @param request
     */
    @PutMapping(path = "/update",  produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "사용자 정보 수정", response = ResponseVO.class)
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
    @ApiOperation(value = "사용자 패스워드 리셋", response = ResponseVO.class)
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
    @ApiOperation(value = "사용자 삭제", response = ResponseVO.class)
    @DeleteMapping(path="/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> deleteUser(@ApiParam(value = "사용자 Id", required = true)
                                              @RequestParam(value = "userId", required = true) String userId) {
        ResponseVO res = new ResponseVO();

        try {
            ResponseVO user = _userService.getUserDetail(userId);
            if (user != null) { //해당 사용자가 있으면
            res =  _userService.deleteUser(userId);
            }
        } catch (Exception e) {
            res.setBoradErrorCode(BoardErrorCode.B_6000);
            res.setMessage(e.getMessage());
            res.setExceptionMessage("사용자 삭제 실패");
        }


        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 사용자 로그인
     *
     * */
    @ApiOperation(value = "사용자 로그인", response = ResponseVO.class)
    @PostMapping(path="/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> login(@ApiParam(value = "사용자 로그인 DTO", required = true)
                                             @RequestBody UserDTO.Login request) {
        ResponseVO res = new ResponseVO();

        try {
                res = _userService.loginUser(request);

        } catch (Exception e) {
            res.setBoradErrorCode(BoardErrorCode.B_6000);
            res.setMessage(e.getMessage());
        }

        return new ResponseEntity(res, HttpStatus.OK);
    }

}
