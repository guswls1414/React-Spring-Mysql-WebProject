package com.hyunjin.bbsback.service;

import com.hyunjin.bbsback.dto.request.board.PostBoardRequestDto;
import com.hyunjin.bbsback.dto.response.board.PostBoardResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);

}
