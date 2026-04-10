// 쿠폰 보상 Enum 정의
Enum RewardLevel {
  NONE [note: '보상 없음 (10점 미만)']
  REWARD_10 [note: '10점 달성 (커피 쿠폰)']
  REWARD_20 [note: '20점 달성 (햄버거 쿠폰)']
}

// 1. 회원 마스터 테이블
Table Member {
  member_id varchar [primary key, note: '회원 고유 식별자 (이름 또는 학번)']
  member_name varchar [note: '회원 이름']
  total_score int [default: 0, note: '누적 획득 점수']
  reward_status RewardLevel [default: 'NONE', note: '쿠폰 보상 달성 단계']
}

// 2. 보드게임 정식 스펙 테이블
Table BoardGame {
  game_id varchar [primary key, note: '게임 고유 번호 (예: G001)']
  official_name varchar [note: '공식 풀 네임 (예: 아그리콜라)']
  difficulty varchar [note: '난이도']
  reward_point int [note: '인증 시 획득 점수 (1~5점)']
}

// 3. 별칭 및 동의어 사전 테이블
Table GameAlias {
  alias_id int [primary key, increment, note: '별칭 고유 번호']
  game_id varchar [note: '게임 고유 번호 참조']
  alias_name varchar [note: '실제 입력하는 줄임말(별칭) (예: 아콜, 가이아)']
}

// 4. 최종 인증 완료 로그 (유효 인증 데이터)
Table PlayLog {
  log_id int [primary key, increment, note: '로그 고유 번호']
  member_id varchar [note: '인증을 진행한 회원']
  game_id varchar [note: '최종 매핑된 정식 게임']
  auth_time datetime(6) [note: '텍스트 인증 시간']
}

// 5. 미확인 로그 (예외 처리/사각지대 데이터) (관계성X)
Table UnidentifiedLog {
  log_id int [primary key, increment]
  auth_time datetime(6) [note: '메시지 전송 시간']
  sender_name varchar [note: '카톡을 보낸 사람']
  original_message text [note: '원본 텍스트 메시지']
  fail_reason varchar [note: '실패 사유 (사진 누락, 게임명 미인식 등)']
}

// --- 테이블 간의 관계(Relationships) 정의 ---

// 하나의 정식 게임(BoardGame)은 여러 개의 별칭(GameAlias)을 가질 수 있습니다 (1:N)
Ref: GameAlias.game_id > BoardGame.game_id

// 한 명의 회원(Member)은 여러 번의 인증 기록(PlayLog)을 가질 수 있습니다 (1:N)
Ref: PlayLog.member_id > Member.member_id

// 하나의 정식 게임(BoardGame)은 여러 번 인증(PlayLog)될 수 있습니다 (1:N)
Ref: PlayLog.game_id > BoardGame.game_id