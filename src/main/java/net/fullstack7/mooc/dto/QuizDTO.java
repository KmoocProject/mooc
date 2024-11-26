package net.fullstack7.mooc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {
    @NotBlank(message = "문제를 입력해주세요")
    private String question;
    
    @NotBlank(message = "정답을 입력해주세요")
    private String answer;
} 