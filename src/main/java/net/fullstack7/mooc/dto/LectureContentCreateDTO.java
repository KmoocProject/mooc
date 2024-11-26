package net.fullstack7.mooc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.AssertTrue;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureContentCreateDTO {
    @NotNull(message = "강의 ID는 필수입니다")
    private Integer lectureId;

    @NotBlank(message = "제목은 필수입니다")
    @Size(min = 2, max = 200, message = "제목은 2-200자 사이여야 합니다")
    private String title;

    @NotBlank(message = "설명은 필수입니다")
    private String description;

    @NotNull(message = "콘텐츠 타입은 필수입니다")
    @Pattern(regexp = "^(video|file|quiz)$", message = "콘텐츠 타입은 video, file, quiz 중 하나여야 합니다")
    private String type;

    // video나 file 타입일 경우에만 필수
    private MultipartFile file;

    // quiz 타입일 경우에만 필수
    private String question;
    private String answer;

    @AssertTrue(message = "동영상/문서 타입의 경우 파일은 필수입니다")
    private boolean isFileValid() {
        if ("video".equals(type) || "file".equals(type)) {
            return file != null && !file.isEmpty();
        }
        return true;
    }

    @AssertTrue(message = "퀴즈 타입의 경우 문제와 답변은 필수입니다")
    private boolean isQuizValid() {
        if ("quiz".equals(type)) {
            return question != null && !question.isBlank() 
                && answer != null && !answer.isBlank();
        }
        return true;
    }
}
