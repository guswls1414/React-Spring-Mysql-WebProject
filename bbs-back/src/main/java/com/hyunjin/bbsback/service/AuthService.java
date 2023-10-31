package com.hyunjin.bbsback.service;

import com.hyunjin.bbsback.dto.request.auth.SignInRequestDto;
import com.hyunjin.bbsback.dto.request.auth.SignUpRequestDto;
import com.hyunjin.bbsback.dto.response.auth.SignInResponseDto;
import com.hyunjin.bbsback.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
