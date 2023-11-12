package com.hyunjin.bbsback.service.implement;

import com.hyunjin.bbsback.dto.response.ResponseDto;
import com.hyunjin.bbsback.dto.response.user.GetSignInUserResponseDto;
import com.hyunjin.bbsback.entity.UserEntity;
import com.hyunjin.bbsback.repository.UserRepository;
import com.hyunjin.bbsback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {   // 로그인 유저 정보 불러오기

        UserEntity userEntity = null;

        try {
            userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return GetSignInUserResponseDto.notExistUser();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);
    }
}
