package com.thc.realspr.repository;

import com.thc.realspr.domain.TbUserInteraction;
import com.thc.realspr.id.UserSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TbUserInteractionRepository extends JpaRepository<TbUserInteraction, UserSubjectId> {
    Optional<TbUserInteraction> findById(UserSubjectId userSubjectId);

    List<TbUserInteraction> findAllByIdSubjectId(int userSubjectId);
}
