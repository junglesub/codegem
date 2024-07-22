package com.thc.realspr.repository;

import com.thc.realspr.domain.TbKaFeed;
import com.thc.realspr.domain.Tbfeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbKaFeedRepository extends JpaRepository<TbKaFeed, String> {
    List<TbKaFeed> findByDeletedNot(String deleted);

    TbKaFeed findTopByOrderBySentAtDesc();
}
