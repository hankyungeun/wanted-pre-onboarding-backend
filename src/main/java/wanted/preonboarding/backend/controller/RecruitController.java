package wanted.preonboarding.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.preonboarding.backend.dto.RecruitPostingDto;
import wanted.preonboarding.backend.service.RecruitService;

import java.util.Map;

@RestController
@RequestMapping("/api/recruit")
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitService recruitService;

    @PostMapping
    public ResponseEntity<Map<String,String>> createPosting(@RequestBody RecruitPostingDto recruitPostingDto){
        recruitService.createPosting(recruitPostingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "공고를 성공적으로 등록했습니다."));
    }
}
