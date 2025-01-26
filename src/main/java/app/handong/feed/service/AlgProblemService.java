package app.handong.feed.service;

import app.handong.feed.dto.AlgorithmDto;
import app.handong.feed.repository.TbAlgProblemRepository;
import org.springframework.stereotype.Service;

@Service
public class AlgProblemService {

    private final TbAlgProblemRepository tbAlgProblemRepository;

    public AlgProblemService(TbAlgProblemRepository tbAlgProblemRepository) {
        this.tbAlgProblemRepository = tbAlgProblemRepository;
    }

    public AlgorithmDto.ProblemResDto findByPPSId(String ppsId) {
        // Preprocess
        char mainCode = Character.toUpperCase(ppsId.charAt(0));
        String remainder = ppsId.substring(1);
        String padRemainder = String.format("%03d", Integer.parseInt(remainder));

        return tbAlgProblemRepository.findByPpsNo(mainCode + padRemainder).convertToDto();
    }

}
