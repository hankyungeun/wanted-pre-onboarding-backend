package wanted.preonboarding.backend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.preonboarding.backend.dto.PostDetailDto;
import wanted.preonboarding.backend.dto.PostListResponseDto;
import wanted.preonboarding.backend.dto.RecruitPostingDto;
import wanted.preonboarding.backend.entity.Company;
import wanted.preonboarding.backend.entity.RecruitPosting;
import wanted.preonboarding.backend.repository.CompanyRepository;
import wanted.preonboarding.backend.repository.RecruitRepository;
import wanted.preonboarding.backend.searcher.SearchBuilder;
import wanted.preonboarding.backend.searcher.SearchOperationType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final CompanyRepository companyRepository;

    public RecruitPosting findPost(Long postId){
        return recruitRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("등록된 공고가 없습니다."));
    }

    public PostDetailDto getDetailPost(Long postId){
        RecruitPosting recruitPosting = findPost(postId);

        PostDetailDto postDetailDto = new PostDetailDto();
        postDetailDto.setPostId(recruitPosting.getId());
        postDetailDto.setCompanyName(recruitPosting.getCompany().getName());
        postDetailDto.setNation(recruitPosting.getCompany().getCountry());
        postDetailDto.setRegion(recruitPosting.getCompany().getRegion());
        postDetailDto.setPosition(recruitPosting.getPosition());
        postDetailDto.setReward(recruitPosting.getReward());
        postDetailDto.setSkill(recruitPosting.getSkill());
        postDetailDto.setContents(recruitPosting.getContents());

        List<Long> otherRecruitPost = recruitRepository.findByCompanyId(recruitPosting.getCompany().getId())
                .stream()
                .filter(post -> !post.getId().equals(postId))
                .map(RecruitPosting::getId)
                .collect(Collectors.toList());
        postDetailDto.setOtherRecruitPost(otherRecruitPost);

        if (!otherRecruitPost.isEmpty()) {
            postDetailDto.setOtherRecruitPost(otherRecruitPost);
        }

        return postDetailDto;
    }

    public ArrayList<PostListResponseDto> getAllPosting(String keyword){
        SearchBuilder<RecruitPosting> searchBuilder = SearchBuilder.builder();
        if (keyword != null && !keyword.isEmpty()) {
            searchBuilder.with("skill", SearchOperationType.CONTAINS, keyword, true);
            searchBuilder.with("company.name", SearchOperationType.CONTAINS, keyword, true);
        }

        List<RecruitPosting> recruitPostings = recruitRepository.findAll(searchBuilder.build());
        ArrayList<PostListResponseDto> postList = new ArrayList<>();
        for (RecruitPosting post : recruitPostings) {
            PostListResponseDto dto = new PostListResponseDto();
            dto.setPostId(post.getId());
            dto.setCompanyName(post.getCompany().getName());
            dto.setNation(post.getCompany().getCountry());
            dto.setRegion(post.getCompany().getRegion());
            dto.setPosition(post.getPosition());
            dto.setReward(post.getReward());
            dto.setSkill(post.getSkill());
            postList.add(dto);
        }
        return postList;
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
        RecruitPosting recruitPosting = findPost(postId);
        recruitPosting.setPosition(recruitPostingDto.getPosition());
        recruitPosting.setReward(recruitPostingDto.getReward());
        recruitPosting.setContents(recruitPostingDto.getContents());
        recruitPosting.setSkill(recruitPostingDto.getSkill());
    }

    @Transactional
    public void deletePosting(Long postId){
        RecruitPosting recruitPosting = findPost(postId);
        recruitRepository.delete(recruitPosting);
    }
}
