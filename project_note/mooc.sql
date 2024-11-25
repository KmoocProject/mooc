-- 일반 회원 테이블: 회원 정보를 저장하는 테이블
CREATE TABLE member (
    memberId VARCHAR(50) PRIMARY KEY, -- 회원 ID
    password VARCHAR(200) NOT NULL, -- 비밀번호
    userName VARCHAR(50) NOT NULL, -- 회원 이름
    email VARCHAR(100) UNIQUE NOT NULL, -- 이메일 (고유)
    gender VARCHAR(10) NOT NULL, -- 성별
    birthYear INT NOT NULL, -- 출생 연도
    education VARCHAR(50), -- 교육 수준
    memberType VARCHAR(20) NOT NULL, -- 회원 유형 (STUDENT, CREDIT_BANK)
    status VARCHAR(20) NOT NULL, -- 회원 상태 (ACTIVE, INACTIVE, WITHDRAWN)
    credit INT DEFAULT 0, -- 학점
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 생성일
);

-- 교사 회원 테이블: 교사 정보를 저장하는 테이블
CREATE TABLE teacher (
    teacherId VARCHAR(50) PRIMARY KEY, -- 교사 ID
    password VARCHAR(200) NOT NULL, -- 비밀번호
    teacherName VARCHAR(50) NOT NULL, -- 교사 이름
    email VARCHAR(100) UNIQUE NOT NULL, -- 이메일 (고유)
    institution VARCHAR(100) NOT NULL, -- 소속 기관
    isApproved BOOLEAN DEFAULT false, -- 관리자 승인 여부
    status VARCHAR(20) NOT NULL, -- 교사 상태 (ACTIVE, INACTIVE, WITHDRAWN)
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 생성일
);

-- 관리자 테이블: 관리자 정보를 저장하는 테이블
CREATE TABLE admin (
    adminId VARCHAR(50) PRIMARY KEY, -- 관리자 ID
    password VARCHAR(200) NOT NULL, -- 비밀번호
    adminName VARCHAR(50) NOT NULL, -- 관리자 이름
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 생성일
);

-- 강좌 테이블: 강좌 정보를 저장하는 테이블 (teacherId 참조 변경)
CREATE TABLE course (
    courseId VARCHAR(50) PRIMARY KEY, -- 강좌 ID
    isCreditBank TINYINT(1) DEFAULT 0, -- 학점 은행 여부
    title VARCHAR(200) NOT NULL, -- 강좌 제목
    thumbnail VARCHAR(200), -- 썸네일 이미지
    category VARCHAR(50) NOT NULL, -- 카테고리
    institution VARCHAR(100) NOT NULL, -- 소속 기관
    weeks INT NOT NULL, -- 주 수
    enrollmentStart TIMESTAMP NOT NULL, -- 등록 시작일
    enrollmentEnd TIMESTAMP NOT NULL, -- 등록 종료일
    certificateAvailable BOOLEAN DEFAULT true, -- 인증서 제공 여부
    learningTime INT NOT NULL, -- 학습 시간
    courseStart TIMESTAMP NOT NULL, -- 강좌 시작일
    courseEnd TIMESTAMP NOT NULL, -- 강좌 종료일
    language VARCHAR(50) NOT NULL, -- 언어
    description TEXT NOT NULL, -- 강좌 설명
    teacherId VARCHAR(50) NOT NULL, -- 교사 ID
    status VARCHAR(20) NOT NULL, -- 강좌 상태 (DRAFT, PUBLISHED, DELETED)
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성일
    FOREIGN KEY (teacherId) REFERENCES teacher(teacherId) -- 교사 ID 외래 키
);

-- 강의 영역 테이블: 강의 영역 정보를 저장하는 테이블
CREATE TABLE lecture (
    lectureId VARCHAR(50) PRIMARY KEY, -- 강의 영역 ID
    title VARCHAR(200) NOT NULL, -- 강의 영역 제목
    description TEXT NOT NULL, -- 강의 영역 설명
    courseId VARCHAR(50) NOT NULL, -- 강좌 ID
    FOREIGN KEY (courseId) REFERENCES course(courseId) -- 강좌 ID 외래 키
);

-- 강의 영역 콘텐츠 테이블: 강의 영역 콘텐츠 정보를 저장하는 테이블
CREATE TABLE lectureContent (
    lectureContentId VARCHAR(50) PRIMARY KEY, -- 강의 영역 콘텐츠 ID
    title VARCHAR(200) NOT NULL, -- 강의 영역 콘텐츠 제목
    description TEXT NOT NULL, -- 강의 영역 콘텐츠 설명
    lectureId VARCHAR(50) NOT NULL, -- 강의 영역 ID
    FOREIGN KEY (lectureId) REFERENCES lecture(lectureId) -- 강의 영역 ID 외래 키
);

-- 강의 파일 테이블: 파일 정보를 저장하는 테이블
CREATE TABLE lectureFile (
    lectureFileId VARCHAR(50) PRIMARY KEY, -- 강의 파일 ID
    fileName VARCHAR(200) NOT NULL, -- 파일 이름
    filePath VARCHAR(200) NOT NULL, -- 파일 경로
    lectureContentId VARCHAR(50) NOT NULL, -- 강의 영역 콘텐츠 ID
    FOREIGN KEY (lectureContentId) REFERENCES lectureContent(lectureContentId) -- 강의 영역 콘텐츠 ID 외래 키
);

CREATE TABLE courseEnrollment (
    courseEnrollmentId VARCHAR(50) PRIMARY KEY, -- 강좌 등록 ID
    memberId VARCHAR(50) NOT NULL, -- 회원 ID
    courseId VARCHAR(50) NOT NULL, -- 강좌 ID
    FOREIGN KEY (memberId) REFERENCES member(memberId), -- 회원 ID 외래 키
    FOREIGN KEY (courseId) REFERENCES course(courseId) -- 강좌 ID 외래 키
);

CREATE TABLE notification (
    notificationId VARCHAR(50) PRIMARY KEY, -- 알림 ID
    memberId VARCHAR(50) NOT NULL, -- 회원 ID
    title VARCHAR(200) NOT NULL, -- 알림 제목
    content TEXT NOT NULL, -- 알림 내용
    isRead BOOLEAN DEFAULT false, -- 읽음 여부
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성일
    FOREIGN KEY (memberId) REFERENCES member(memberId) -- 회원 ID 외래 키
);

CREATE TABLE notice (
    noticeId VARCHAR(50) PRIMARY KEY, -- 공지사항 ID
    adminId VARCHAR(50) NOT NULL, -- 관리자 ID
    title VARCHAR(200) NOT NULL, -- 공지사항 제목
    content TEXT NOT NULL, -- 공지사항 내용
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성일
    importance TINYINT(1) NOT NULL, -- 중요도
    FOREIGN KEY (adminId) REFERENCES admin(adminId) -- 관리자 ID 외래 키
);