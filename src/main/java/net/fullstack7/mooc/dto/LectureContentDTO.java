package net.fullstack7.mooc.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import net.fullstack7.mooc.dto.LectureFileDTO;
import net.fullstack7.mooc.dto.QuizDTO;
import net.fullstack7.mooc.domain.LectureContent;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureContentDTO {
    private int lectureContentId;
    private String title;
    private String description;
    private int isVideo;
    private LectureFileDTO file;
    private List<QuizDTO> quizzes;
    private String type;
    
    public static LectureContentDTO from(LectureContent content) {
        String type = content.getIsVideo() == 1 ? "video" : 
                     content.getQuizzes() != null && !content.getQuizzes().isEmpty() ? "quiz" : "file";
        
        return LectureContentDTO.builder()
            .lectureContentId(content.getLectureContentId())
            .title(content.getTitle())
            .description(content.getDescription())
            .isVideo(content.getIsVideo())
            .type(type)
            .build();
    }
}