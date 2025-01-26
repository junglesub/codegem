package app.handong.codegem.repository;

import app.handong.codegem.domain.Tbuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbuserRepository extends JpaRepository<Tbuser, String> {
    Tbuser findByEmail(String name);

    Tbuser findByGithubId(long githubId);
}
