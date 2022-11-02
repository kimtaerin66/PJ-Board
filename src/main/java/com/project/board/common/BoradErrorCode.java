package com.project.board.common;

import lombok.Getter;

/**
 * 에러 코드 정리
 *
 * @author kimtaerin66
 */
@Getter
public enum BoradErrorCode {

    B_200(200, "Success"),

    /**
     * DB 관련 에러 1000 ~ 1999
     */
    B_1000(1000, "DB Error"),
    B_1001(1001, "DB No Data"),
    B_1002(1002, "DB DuplicateKey"),
    B_1003(1003, "DB Update Error"),
    B_1004(1004, "DB Insert Error"),
    B_1005(1005, "DB Delete Error"),


    /**
     * 서버 관련 에러 2000 ~ 2999
     */
    B_2000(2000, "Internel Exception Error"),
    B_2001(2001, "Spring Bind Error"),
    B_2002(2002, "Server Not Found"),


    /**
     * param, json 관련 에러 3000 ~ 3999
     */
    B_3000(3000, "Param Mapping Error"),
    B_3001(3001, "Json Parse Error"),

    /**
     * 기타 8000 ~ 8999
     */
    B_6000(6000, "Unknown error"),
    /**
     * 기타 8000 ~ 8999
     */
    B_8000(8000, "Custom Error");

    final int _code;
    final String _message;

    BoradErrorCode(int code, String message) {
        this._code = code;
        this._message = message;

    }
    }
