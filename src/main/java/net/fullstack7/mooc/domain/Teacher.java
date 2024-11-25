package net.fullstack7.mooc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="teacher")
public class Teacher {
    @Id
    private String teacherId; // VARCHAR(50) PRIMARY KEY, -- 교사 ID
    private String password; // VARCHAR(200) NOT NULL, -- 비밀번호
    private String teacherName; // VARCHAR(50) NOT NULL, -- 교사 이름
    private String email; // VARCHAR(100) UNIQUE NOT NULL, -- 이메일 (고유)
    private String institution; // VARCHAR(100) NOT NULL, -- 소속 기관
    private int isApproved; // TINYINT(1) DEFAULT 0, -- 관리자 승인 여부
    private String status; // VARCHAR(20) NOT NULL, -- 교사 상태 (ACTIVE, INACTIVE, WITHDRAWN)
    private LocalDateTime createdAt; //
}
