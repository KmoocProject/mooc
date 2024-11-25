package net.fullstack7.mooc.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.sql.Timestamp;

@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{4,14}$", message = "아이디는 영문자로 시작하고 5~15자의 영문자와 숫자만 사용 가능합니다.")
    private String memberId;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$", message = "비밀번호는 영문, 숫자, 특수문자를 포함하여 8~20자로 입력해주세요.")
    private String password;
    @NotBlank
    @Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 한글 2~10자 이내로 입력 가능합니다.")
    private String userName;
    @NotBlank
    @Pattern(regexp = "^[\\w-]+(?:\\.[\\w-]+)*@[\\w-]+(?:\\.[\\w-]+)*\\.[a-zA-Z]{2,}(\\.[a-zA-Z]{2,5})?$", message = "유효한 이메일 형식으로 입력해주세요.")
    private String email;
    @NotBlank
    private int gender;
    @NotBlank
    private String education;
    @NotBlank
    private int memberType;

    private String status;
    private int credit;
    private Timestamp createdAt;
}
