package net.fullstack7.mooc.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import net.fullstack7.mooc.domain.Course;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseSearchDTO extends PageDTO<Course> {
    @Builder.Default
    private int isCreditBank = -1;
    @Builder.Default
    private int subjectId = -1;
    private String status;

    @Override
    public void initialize() {
        super.initialize();

        StringBuilder sb = new StringBuilder();
        sb.append("searchCategory="+ getSearchField() +"&searchWord="+getSearchValue()+"&sortField="+getSortField()+"&sortOrder="+getSortDirection());
        if(isCreditBank >= 0) {
            sb.append("&isCreditBank="+isCreditBank);
        }
        if(subjectId >= 0) {
            sb.append("&subjectId="+subjectId);
        }
        if(status != null) {
            sb.append("&status="+URLEncoder.encode(status, StandardCharsets.UTF_8));
        }

        this.setQueryString(URLEncoder.encode(sb.toString(), StandardCharsets.UTF_8));
    }
}
