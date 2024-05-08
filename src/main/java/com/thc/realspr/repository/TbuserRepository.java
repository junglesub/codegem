package com.thc.realspr.repository;

import com.thc.realspr.domain.Tbuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbuserRepository extends JpaRepository<Tbuser, String> {
}
