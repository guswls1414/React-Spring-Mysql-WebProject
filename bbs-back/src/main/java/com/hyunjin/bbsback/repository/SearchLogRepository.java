package com.hyunjin.bbsback.repository;

import com.hyunjin.bbsback.entity.SearchLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer> {
}
