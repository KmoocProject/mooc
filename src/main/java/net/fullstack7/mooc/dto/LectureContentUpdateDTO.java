package net.fullstack7.mooc.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureContentUpdateDTO {
    private String title;
    private String description;
    private String type;  // video, file, quiz
    private MultipartFile file;
    private String question;
    private String answer;
}