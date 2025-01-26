package app.handong.codegem.service;

import app.handong.codegem.domain.TbAlgSolution;
import app.handong.codegem.dto.AlgorithmDto;
import app.handong.codegem.exception.NotFoundException;
import app.handong.codegem.mapper.TbAlgSolutionMapper;
import app.handong.codegem.repository.TbAlgSolutionRepository;
import app.handong.codegem.util.GHAppHandler;
import app.handong.codegem.util.JWTFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlgSolutionService {

    private final TbAlgSolutionRepository tbAlgSolutionRepository;
    private final TbAlgSolutionMapper tbAlgSolutionMapper;

    public AlgSolutionService(TbAlgSolutionRepository tbAlgSolutionRepository, TbAlgSolutionMapper tbAlgSolutionMapper) {
        this.tbAlgSolutionRepository = tbAlgSolutionRepository;
        this.tbAlgSolutionMapper = tbAlgSolutionMapper;
    }

    public AlgorithmDto.SolutionResDto create(AlgorithmDto.SolutionReqDto solutionReqDto, String githubUserId) {

        // Add to github
        try {
            final String repo = solutionReqDto.getGithubRepoId();
            final String accessToken = GHAppHandler.getAccessToken(repo);

            // Todo: Get information from db not user
            GHAppHandler.addLabel(accessToken, repo, solutionReqDto.getPlatform());

            String body = solutionReqDto.getProblemUrl();
            body += "\n";
            body += "```" + solutionReqDto.getExt() + " showLineNumbers";
            body += "\n";
            body += solutionReqDto.getCode();
            body += "\n";
            body += "```\n\n";
            body += solutionReqDto.getMessage();

            int issueNumber = GHAppHandler.addIssue(accessToken, repo, solutionReqDto.getPlatform(), solutionReqDto.getTitle(), body);

            TbAlgSolution tbAlgSolution = new TbAlgSolution(repo, solutionReqDto.getGithubFilePath(), issueNumber, solutionReqDto.getProblemUrl(), githubUserId);
            tbAlgSolutionRepository.save(tbAlgSolution);
            return tbAlgSolution.convertToDto();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1. Check if label exists


        return null;
//        TbAlgSolution tbAlgSolution = new TbAlgSolution(solutionReqDto.getGithubRepoId(), solutionReqDto.getGithubFilePath(), solutionReqDto.getGithubIssueId(), solutionReqDto.getUserId(), solutionReqDto.getMessage());
//        try {
//            System.out.println(JWTFactory.generateJwt("Iv23liw14IEZDyFZSAOV"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        tbAlgSolveRepository.save(tbAlgSolve);
//        return tbAlgSolution.convertToDto();
    }

    public AlgorithmDto.SolutionResDto getById(String id) {
        TbAlgSolution tbAlgSolution = tbAlgSolutionRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found AlgSolve"));
        return tbAlgSolution.convertToDto();
    }

    public List<AlgorithmDto.SolutionResDto> getAll() {
        return tbAlgSolutionRepository.findAll().stream().map(TbAlgSolution::convertToDto).collect(Collectors.toList());
    }

    public List<AlgorithmDto.AlgSolutionDashboardDto> aggregateAllSolutions() {
        return tbAlgSolutionMapper.aggregateAllSolutions();
    }

    public List<String> getSolvedPaths(Long userId) {
        return tbAlgSolutionMapper.findPath(userId);
    }
}
