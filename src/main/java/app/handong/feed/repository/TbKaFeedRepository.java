package app.handong.feed.repository;

import app.handong.feed.domain.TbKaFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbKaFeedRepository extends JpaRepository<TbKaFeed, String> {
    List<TbKaFeed> findByDeletedNot(String deleted);

    List<TbKaFeed> findAllByDeletedNotOrderBySentAtDesc(String deleted);

    TbKaFeed findTopByOrderBySentAtDesc();
}
