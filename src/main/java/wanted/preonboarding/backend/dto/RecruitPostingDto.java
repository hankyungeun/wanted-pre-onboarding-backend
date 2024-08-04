package wanted.preonboarding.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitPostingDto {
    private Long companyId;
    private String position;
    private int reward;
    private String contents;
    private String skill;
}