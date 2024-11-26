-- 일반 회원 테이블: 회원 정보를 저장하는 테이블
CREATE TABLE member (
    memberId VARCHAR(50) PRIMARY KEY, -- 회원 ID
    password VARCHAR(200) NOT NULL, -- 비밀번호
    userName VARCHAR(20) NOT NULL, -- 회원 이름
    email VARCHAR(100) UNIQUE NOT NULL, -- 이메일 (고유)
    gender TINYINT(1) NOT NULL, -- 성별(0 : male, 1 : female)
    memberType TINYINT(1) NOT NULL, -- 회원 유형 (0 : STUDENT, 1 : CREDIT_BANK)
    status VARCHAR(20) NOT NULL, -- 회원 상태 (ACTIVE, INACTIVE, WITHDRAWN)
    credit INT DEFAULT 0, -- 학점
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 생성일
);

-- 소속 기관 테이블: 소속 기관 정보를 저장하는 테이블
CREATE TABLE institution (
    institutionId INT PRIMARY KEY AUTO_INCREMENT, -- 소속 기관 ID
    institutionName VARCHAR(100) NOT NULL -- 소속 기관 이름
);

-- 교사 회원 테이블: 교사 정보를 저장하는 테이블
CREATE TABLE teacher (
    teacherId VARCHAR(50) PRIMARY KEY, -- 교사 ID
    password VARCHAR(200) NOT NULL, -- 비밀번호
    teacherName VARCHAR(20) NOT NULL, -- 교사 이름
    email VARCHAR(100) UNIQUE NOT NULL, -- 이메일 (고유)
    institutionId INT NOT NULL, -- 소속 기관 ID
    isApproved TINYINT(1) DEFAULT 0, -- 관리자 승인 여부
    status VARCHAR(20) NOT NULL, -- 교사 상태 (ACTIVE, INACTIVE, WITHDRAWN)
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성일
    FOREIGN KEY (institutionId) REFERENCES institution(institutionId) -- 소속 기관 ID 외래 키
);

-- 관리자 테이블: 관리자 정보를 저장하는 테이블
CREATE TABLE admin (
    adminId VARCHAR(50) PRIMARY KEY, -- 관리자 ID
    password VARCHAR(200) NOT NULL, -- 비밀번호
    adminName VARCHAR(50) NOT NULL, -- 관리자 이름
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 생성일
);

-- 과목 테이블: 과목 정보를 저장하는 테이블
CREATE subject TABLE  (
    subjectId INT PRIMARY KEY AUTO_INCREMENT, -- 과목 ID
    subjectName VARCHAR(50) NOT NULL -- 과목 이름
);

-- 강좌 테이블: 강좌 정보를 저장하는 테이블 (teacherId 참조 변경)
CREATE TABLE course (
    courseId INT PRIMARY KEY AUTO_INCREMENT, -- 강좌 ID
    viewCount INT DEFAULT 0, -- 조회수
    isCreditBank TINYINT(1) DEFAULT 0, -- 학점 은행 여부
    title VARCHAR(200) NOT NULL, -- 강좌 제목
    thumbnail VARCHAR(200), -- 썸네일 이미지
    subjectId INT NOT NULL, -- 과목 ID
    weeks INT NOT NULL, -- 주 수
    learningTime INT NOT NULL, -- 학습 시간
    language VARCHAR(50) NOT NULL, -- 언어
    description TEXT NOT NULL, -- 강좌 설명
    teacherId VARCHAR(50) NOT NULL, -- 교사 ID
    status VARCHAR(20) NOT NULL, -- 강좌 상태 (DRAFT, PUBLISHED, DELETED)
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성일
    FOREIGN KEY (teacherId) REFERENCES teacher(teacherId), -- 교사 ID 외래 키
    FOREIGN KEY (subjectId) REFERENCES subject(subjectId) -- 과목 ID 외래 키
);

-- 강의 영역 테이블: 강의 영역 정보를 저장하는 테이블
CREATE TABLE lecture (
    lectureId INT PRIMARY KEY AUTO_INCREMENT, -- 강의 영역 ID
    title VARCHAR(200) NOT NULL, -- 강의 영역 제목
    description TEXT NOT NULL, -- 강의 영역 설명
    courseId INT NOT NULL, -- 강좌 ID
    FOREIGN KEY (courseId) REFERENCES course(courseId) -- 강좌 ID 외래 키
);

-- 강의 영역 콘텐츠 테이블: 강의 영역 콘텐츠 정보를 저장하는 테이블
CREATE TABLE lectureContent (
    lectureContentId INT PRIMARY KEY AUTO_INCREMENT, -- 강의 영역 콘텐츠 ID
    title VARCHAR(200) NOT NULL, -- 강의 영역 콘텐츠 제목
    description TEXT NOT NULL, -- 강의 영역 콘텐츠 설명
    lectureId INT NOT NULL, -- 강의 영역 ID
    isVideo TINYINT(1) DEFAULT 0, -- 동영상 여부
    FOREIGN KEY (lectureId) REFERENCES lecture(lectureId) -- 강의 영역 ID 외래 키
);

