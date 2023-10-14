package com.hyunjin.bbsback.repository;

import com.hyunjin.bbsback.entity.FavoriteEntity;
import com.hyunjin.bbsback.entity.primaryKey.FavoritePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {
}
