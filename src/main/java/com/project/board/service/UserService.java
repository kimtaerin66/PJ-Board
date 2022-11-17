package com.project.board.service;

import com.project.board.domain.ResponseVO;
import com.project.board.domain.db.UserDTO;

public interface UserService {


    ResponseVO      getUserList();


    ResponseVO     getUserDetail(String userId);

    ResponseVO     registerUser(UserDTO.Request request);

    ResponseVO     updateUser(UserDTO.Update request);

    ResponseVO     deleteUser(String userId);

    ResponseVO     duplicateId(String userId);

    ResponseVO     pwReset(String userId);
}