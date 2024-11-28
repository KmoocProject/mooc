package net.fullstack7.mooc.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "lectureContent")
@Table(name="quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="quizId", unique = true, nullable = false)
    private int quizId; // INT PRIMARY KEY AUTO_INCREMENT, -- 퀴즈 ID
    @Column(name="question", columnDefinition = "text not null")
    private String question; // TEXT NOT NULL, -- 질문
    @Column(name="answer", columnDefinition = "text not null")
    private String answer; // TEXT NOT NULL, -- 정답

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lectureContentId", nullable=false)
    private LectureContent lectureContent;
//    FOREIGN KEY (lectureContentId) REFERENCES lectureContent(lectureContentId) -- 강의 영역 콘텐츠 ID 외래 키
}
