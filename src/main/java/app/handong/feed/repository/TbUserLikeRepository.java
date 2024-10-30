package app.handong.feed.repository;

import app.handong.feed.domain.TbUserLike;
import app.handong.feed.id.UserSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TbUserLikeRepository extends JpaRepository<TbUserLike, UserSubjectId> {
    Optional<TbUserLike> findById(UserSubjectId userSubjectId);

    List<TbUserLike> findAllByIdSubjectId(int userSubjectId);
}
