package wanted.preonboarding.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.preonboarding.backend.dto.ApplyDto;
import wanted.preonboarding.backend.service.ApplyService;

import java.util.Map;

@RestController
@RequestMapping("/api/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping
    public ResponseEntity<Map<String,String>> createPosting(@RequestBody ApplyDto applyDto){
        applyService.apply(applyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "성공적으로 지원 했습니다."));
    }
}
