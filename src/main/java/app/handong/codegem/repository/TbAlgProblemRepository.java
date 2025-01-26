package app.handong.codegem.repository;

import app.handong.codegem.domain.TbAlgProblem;
import app.handong.codegem.dto.AlgorithmDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbAlgProblemRepository extends JpaRepository<TbAlgProblem, Long> {
    TbAlgProblem findByPpsNo(String ppsNo);
}
