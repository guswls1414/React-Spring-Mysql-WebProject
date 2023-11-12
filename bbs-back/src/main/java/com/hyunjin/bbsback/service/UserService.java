package com.hyunjin.bbsback.service;

import com.hyunjin.bbsback.dto.response.user.GetSignInUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);

}
