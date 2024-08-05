package wanted.preonboarding.backend.dto;

import lombok.Data;

@Data
public class PostListResponseDto {
    private Long postId;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private int reward;
    private String skill;
}
