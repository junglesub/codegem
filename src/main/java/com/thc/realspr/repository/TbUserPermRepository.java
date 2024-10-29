package com.thc.realspr.repository;

import com.thc.realspr.domain.TbUserLike;
import com.thc.realspr.domain.TbUserPerm;
import com.thc.realspr.id.UserPermId;
import com.thc.realspr.id.UserSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TbUserPermRepository extends JpaRepository<TbUserPerm, UserPermId> {
    Optional<TbUserPerm> findById(UserPermId userPermId);
}
