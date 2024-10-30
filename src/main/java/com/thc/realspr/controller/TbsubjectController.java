package com.thc.realspr.controller;

import com.thc.realspr.dto.TbmessageDto;
import com.thc.realspr.dto.TbsubjectDto;
import com.thc.realspr.dto.UserInteractionDto;
import com.thc.realspr.service.TbKaFeedService;
import com.thc.realspr.service.TbsubjectService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/subject")
@RestController
public class TbsubjectController {

    private final TbsubjectService tbsubjectService;

    public TbsubjectController(
            TbsubjectService tbsubjectService
    ) {
        this.tbsubjectService = tbsubjectService;
    }

    @Operation(summary = "주제 세부내용",
            description = "주제의 id와 유저 id로 세부내용 조회 <br />"
                    + "@param Long subjectId <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsubjectDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
                    + "@exception 404 Not Found (주제 또는 유저를 찾을 수 없는 경우) <br />"
                    + "@exception 400 Bad Request (필수 파라미터 누락 시)"
    )
    @GetMapping("/{subjectId}")
    public ResponseEntity<TbsubjectDto.DetailResDto> getSubjectDetail(@Valid @PathVariable("subjectId") Long subjectId, HttpServletRequest request) {
        String userId = (String) request.getAttribute("reqUserId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        TbsubjectDto.DetailResDto detail = tbsubjectService.getDetail(
                TbsubjectDto.DetailReqDto.builder()
                        .subjectId(subjectId)
                        .userId(userId)
                        .build()
        );

        if (detail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(detail);
    }


}
