package com.flowiee.app.hethong.repository;

import com.flowiee.app.hethong.entity.CauHinhHeThong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowieeConfigRepository extends JpaRepository<CauHinhHeThong, Integer> {
    @Query("from CauHinhHeThong order by sort")
    List<CauHinhHeThong> findAll();
}