package com.hyunjin.bbsback.repository;

import com.hyunjin.bbsback.entity.BoardListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {
}
