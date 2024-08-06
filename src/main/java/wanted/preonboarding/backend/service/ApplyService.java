package wanted.preonboarding.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wanted.preonboarding.backend.dto.ApplyDto;
import wanted.preonboarding.backend.entity.ApplyList;
import wanted.preonboarding.backend.entity.RecruitPosting;
import wanted.preonboarding.backend.entity.User;
import wanted.preonboarding.backend.repository.ApplyRepository;
import wanted.preonboarding.backend.repository.RecruitRepository;
import wanted.preonboarding.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final UserRepository userRepository;
    private final ApplyRepository applyRepository;
    private final RecruitRepository recruitRepository;

    @Transactional
    public void apply(ApplyDto applyDto){
        User user = userRepository.findById(applyDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        RecruitPosting recruitPosting = recruitRepository.findById(applyDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("등록된 공고가 없습니다."));

        if (applyRepository.existsByUserAndRecruitPosting(user, recruitPosting)) {
            throw new IllegalArgumentException("이미 지원한 공고입니다.");
        }

        ApplyList applyList = new ApplyList();
        applyList.setUser(user);
        applyList.setRecruitPosting(recruitPosting);

        applyRepository.save(applyList);
    }
}
