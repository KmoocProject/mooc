package net.fullstack7.mooc.util;

import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.constant.FileConstants;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class FileUploadUtil {

    @Value("${file.upload.path}")
    private String uploadPath;

    /**
     * 단일 파일 업로드
     */
    public String uploadFile(MultipartFile file, String subPath) throws IOException {
        if (file.isEmpty()) {
            log.info("파일이 비어있습니다.");
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }

        String originalFilename = file.getOriginalFilename();
        log.info("파일 업로드: {}", originalFilename);

        try {
            // 파일명 생성
            String extension = FilenameUtils.getExtension(originalFilename);
            String newFilename = UUID.randomUUID().toString() + "." + extension;

            // 업로드 경로 생성
            String targetPath = Paths.get(uploadPath, subPath).toString();
            File targetDir = new File(targetPath);
            if (!targetDir.exists()) {
                targetDir.mkdirs();
                log.debug("디렉토리 생성: {}", targetPath);
            }

            // 파일 저장
            Path targetFile = Paths.get(targetPath, newFilename);
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);

            log.info("파일 업로드 성공: {}", newFilename);
            return Paths.get(subPath, newFilename).toString();

        } catch (IOException e) {
            log.error("파일 업로드 실패: {}", originalFilename, e);
            throw e;
        }
    }

    /**
     * 다중 파일 업로드
     */
    public List<String> uploadFiles(List<MultipartFile> files, String subPath) {
        log.info("{}개의 파일 업로드: {}", files.size(), subPath);
        List<String> uploadedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                String filePath = uploadFile(file, subPath);
                uploadedFiles.add(filePath);
            } catch (IOException e) {
                log.error("파일 업로드 실패: {}", file.getOriginalFilename(), e);
            }
        }

        return uploadedFiles;
    }

    /**
     * 파일 삭제
     */
    public boolean deleteFile(String filePath) {
        log.info("파일 삭제: {}", filePath);
        try {
            Path fullPath = Paths.get(uploadPath, filePath);
            boolean deleted = Files.deleteIfExists(fullPath);
            
            if (deleted) {
                log.info("파일 삭제 성공: {}", filePath);
            } else {
                log.warn("파일 없음: {}", filePath);
            }
            
            return deleted;
        } catch (IOException e) {
            log.error("파일 삭제 실패: {}", filePath, e);
            return false;
        }
    }

    /**
     * 파일 존재 여부 확인
     */
    public boolean exists(String filePath) {
        Path fullPath = Paths.get(uploadPath, filePath);
        return Files.exists(fullPath);
    }

    /**
     * 파일 경로 가져오기
     */
    public Path getFilePath(String filePath) {
        return Paths.get(uploadPath, filePath);
    }

    public void validateFileExtension(MultipartFile file, String[] allowedExtensions) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        boolean isValid = Arrays.asList(allowedExtensions).contains(extension);
        
        if (!isValid) {
            throw new IllegalArgumentException("허용되지 않는 파일 형식입니다: " + extension);
        }
    }

    public String uploadVideoFile(MultipartFile file, String subPath) throws IOException {
        validateFileExtension(file, FileConstants.ALLOWED_VIDEO_EXTENSIONS);
        return uploadFile(file, subPath);
    }

    public String uploadDocumentFile(MultipartFile file, String subPath) throws IOException {
        validateFileExtension(file, FileConstants.ALLOWED_DOCUMENT_EXTENSIONS);
        return uploadFile(file, subPath);
    }

    public String uploadImageFile(MultipartFile file, String subPath) throws IOException {
        validateFileExtension(file, FileConstants.ALLOWED_IMAGE_EXTENSIONS);
        return uploadFile(file, subPath);
    }
}