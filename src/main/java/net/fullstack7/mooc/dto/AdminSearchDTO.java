package net.fullstack7.mooc.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import net.fullstack7.mooc.domain.Teacher;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminSearchDTO extends PageDTO<Teacher> {
    @Builder.Default
    private int memberType = -1;
    @Builder.Default
    private int isApproved = -1;
    private String status;
    private String searchId;
    @Builder.Default
    private String typeSelect = "t";

    @Override
    public void initialize() {
        super.initialize();
        if(this.typeSelect.equals("s"))
            this.setQueryString(URLEncoder.encode("typeSelect=" + this.typeSelect + "&status=" + this.status + "&searchId=" + this.searchId + "&memberType=" + memberType, StandardCharsets.UTF_8));
        else
            this.setQueryString(URLEncoder.encode("typeSelect=" + this.typeSelect + "&status=" + this.status + "&searchId=" + this.searchId + "&isApproved=" + memberType, StandardCharsets.UTF_8));
    }
}
