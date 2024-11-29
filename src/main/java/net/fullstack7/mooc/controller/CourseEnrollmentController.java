package net.fullstack7.mooc.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.dto.ApiResponse;
import net.fullstack7.mooc.dto.CourseEnrollmentDTO;
import net.fullstack7.mooc.dto.MemberDTO;
import net.fullstack7.mooc.service.CourseEnrollmentServiceIf;
import net.fullstack7.mooc.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseEnrollment")
@RequiredArgsConstructor
@Log4j2
public class CourseEnrollmentController {
    private final CourseEnrollmentServiceIf courseEnrollmentService;
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @PostMapping("/regist/{courseId}")
    public ResponseEntity<?> regist(@PathVariable int courseId
    , HttpSession session
    ) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        try{
            courseEnrollmentService.regist(
                    CourseEnrollmentDTO
                            .builder()
                            .course(Course.builder().courseId(courseId).build())
                            .member(modelMapper.map(memberDTO, Member.class))
                            .build()
            );
            return ResponseEntity.ok(ApiResponse.success("수강신청이 완료되었습니다."));
        }catch(Exception e){
            log.error(e);
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
