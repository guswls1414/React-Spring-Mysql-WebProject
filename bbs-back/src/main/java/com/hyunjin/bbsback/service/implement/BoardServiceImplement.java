package com.hyunjin.bbsback.service.implement;

import com.hyunjin.bbsback.dto.request.board.PostBoardRequestDto;
import com.hyunjin.bbsback.dto.response.ResponseDto;
import com.hyunjin.bbsback.dto.response.board.PostBoardResponseDto;
import com.hyunjin.bbsback.entity.BoardEntity;
import com.hyunjin.bbsback.entity.ImageEntity;
import com.hyunjin.bbsback.repository.BoardRepository;
import com.hyunjin.bbsback.repository.ImageRepository;
import com.hyunjin.bbsback.repository.UserRepository;
import com.hyunjin.bbsback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {

        try {

            boolean existedEmail = userRepository.existsByEmail(email);
            if (!existedEmail) return PostBoardResponseDto.notExistUser();

            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);

            int boardNumber = boardEntity.getBoardNumber();
            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for(String image : boardImageList) {
                ImageEntity imageEntity = new ImageEntity(boardNumber, image);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();
    }
}
