package com.project.board.domain;

import lombok.Data;

import java.util.Date;


public class Board {

    @Data
    public static class Response{
        private int boardSeq;

        private String title;

        private String contents;

        private Date regDate;
    }

    @Data
    public static class Request{
        private int boardSeq;

        private String title;

        private String contents;

    }


}
