package app.handong.feed.repository;

import app.handong.feed.domain.TbAlgSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbKaFeedRepository extends JpaRepository<TbAlgSolution, String> {
    List<TbAlgSolution> findByDeletedNot(String deleted);

//    List<TbAlgSolve> findAllByDeletedNotOrderBySentAtDesc(String deleted);
//
//    TbAlgSolve findTopByOrderBySentAtDesc();
}
