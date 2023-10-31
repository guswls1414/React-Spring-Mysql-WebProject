package com.hyunjin.bbsback.service.implement;

import com.hyunjin.bbsback.dto.request.auth.SignInRequestDto;
import com.hyunjin.bbsback.dto.request.auth.SignUpRequestDto;
import com.hyunjin.bbsback.dto.response.ResponseDto;
import com.hyunjin.bbsback.dto.response.auth.SignInResponseDto;
import com.hyunjin.bbsback.dto.response.auth.SignUpResponseDto;
import com.hyunjin.bbsback.entity.UserEntity;
import com.hyunjin.bbsback.provider.JwtProvider;
import com.hyunjin.bbsback.repository.UserRepository;
import com.hyunjin.bbsback.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor    // final 로 되어있는 필수 필드에 대해 생성자를 만들어줌
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;    // RequiredArgsConstructor 에 의해 자동으로 생성자가 생성됨
    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) { // 회원가입 메서드

        try {
            //  request dto 에서 이메일을 가져오고 중복 확인
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if (existedEmail) return SignUpResponseDto.duplicateEmail();

            //  request dto 에서 이메일을 가져오고 중복 확인
            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname) return SignUpResponseDto.duplicateNickname();

            //  request dto 에서 이메일을 가져오고 중복 확인
            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
            if (existedTelNumber) return SignUpResponseDto.duplicateTelNumber();

            String password = dto.getPassword();    // request dto 에서 비밀번호 가져오기
            String encodedPassword = passwordEncoder.encode(password);  // 비밀번호 암호화
            dto.setPassword(encodedPassword);   // request dto 에 암호화된 비밀번호 넣기

            // UserEntity 생성 후 데이터베이스에 저장
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch(Exception exception) {  // 데이터베이스에 접근하는 경우 밖에 에러가 발생 안함
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) { // 로그인 메서드

        String token = null;

        try {

            // request dto 에서 이메일을 가져오고 존재 여부 확인
            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return SignInResponseDto.signInFailed();

            // request dto 에서 비밀번호를 가져오고 db에 저장된 암호화된 비밀번호랑 일치하는지 확인
            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return SignInResponseDto.signInFailed();

            token = jwtProvider.create(email);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);

    }
}
