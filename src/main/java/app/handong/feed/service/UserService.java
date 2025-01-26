package app.handong.feed.service;

import app.handong.feed.domain.Tbuser;
import app.handong.feed.dto.TbuserDto;
import app.handong.feed.exception.NotFoundException;
import app.handong.feed.repository.TbuserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final TbuserRepository tbuserRepository;

    public UserService(TbuserRepository tbuserRepository) {
        this.tbuserRepository = tbuserRepository;
    }

    public TbuserDto.GithubRepo getRepo(Long githubUserId) {
        Tbuser user = tbuserRepository.findByGithubId(githubUserId);
        if (user == null) {
            throw new NotFoundException("User Not Found");
        }

        return new TbuserDto.GithubRepo(user.getGithubRepo(), user.getGithubId(), user.getGithubName());
    }
}
