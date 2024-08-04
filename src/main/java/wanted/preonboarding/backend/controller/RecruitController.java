package wanted.preonboarding.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping("/{postId}")
    public ResponseEntity<Map<String,String>> updatePosting(@PathVariable Long postId,
                                                            @RequestBody RecruitPostingDto recruitPostingDto){
        recruitService.updatePosting(postId, recruitPostingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "내용을 성공적으로 수정했습니다."));
    }
    
    @DeleteMapping("/{postId}")
    public ResponseEntity<Map<String,String>> deletePosting(@PathVariable Long postId){
        recruitService.deletePosting(postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "내용을 성공적으로 삭제했습니다."));
    }
}
