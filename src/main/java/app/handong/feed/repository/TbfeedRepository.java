package app.handong.feed.repository;

import app.handong.feed.domain.Tbfeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbfeedRepository extends JpaRepository<Tbfeed, String> {
    List<Tbfeed> findByDeletedNot(String deleted);
}
