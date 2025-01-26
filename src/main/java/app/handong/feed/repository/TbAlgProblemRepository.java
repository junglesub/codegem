package app.handong.feed.repository;

import app.handong.feed.domain.TbAlgProblem;
import app.handong.feed.dto.AlgorithmDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbAlgProblemRepository extends JpaRepository<TbAlgProblem, Long> {
    TbAlgProblem findByPpsNo(String ppsNo);
}
