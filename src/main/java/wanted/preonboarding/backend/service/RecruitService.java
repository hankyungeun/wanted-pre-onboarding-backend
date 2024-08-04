package wanted.preonboarding.backend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.preonboarding.backend.dto.RecruitPostingDto;
import wanted.preonboarding.backend.entity.Company;
import wanted.preonboarding.backend.entity.RecruitPosting;
import wanted.preonboarding.backend.repository.CompanyRepository;
import wanted.preonboarding.backend.repository.RecruitRepository;

@Service
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final CompanyRepository companyRepository;

    public RecruitPosting getRecruitPosting(Long postId){
        return recruitRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("등록된 공고가 없습니다."));
    }

    @Transactional
    public void createPosting(RecruitPostingDto recruitPostingDto){
        Company company = companyRepository.findById(recruitPostingDto.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("등록된 회사가 없습니다."));

        RecruitPosting recruitPosting = new RecruitPosting();
        recruitPosting.setCompany(company);
        recruitPosting.setPosition(recruitPostingDto.getPosition());
        recruitPosting.setReward(recruitPostingDto.getReward());
        recruitPosting.setContents(recruitPostingDto.getContents());
        recruitPosting.setSkill(recruitPostingDto.getSkill());

        recruitRepository.save(recruitPosting);
    }

    @Transactional
    public void updatePosting(Long postId, RecruitPostingDto recruitPostingDto){
        RecruitPosting recruitPosting = getRecruitPosting(postId);
        recruitPosting.setPosition(recruitPostingDto.getPosition());
        recruitPosting.setReward(recruitPostingDto.getReward());
        recruitPosting.setContents(recruitPostingDto.getContents());
        recruitPosting.setSkill(recruitPostingDto.getSkill());
    }
}
