# 프로젝트 명 : MOOC (Massive, Open, Online, Course) 플랫폼

## 📝 프로젝트 소개
> 학점은행제를 지원하는 온라인 교육 플랫폼
- KMOOC 사이트를 참고하여 만든 프로젝트

## 👥 팀원 소개
|이름|역할|담당 기능|GitHub|
|---|---|---|---|
|최사랑|팀장|• 프론트엔드 퍼블리싱<br>• 공통영역 레이아웃|[@GitHub](https://github.com/coeisarang)|
|강경민|팀원|• 선생님 페이지<br>• DB 설계<br>• 강의 관련<br>• 파일 업로드|[@GitHub](https://github.com/GyeongMin2)|
|강감찬|팀원|• 강의 신청 프로세스<br>• MOOC 프로젝트 전반 문서작업<br>• 페이징 유틸|[@GitHub](https://github.com/kangkamchan)|
|공미경|팀원|• 관리자 페이지<br>• 관리자 강의관리<br>• 관리자 회원관리<br>• 관리자 공지사항 관리|[@GitHub](https://github.com/qoiol)|
|이원희|팀원|• 회원 관련<br>• 로그인/로그아웃 <br>• 임시 비밀번호 발급|[@GitHub](https://github.com/wonheeLee95)|

## 📅 프로젝트 일정

| 단계     | 11/22 | 11/23 | 11/24 | 11/25 | 11/26 | 11/27 | 11/28 | 11/29 | 11/30 | 12/01 |
| -------- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
| 프로젝트 기획 | ✓     | ✓     |       |       |       |       |       |       |       |       |
| 요구사항 정의 |       |       | ✓     |       |       |       |       |       |       |       |
| 기능 명세서 |       |       | ✓     |       |       |       |       |       |       |       |
| DB설계     |       |       | ✓     |       |       |       |       |       |       |       |
| 개발 |       |       |       | ✓     | ✓     | ✓     | ✓     | ✓     |       |       |
| 테스트&배포 |       |       |       |       |       |       |       |       | ✓     | ✓     |

## 🛠 기술 스택
### Backend
- Java 17
- Spring Boot 3.3.6
- JPA/Hibernate

### Frontend
- Thymeleaf
- Bootstrap 5
- JavaScript

### Database
- MariaDB
- MySQL

### 빌드 도구
- Gradle

## 🔍 주요 기능

### 1. 회원 관리 기능
- 일반 회원/강사 회원가입 및 로그인
- 회원정보 수정 및 탈퇴
- 아이디/비밀번호 찾기
- 강사 회원 승인관리

### 2. 강좌 관리 기능
- 강좌 등록/수정/삭제
- 강좌 콘텐츠(강의,퀴즈 등) 관리
- 강좌 상태 관리 (임시저장/배포/삭제)
- 학점은행제 강좌 구분

### 3. 수강 관리 기능
- 수강신청/취소
- 학습 진도율 관리
- 수강완료 처리(80% 이상 수강시)
- 학점 이수 관리

### 4. 관리자 기능
- 회원/강사 관리
- 강좌/승인 관리
- 공지사항 관리

### 5. 파일 관리 기능
- 강의 자료 업로드/다운로드
- 이미지/동영상 파일 관리
- 허용 파일 형식 제한

## 💻 주요 코드

***
## 작성 양식
### 이름
#### 코드설멍

- 코드상세 설멍

```language
code
```
***

### 강경민
#### 3단계 강의 등록 시스템

**기본 정보 등록**
- 강좌 제목, 과목, 학습 기간, 언어 등 기본 정보 입력
- 썸네일 이미지 업로드 (지원 형식: jpg, jpeg, png, gif)
```java
@PostMapping("/courses")
public ResponseEntity<?> createCourse(@Valid CourseCreateDTO courseDTO, HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute("teacher");
    Course course = courseService.createCourse(courseDTO, teacher);
    return ResponseEntity.ok(CourseResponseDTO.from(course));
}

// 이미지 파일 업로드 처리
public String uploadImageFile(MultipartFile file, String subPath) throws IOException {
    validateFileExtension(file, FileConstants.ALLOWED_IMAGE_EXTENSIONS);
    return uploadFile(file, subPath);
}
```
**섹션 구성**
- 체계적인 학습을 위한 섹션 단위 구성
- 섹션별 제목과 설명 입력
- 자유로운 섹션 추가/삭제
```java
@PostMapping("/courses/{courseId}/lectures")
public ResponseEntity<?> createLecture(@PathVariable int courseId, @RequestBody LectureCreateDTO dto) {
    Lecture lecture = courseService.createLecture(courseId, dto);
    return ResponseEntity.ok(LectureResponseDTO.from(lecture));
}
```
**콘텐츠 등록**
- 다양한 유형의 콘텐츠 지원
  - 동영상 (mp4, mov)
  - 문서 (pdf, ppt, pptx, doc, docx 등)
  - 퀴즈
- 섹션별 자유로운 콘텐츠 구성
- 실시간 파일 업로드 상태 확인
- 안전한 파일 저장 및 관리 (UUID 기반 파일명 자동 생성)
```java
@PostMapping("/lectures/{lectureId}/contents")
public ResponseEntity<?> addContent(@PathVariable int lectureId, 
                                  @ModelAttribute LectureContentCreateDTO dto) {
    // 파일 업로드 보안 처리
    if (dto.getType().equals("VIDEO")) {
        String filePath = fileUploadUtil.uploadVideoFile(dto.getFile(), "videos");
        dto.setFilePath(filePath);
    }
    
    // 콘텐츠 저장
    LectureContent content = courseService.createContent(lectureId, dto);
    return ResponseEntity.ok(LectureContentResponseDTO.from(content));
}

// 퀴즈 등록
@PostMapping("/lectures/{lectureId}/quizzes")
public ResponseEntity<?> addQuizzes(@PathVariable int lectureId, 
                                  @RequestBody QuizCreateDTO dto) {
    courseService.createQuizzes(lectureId, dto.getQuizzes());
    return ResponseEntity.ok(ApiResponse.success("퀴즈가 등록되었습니다."));
}
```
```java
//파일 관리시스템
public String uploadFile(MultipartFile file, String subPath) throws IOException {
    // UUID 기반 파일명 생성
    String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    String newFilename = UUID.randomUUID().toString() + "." + extension;
    
    // 파일 저장 경로 생성
    String targetPath = Paths.get(uploadPath, subPath).toString();
    Path targetFile = Paths.get(targetPath, newFilename);
    
    // 파일 저장
    Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
    return UPLOAD_PATH + Paths.get(subPath, newFilename);
}
```
[@프론트엔드 JS 코드 보기](https://github.com/KmoocProject/mooc/blob/main/src/main/resources/templates/teacher/registLecture.html)
