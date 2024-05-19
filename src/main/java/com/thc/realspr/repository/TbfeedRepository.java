package com.thc.realspr.repository;

import com.thc.realspr.domain.Tbfeed;
import com.thc.realspr.domain.Tbuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbfeedRepository extends JpaRepository<Tbfeed, String> {
    List<Tbfeed> findByDeletedNot(String deleted);
}
