package net.fullstack7.mooc.dto;

import java.util.List;
import net.fullstack7.mooc.dto.LectureDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CourseDetailDTO {
    private int courseId;
    private String title;
    private String description;
    private String thumbnail;
    private int weeks;
    private int learningTime;
    private String language;
    private int isCreditBank;
    private String teacherId;
    private List<LectureDTO> lectures;
}