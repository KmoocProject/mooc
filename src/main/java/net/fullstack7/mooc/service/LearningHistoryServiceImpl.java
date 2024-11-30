package net.fullstack7.mooc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.*;
import net.fullstack7.mooc.dto.LearningHistoryDTO;
import net.fullstack7.mooc.repository.LearningHistoryRepository;
import net.fullstack7.mooc.repository.LectureContentRepository;
import net.fullstack7.mooc.repository.LectureRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class LearningHistoryServiceImpl implements LearningHistoryServiceIf{
    private final LearningHistoryRepository learningHistoryRepository;
    private final LectureRepository lectureRepository;
    private final LectureContentRepository lectureContentRepository;
    @Override
    public int saveAll(int courseId, String memberId) {
        log.info("LearningHistoryService saveAll");
        List<Lecture> lectures = lectureRepository.findAllByCourse(Course.builder().courseId(courseId).build());
        log.info("lectures : {}", lectures);
        List<LearningHistory> learningHistoryList = new ArrayList<>();
        for(Lecture lecture : lectures){
            log.info("lecture : {}", lecture);
            List<LectureContent> videoLectureContents = lectureContentRepository.findByLectureAndIsVideo(lecture, 1);
            log.info("lectureContents : {}", videoLectureContents);
            if(videoLectureContents.isEmpty()){
                continue;
            }
            for(LectureContent lectureContent : videoLectureContents){
                log.info("lectureContent : {}", lectureContent);
                learningHistoryList.add(
                        LearningHistory
                                .builder()
                                .lectureContentId(lectureContent.getLectureContentId())
                                .member(Member.builder().memberId(memberId).build())
                                .build()
                );
            }
        }
        log.info("learningHistoryList : {}", learningHistoryList);
        return learningHistoryRepository.saveAll(learningHistoryList).size();
    }

    @Override
    public void deleteAll(int courseId, String memberId) {
        List<Lecture> lectures = lectureRepository.findAllByCourse(Course.builder().courseId(courseId).build());
        List<LearningHistory> learningHistoryList = new ArrayList<>();
        for(Lecture lecture : lectures){
            List<LectureContent> videoLectureContents = lectureContentRepository.findByLectureAndIsVideo(lecture, 1);
            log.info("lectureContents : {}", videoLectureContents);
            if(videoLectureContents.isEmpty()){
                continue;
            }
            for(LectureContent lectureContent : videoLectureContents){
                log.info("lectureContent : {}", lectureContent);
                learningHistoryList.add(
                    learningHistoryRepository.findByLectureContentIdAndMember(
                            lectureContent.getLectureContentId(),
                            Member.builder().memberId(memberId).build()
                    )
                );
            }
        }
        learningHistoryRepository.deleteAll(learningHistoryList);
    }
}
