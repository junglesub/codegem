package app.handong.feed.repository;

import app.handong.feed.domain.TbUserPerm;
import app.handong.feed.id.UserPermId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TbUserPermRepository extends JpaRepository<TbUserPerm, UserPermId> {
    Optional<TbUserPerm> findById(UserPermId userPermId);
}
