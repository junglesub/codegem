package app.handong.feed.controller;

import app.handong.feed.dto.AlgorithmDto;
import app.handong.feed.dto.GHOauthDto;
import app.handong.feed.service.AlgSolutionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/solution")
@RestController
public class AlgSolutionController {

    private final AlgSolutionService algSolutionService;

    public AlgSolutionController(AlgSolutionService algSolutionService) {
        this.algSolutionService = algSolutionService;
    }

    @PostMapping("/create")
    public ResponseEntity<AlgorithmDto.SolutionResDto> solve(@RequestBody AlgorithmDto.SolutionReqDto solutionReqDto, HttpServletRequest request) {
        GHOauthDto.User GHUser = (GHOauthDto.User) request.getAttribute(GHOauthDto.User.class.getName());
        return ResponseEntity.status(HttpStatus.OK).body(algSolutionService.create(solutionReqDto, GHUser.getId() + ""));
    }

    @GetMapping("/get")
    public ResponseEntity<List<AlgorithmDto.SolutionResDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(algSolutionService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AlgorithmDto.SolutionResDto> get(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(algSolutionService.getById(id));
    }

    @GetMapping("/solved")
    public ResponseEntity<AlgorithmDto.AlgSolvedPaths> getSolvedPaths(HttpServletRequest request) {
        GHOauthDto.User GHUser = (GHOauthDto.User) request.getAttribute(GHOauthDto.User.class.getName());
        return ResponseEntity.ok(new AlgorithmDto.AlgSolvedPaths(algSolutionService.getSolvedPaths(GHUser.getId())));
    }

    @GetMapping("/aggregateAllSolutions")
    public ResponseEntity<List<AlgorithmDto.AlgSolutionDashboardDto>> aggregateAllSolutions() {
        return ResponseEntity.ok(algSolutionService.aggregateAllSolutions());
    }

}