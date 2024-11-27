package net.fullstack7.mooc.search;

import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.dto.CourseResponseDTO;
import net.fullstack7.mooc.dto.CourseSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseSearch {
    Page<CourseResponseDTO> coursePage(Pageable pageable, CourseSearchDTO courseSearchDTO, String memberId);
}
