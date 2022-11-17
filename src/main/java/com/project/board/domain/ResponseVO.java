package com.project.board.domain;

import com.project.board.common.BoardErrorCode;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *  공통 response
 *
 * @author kimtaerin66
 * */

@Data
public class ResponseVO {
    private Integer code;

    private String message;

    private String exceptionMessage;

    private LocalDateTime serverTime;

    private Object result;

    public ResponseVO(){ //기본값 설정
        this.code = 200;
        this.message = "";
        this.exceptionMessage = "";
        this.serverTime = LocalDateTime.now();
    }

    //에러처리
    public void setBoradErrorCode(BoardErrorCode code){
        this.code = code.get_code();
        this.message = code.get_message();
    }
}
