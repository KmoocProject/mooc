package net.fullstack7.mooc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModifyCreditDTO {
    @Pattern(regexp = "^[가-힣]{1,10}$", message = "10자 이내로 입력해주세요.")
    private String name;
    @Pattern(regexp = "^\\d{11}$", message = "11자리 숫자로 입력해주세요.")
    private String phone;
}
