package net.fullstack7.mooc.service;

import net.fullstack7.mooc.dto.LearningHistoryDTO;

import java.util.List;

public interface LearningHistoryServiceIf {
    public int saveAll(int courseId, String memberId);
    public void deleteAll(int courseId, String memberId);
}
