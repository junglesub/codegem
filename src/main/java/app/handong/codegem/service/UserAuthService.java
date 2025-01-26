package app.handong.codegem.service;

import app.handong.codegem.domain.Tbuser;
import app.handong.codegem.dto.GHOauthDto;
import app.handong.codegem.exception.NoAuthenticatedException;
import app.handong.codegem.repository.TbuserRepository;
import app.handong.codegem.util.GHOauthHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class UserAuthService {

    private final TbuserRepository tbuserRepository;
    @Value("${GH_CLIENT_ID:${GH_CLIENT_ID_DEFAULT:#{null}}}")
    String ghClientId;

    @Value("${GH_CLIENT_SECRET:${GH_CLIENT_SECRET_DEFAULT:#{null}}}")
    String ghClientSecret;

    public UserAuthService(TbuserRepository tbuserRepository) {
        this.tbuserRepository = tbuserRepository;
    }


    public String resolveGHAccess(String code) {
        System.out.println(ghClientId);
        System.out.println(ghClientSecret);
        GHOauthDto.Response response = GHOauthHandler.getGithubOauth(ghClientId, ghClientSecret, code);

        if (response.getAccessToken() == null) {
            throw new NoAuthenticatedException("Invalid Token");
        }
//        if (response.getScope() == null || !response.getScope().contains("write:discussion"))
//            throw new NoAuthenticatedException("No Dissusion Write Permission");

        // Save user to database
        GHOauthDto.User ghUser = GHOauthHandler.getGhUser(response.getAccessToken());
        Tbuser tbuser = tbuserRepository.findByGithubId(ghUser.getId());
        if (tbuser == null) {
            // New user
            tbuser = new Tbuser();
            tbuser.setCreated_at(LocalDateTime.now());
            tbuser.setModified_at(LocalDateTime.now());
        }
        tbuser.setGithubId(ghUser.getId());
        tbuser.setGithubName(ghUser.getLogin());
        tbuser.setLast_login_time(LocalDateTime.now());
        tbuser.setEmail(ghUser.getEmail());
        tbuserRepository.save(tbuser);
        return response.getAccessToken();
    }

    public boolean setRepo(Long githubId, String repoName) {
        Tbuser tbuser = tbuserRepository.findByGithubId(githubId);
        if (tbuser == null) {
            return false;
        }

        tbuser.setGithubRepo(repoName);
        tbuser.setModified_at(LocalDateTime.now());
        tbuserRepository.save(tbuser);
        return true;
    }
}
