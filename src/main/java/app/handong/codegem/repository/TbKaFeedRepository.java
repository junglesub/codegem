package app.handong.codegem.repository;

import app.handong.codegem.domain.TbAlgSolution;
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
