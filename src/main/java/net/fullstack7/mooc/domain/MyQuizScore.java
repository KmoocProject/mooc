package net.fullstack7.mooc.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="myQuizScore")
public class MyQuizScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="myQuizId", unique = true, nullable = false)
    private int myQuizId; // INT PRIMARY KEY AUTO_INCREMENT, -- 내 퀴즈 ID

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;
    @ManyToOne
    @JoinColumn(name = "quizId", nullable = false)
    private Quiz quiz;

    @Column(name="isCorrect", columnDefinition = "tinyint(1) default 0")
    private int isCorrect; // TINYINT(1) DEFAULT 0, -- 정답 여부
//    FOREIGN KEY (memberId) REFERENCES member(memberId), -- 회원 ID 외래 키
//    FOREIGN KEY (quizId) REFERENCES quiz(quizId) -- 퀴즈 ID 외래 키
}
