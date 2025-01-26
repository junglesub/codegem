package app.handong.codegem.controller;

import app.handong.codegem.dto.AlgorithmDto;
import app.handong.codegem.service.AlgProblemService;
import app.handong.codegem.service.AlgSolutionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/pps")
@RestController
public class AlgProblemController {

    private final AlgProblemService algProblemService;

    public AlgProblemController(AlgProblemService algProblemService) {
        this.algProblemService = algProblemService;
    }

    @GetMapping("/findcode/{id}")
    public ResponseEntity<AlgorithmDto.ProblemResDto> get(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(algProblemService.findByPPSId(id));
    }

}