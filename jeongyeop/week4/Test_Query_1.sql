USE boardgame_guide;

-- 1. Member 테이블 테스트 데이터 삽입
-- (account_id는 아직 회원가입 연동 전이므로 NULL로 비워둡니다)
INSERT INTO Member (member_id, account_id, student_number, member_name, dept_name, deleted_at) VALUES 
('M001', NULL, 'B911111', '류정엽', '산업데이터공학과', NULL),
('M002', NULL, 'B911112', '김기현A', '컴퓨터공학과', NULL),
('M003', NULL, 'C111113', '노규진', '컴퓨터공학과', NULL),
('M004', NULL, 'C211114', '김준', '시각디자인과', NULL),
('M005', NULL, 'B811115', '안예슬해', '컴퓨터공학과', '2026-02-28 14:30:00'); -- 탈퇴한 회원 (소프트 딜리트)

-- 2. BoardGame 테이블 테스트 데이터 삽입
INSERT INTO BoardGame (game_id, game_name, difficulty, play_time, description) VALUES 
('G001', '아그리콜라', '어려움', 120, '농장을 경영하며 가족을 먹여 살리는 치열한 일꾼 놓기 게임입니다.'),
('G002', '카탄', '보통', 90, '자원을 채집하고 거래하여 가장 먼저 10점을 모으는 개척 게임입니다.'),
('G003', '스플렌더', '쉬움', 30, '보석 칩을 모아 발전 카드를 구매하는 엔진 빌딩 게임입니다.'),
('G004', '테라포밍 마스', '어려움', 150, '화성을 인간이 살 수 있는 환경으로 개척하는 고도 전략 게임입니다.'),
('G005', '할리갈리', '매우 쉬움', 15, '같은 과일이 5개가 되면 종을 치는 흥미진진한 순발력 게임입니다.');