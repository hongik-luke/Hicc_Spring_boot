-- 1. 실습용 데이터베이스 생성 및 선택
CREATE DATABASE IF NOT EXISTS boardgame_guide DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE boardgame_guide;

-- =========================================
-- 1단계: 마스터 테이블 생성 (가장 먼저 생성)
-- =========================================

CREATE TABLE Member (
    member_id VARCHAR(50) PRIMARY KEY COMMENT '고유 회원 번호 (예: M001)',
    account_id CHAR(36) COMMENT 'Supabase Auth User ID',
    student_number VARCHAR(50) UNIQUE COMMENT '학번',
    member_name VARCHAR(100) NOT NULL COMMENT '이름 (동명이인 식별자 포함)',
    dept_name VARCHAR(100) COMMENT '학과',
    deleted_at DATETIME COMMENT '소프트 딜리트 (NULL이면 활동 중)'
);

CREATE TABLE Semester (
    semester_id VARCHAR(50) PRIMARY KEY COMMENT '학기 식별자 (예: 26-1)',
    start_date DATE COMMENT '학기 시작일',
    end_date DATE COMMENT '학기 종료일',
    is_current BOOLEAN COMMENT '현재 진행 중인 학기 여부'
);

CREATE TABLE BoardGame (
    game_id VARCHAR(50) PRIMARY KEY COMMENT '게임 ID (예: G001)',
    game_name VARCHAR(200) NOT NULL COMMENT '보드게임 정식 명칭',
    difficulty VARCHAR(50) COMMENT '기본 난이도',
    play_time INT COMMENT '예상 플레이 시간 (분)',
    description TEXT COMMENT '게임 설명 (퍼블릭 뷰용)'
);

-- =========================================
-- 2단계: 연관 테이블 생성 (마스터를 참조함)
-- =========================================

CREATE TABLE SemesterMember (
    semester_id VARCHAR(50),
    member_id VARCHAR(50),
    total_score INT DEFAULT 0 COMMENT '해당 학기 누적 점수',
    last_play_at DATETIME COMMENT '해당 학기 마지막 인증 시간',
    PRIMARY KEY (semester_id, member_id),
    FOREIGN KEY (semester_id) REFERENCES Semester(semester_id),
    FOREIGN KEY (member_id) REFERENCES Member(member_id)
);

CREATE TABLE SemesterEventGame (
    semester_id VARCHAR(50),
    game_id VARCHAR(50),
    reward_point INT COMMENT '해당 학기 플레이 시 부여 포인트',
    PRIMARY KEY (semester_id, game_id),
    FOREIGN KEY (semester_id) REFERENCES Semester(semester_id),
    FOREIGN KEY (game_id) REFERENCES BoardGame(game_id)
);

CREATE TABLE GameAlias (
    alias_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '자동 증가 고유 번호',
    game_id VARCHAR(50),
    alias_name VARCHAR(100) NOT NULL COMMENT '채팅방 별칭 (예: 아콜)',
    FOREIGN KEY (game_id) REFERENCES BoardGame(game_id)
);

CREATE TABLE Inventory (
    item_id VARCHAR(50) PRIMARY KEY COMMENT '개별 박스 관리번호 (예: I001)',
    game_id VARCHAR(50),
    owner_type VARCHAR(50) COMMENT '소유자 분류 (CLUB / PERSONAL)',
    status VARCHAR(50) COMMENT '상태 (AVAILABLE / RENTED / LOST)',
    FOREIGN KEY (game_id) REFERENCES BoardGame(game_id)
);

-- =========================================
-- 3단계: 트랜잭션 및 로그 테이블 생성
-- =========================================

CREATE TABLE RentalLog (
    rental_id INT AUTO_INCREMENT PRIMARY KEY,
    item_id VARCHAR(50),
    member_id VARCHAR(50),
    borrowed_at DATETIME COMMENT '대여 일시',
    due_date DATETIME COMMENT '반납 예정일',
    returned_at DATETIME COMMENT '실제 반납 일시',
    status VARCHAR(50) COMMENT '상태 (ACTIVE / RETURNED / OVERDUE)',
    FOREIGN KEY (item_id) REFERENCES Inventory(item_id),
    FOREIGN KEY (member_id) REFERENCES Member(member_id)
);

CREATE TABLE PlayLog (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    semester_id VARCHAR(50),
    member_id VARCHAR(50),
    game_id VARCHAR(50),
    auth_time DATETIME COMMENT '카카오톡 인증 시간',
    is_verified BOOLEAN DEFAULT FALSE COMMENT '관리자 검증 여부',
    FOREIGN KEY (semester_id) REFERENCES Semester(semester_id),
    FOREIGN KEY (member_id) REFERENCES Member(member_id),
    FOREIGN KEY (game_id) REFERENCES BoardGame(game_id)
);

CREATE TABLE UnidentifiedLog (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    auth_time DATETIME,
    sender_name VARCHAR(100) COMMENT '카카오톡 발송자 닉네임',
    original_message TEXT COMMENT '문제가 발생한 원본 메시지',
    fail_reason VARCHAR(255) COMMENT '반려 사유',
    resolved_at DATETIME COMMENT '관리자 처리 일시'
);