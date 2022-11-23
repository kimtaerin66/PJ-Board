package com.project.board.domain.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;


public class UserDTO {

    @Data
    @ApiModel(value = "Response", description = "사용자 DTO(Res)")
    public static class Response {
        @ApiModelProperty(notes = "유저 PK", required = false, hidden = false)
        private int userPk;
        @ApiModelProperty(notes = "아이디", required = true, example = "test1234")
        @NotBlank(message = "userId 는 필수 입력 항목입니다.")
        private String userId;
        @ApiModelProperty(notes = "패스워드", required = false, example = "0")
        @NotBlank(message = "password 는 필수 입력 항목입니다.")
        private String userPw;
        @ApiModelProperty(notes = "이름", required = true, example = "김태린")
        @NotBlank(message = "userName 이름은 필수 입력 항목입니다.")
        private String userName;
        @ApiModelProperty(notes = "이메일", required = false)
        private String userEmail;
        @ApiModelProperty(notes = "핸드폰 번호", required = false)
        private String userPhone;
        @ApiModelProperty(value = "등록 날짜", example = "yyyy-MM-ddThh:mm:ss")
        private Date userRegDate;

    }


    @Data
    @ApiModel(value = "Request", description = "사용자 DTO(Req)")
    public static class Request {
        @ApiModelProperty(notes = "아이디", required = true, example = "test1234")
        @NotBlank(message = "userId 는 필수 입력 항목입니다.")
        private String userId;
        @ApiModelProperty(notes = "패스워드", required = false, example = "0")
        @NotBlank(message = "password 는 필수 입력 항목입니다.")
        private String userPw;
        @ApiModelProperty(notes = "이름", required = true, example = "김태린")
        @NotBlank(message = "userName 이름은 필수 입력 항목입니다.")
        private String userName;
        @ApiModelProperty(notes = "이메일", required = false)
        private String userEmail;
        @ApiModelProperty(notes = "핸드폰 번호", required = false)
        private String userPhone;

    }

    @Data
    public static class Update {
        @ApiModelProperty(notes = "아이디", required = true, example = "test1234")
        @NotBlank(message = "userId 는 필수 입력 항목입니다.")
        private String userId;
        @ApiModelProperty(notes = "패스워드", required = false, example = "0")
        @NotBlank(message = "password 는 필수 입력 항목입니다.")
        private String userPw;
        @ApiModelProperty(notes = "이름", required = true, example = "김태린")
        @NotBlank(message = "userName 이름은 필수 입력 항목입니다.")
        private String userName;
        @ApiModelProperty(notes = "이메일", required = false)
        private String userEmail;
        @ApiModelProperty(notes = "핸드폰 번호", required = false)
        private String userPhone;

    }


    @Data
    public static class Search {
        @ApiModelProperty(notes = "검색명")
        private String keyword;
        @ApiModelProperty(notes = "보드 타입")
        private String boardTypes;
    }

    @Data
    public static class Login {
        @ApiModelProperty(notes = "아이디", required = true, example = "test1234")
        @NotBlank(message = "userId 는 필수 입력 항목입니다.")
        private String userId;

        @ApiModelProperty(notes = "패스워드", required = false, example = "0")
        @NotBlank(message = "password 는 필수 입력 항목입니다.")
        private String userPw;

    }

    @Data
    public static class TblResponse {
        @ApiModelProperty(notes = "Page No")
        int pageNo;
        @ApiModelProperty(notes = "Page Size")
        int pageSize;
//        @ApiModelProperty(notes = "Page Total Size")
//        int pageTotalSize;
        @ApiModelProperty(notes = "Result List")
        List<UserDTO.Response> list;

    }

}
