package wanted.preonboarding.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostDetailDto {
    private Long postId;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private int reward;
    private String skill;
    private String contents;
    private List<Long> otherRecruitPost;
}
