package net.fullstack7.mooc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureContentCreateDTO {
    @NotBlank(message = "콘텐츠 제목을 입력해주세요")
    private String title;
    
    @NotBlank(message = "콘텐츠 설명을 입력해주세요")
    private String description;
    
    @NotNull(message = "섹션 ID가 필요합니다")
    private Integer lectureId;
    
    private int isVideo;
    
    private MultipartFile file;
    
    // 퀴즈
    private List<QuizDTO> quizList;
}