-- 강의 파일 테이블: 파일 정보를 저장하는 테이블
CREATE TABLE lectureFile (
    lectureFileId INT PRIMARY KEY AUTO_INCREMENT, -- 강의 파일 ID
    fileName VARCHAR(200) NOT NULL, -- 파일 이름
    filePath VARCHAR(200) NOT NULL, -- 파일 경로
    fileExtension VARCHAR(50) NOT NULL, -- 파일 확장자
    lectureContentId INT NOT NULL, -- 강의 영역 콘텐츠 ID
    FOREIGN KEY (lectureContentId) REFERENCES lectureContent(lectureContentId) -- 강의 영역 콘텐츠 ID 외래 키
);

CREATE TABLE quiz (
    quizId INT PRIMARY KEY AUTO_INCREMENT, -- 퀴즈 ID
    question TEXT NOT NULL, -- 질문
    answer TEXT NOT NULL, -- 정답
    lectureContentId INT NOT NULL, -- 강의 영역 콘텐츠 ID
    FOREIGN KEY (lectureContentId) REFERENCES lectureContent(lectureContentId) -- 강의 영역 콘텐츠 ID 외래 키
);

-- 내 퀴즈 테이블: 내 퀴즈 정보를 저장하는 테이블
CREATE TABLE myQuizScore (
    myQuizId INT PRIMARY KEY AUTO_INCREMENT, -- 내 퀴즈 ID
    memberId VARCHAR(50) NOT NULL, -- 회원 ID
    quizId INT NOT NULL, -- 퀴즈 ID
    isCorrect TINYINT(1) DEFAULT 0, -- 정답 여부
    FOREIGN KEY (memberId) REFERENCES member(memberId), -- 회원 ID 외래 키
    FOREIGN KEY (quizId) REFERENCES quiz(quizId) -- 퀴즈 ID 외래 키
);

-- 수강신청 테이블: 수강신청 정보를 저장하는 테이블
CREATE TABLE courseEnrollment (
    courseEnrollmentId INT PRIMARY KEY AUTO_INCREMENT, -- 강좌 등록 ID
    memberId VARCHAR(50) NOT NULL, -- 회원 ID
    courseId INT NOT NULL, -- 강좌 ID
	enrollmentDate DATETIME NOT NULL DEFAULT NOW(), -- 수강신청일
	isCompleted TINYINT(1) DEFAULT 0, -- 수강완료여부(80%이상 수강시 완료)
    FOREIGN KEY (memberId) REFERENCES member(memberId), -- 회원 ID 외래 키
    FOREIGN KEY (courseId) REFERENCES course(courseId) -- 강좌 ID 외래 키
);

CREATE TABLE alert (
    alertId INT PRIMARY KEY AUTO_INCREMENT, -- 알림 ID
    memberId VARCHAR(50) NOT NULL, -- 회원 ID
    content TEXT NOT NULL, -- 알림 내용
    isRead TINYINT(1) DEFAULT 0, -- 읽음 여부
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성일
    FOREIGN KEY (memberId) REFERENCES member(memberId) -- 회원 ID 외래 키
);

CREATE TABLE notice (
    noticeId INT PRIMARY KEY AUTO_INCREMENT, -- 공지사항 ID
    adminId VARCHAR(50) NOT NULL, -- 관리자 ID
    title VARCHAR(200) NOT NULL, -- 공지사항 제목
    content TEXT NOT NULL, -- 공지사항 내용
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성일
    importance TINYINT(1) NOT NULL, -- 중요도
    FOREIGN KEY (adminId) REFERENCES admin(adminId) -- 관리자 ID 외래 키
);

-- 학습내역 테이블: 학습내역 정보를 저장하는 테이블
CREATE TABLE learningHistory (
    learningHistoryId INT PRIMARY KEY AUTO_INCREMENT, -- 학습내역 ID
    memberId VARCHAR(50) NOT NULL, -- 회원 ID
    lectureContentId INT NOT NULL, -- 강의 영역 콘텐츠 ID
    isCompleted TINYINT(1) DEFAULT 0, -- 완료 여부
    FOREIGN KEY (memberId) REFERENCES member(memberId), -- 회원 ID 외래 키  
    FOREIGN KEY (lectureContentId) REFERENCES lectureContent(lectureContentId) -- 강의 영역 콘텐츠 ID 외래 키
);

insert into subject (subjectName) values ('인문');
insert into subject (subjectName) values ('사회');
insert into subject (subjectName) values ('교육');
insert into subject (subjectName) values ('공학');
insert into subject (subjectName) values ('자연');
insert into subject (subjectName) values ('의약');
insert into subject (subjectName) values ('예체능');
insert into subject (subjectName) values ('융·복합');
insert into subject (subjectName) values ('기타');

insert into institution (institutionName) values ('가상대학교');
insert into institution (institutionName) values ('미래대학교');
insert into institution (institutionName) values ('상상대학교');
insert into institution (institutionName) values ('허구대학교');
insert into institution (institutionName) values ('신화대학교');
insert into institution (institutionName) values ('환상대학교');
insert into institution (institutionName) values ('유토피아대학교');
insert into institution (institutionName) values ('꿈꾸는대학교');
insert into institution (institutionName) values ('가상의대학교');
insert into institution (institutionName) values ('무한대학교');
insert into institution (institutionName) values ('신비대학교');
insert into institution (institutionName) values ('환상적인대학교');
insert into institution (institutionName) values ('비현실대학교');
insert into institution (institutionName) values ('상상의대학교');
