package net.fullstack7.mooc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.Admin;
import net.fullstack7.mooc.domain.Notice;
import net.fullstack7.mooc.dto.NoticeDTO;
import net.fullstack7.mooc.dto.PageDTO;
import net.fullstack7.mooc.repository.NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeServiceIf {
    private final NoticeRepository noticeRepository;

    @Override
    public Page<NoticeDTO> getNotices(PageDTO<NoticeDTO> pageDTO) {

        Page<NoticeDTO> notices = noticeRepository.noticePage(pageDTO.getPageable(), pageDTO.getSearchField(), pageDTO.getSearchValue());

        pageDTO.setTotalCount((int)notices.getTotalElements());

        notices = noticeRepository.noticePage(pageDTO.getPageable(), pageDTO.getSearchField(), pageDTO.getSearchValue());

        return notices;

    }

    @Override
    public Notice view(int noticeId) {
        Optional<Notice> byNoticeId = noticeRepository.findById(noticeId);

        if(byNoticeId != null)
            return byNoticeId.get();

        return null;
    }

    @Override
    public Notice getNewestNotice() {
        return noticeRepository.findTopByOrderByCreatedAtDesc().orElse(null);
    }
}
