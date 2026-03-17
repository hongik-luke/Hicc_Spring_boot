# Hicc_Spring_boot_Study

## HICC 스프링 스터디를 위한 레포입니다.

---

## 📁 저장소 정리 규칙

- `main` 브랜치에는 **각자 자신의 이름 또는 GitHub 아이디로 된 폴더**를 하나씩 생성합니다.
- 각자 폴더 내부에는 **주차별 폴더**를 만들어 학습 내용과 실습 결과를 정리합니다.
- 예시 구조는 아래와 같습니다.

```bash
main
├─ luke/
│  ├─ week1/
│  ├─ week2/
│  ├─ week3/
│  └─ week4/
├─ minsu/
│  ├─ week1/
│  ├─ week2/
│  └─ week3/
```

## 🌳 깃 브랜치 전략

### 1. 기본 브랜치 운영 규칙

- `develop` 브랜치는 **디폴트 브랜치**이자 **개발 완료된 기능이 통합되는 브랜치**입니다.
- `develop` 브랜치는 **팀장만 직접 관리 및 머지**합니다.
- 모든 팀원은 **최신 `develop` 브랜치**를 기준으로 새로운 브랜치를 생성하여 작업합니다.
- `main` 또는 `develop` 브랜치에 **직접 push하지 않습니다.**

### 2. 브랜치 생성 규칙

브랜치 명명 규칙은 아래와 같습니다.

- `feat/[이슈 번호]/[기능명]`
  - 예: `feat/123/user-login`
  - 새로운 기능 개발 시 사용
  - Label: `✨ feature`

- `refactor/[이슈 번호]/[기능명]`
  - 예: `refactor/456/user-service`
  - 코드 리팩토링 시 사용
  - Label: `♻️ refactor`

- `bug/[이슈 번호]/[기능명]`
  - 예: `bug/789/null-pointer-exception`
  - 버그 수정 시 사용
  - Label: `🐛 bug`

### 3. PR 반영 규칙

- 한 주차의 작업이 끝나면 반드시 **Pull Request**를 생성하여 반영합니다.
- PR 제목에는 **주차 / 작업 내용 / 작성자**가 드러나도록 작성합니다.

예시:
- `[Week3] DB 설계 및 ERD 작성 - luke`
- `[Week4] JPA 엔티티 매핑 및 Repository 구현 - minsu`

- PR 본문에는 아래 내용을 최소한으로 포함합니다.
  - 작업 내용
  - 주요 변경 파일
  - 실행 또는 확인 방법
  - 리뷰 포인트

---

## 🤝 PR (Pull Request) 전략

* `develop` 브랜치 PR:
    * 👑 **팀장만 승인 및 머지** 가능
* **그 외 브랜치 PR**:
    * 👥 `develop` 브랜치 외 다른 브랜치로 머지할 때는 **최소 1명 이상의 추가 승인** 후 머지 가능

---

## 🧑‍💻 네이밍 컨벤션

### 🔤 일반 네이밍 규칙

* **클래스명**: `PascalCase`(첫글자와 이어지는 단어의 첫글자를 대문자로 표기하는 방법)
    * 예: `UserService`, `ProductRepository`
* **변수명**: `camelCase`(첫단어는 소문자로 표기하지만, 이어지는 단어의 첫글자는 대문자로 표기하는 방법)
    * 예: `userName`, `orderId`, `totalAmount`
* **DB 컬럼명**: `snake_case`(모든 단어를 소문자로 표기하고, 단어를 언더바(_) 로 연결하는 방법)
    * 예: `user_name`, `product_price`, `created_at`
* **상수명**: `UPPER_CASE`(모든 단어를 대문자로 표기하고, 단어를 언더바(_) 로 연결하는 방법)
    * 예: `MAX_RETRIES`, `DEFAULT_PAGE_SIZE`

### 🧩 메서드 네이밍 규칙

* **`QueryService` 또는 `QueryFacade` 메서드**: 데이터를 조회할 때 `retrieve`로 시작합니다.
    * 예: `retrieveMeetings`, `retrieveUnreadNotifications`
* **`API` 메서드**: 데이터를 조회할 때 `fetch`로 시작합니다.
    * 예: `fetchBookBasicInfo`, `fetchMembershipInfo`
* **`Map<K, V>` 반환 메서드**: 메서드 가장 뒤에 `By[Key](s)`를 붙여 반환되는 맵의 키를 명시합니다.
    * 이때 키가 1개면 `s`를 붙이지 않고 복수면 `s`를 붙입니다.
    * 또한 조회하는 값들이 여러 개인 경우는 `s`로 복수형을 표현합니다.
        1. 하나의 키로 하나의 값을 조회하는 경우
            * 예: `retrieveMemberIdByNickname`(닉네임 1개로 해당되는 id값 1개 조회)
        2. 하나의 키로 여러 개의 값을 조회하는 경우
            * 예: `retrieveMemberIdsByNickname`(닉네임 1개로 해당되는 id값 여러 개 조회)
        3. 여러 개의 키로 여러 개의 값을 조회하는 경우
            * 예: `retrieveMemberIdsByNicknames`(닉네임 여러 개로 해당되는 id값 여러 개 조회)
* **상태 반전 메서드**: 상태를 반전시키는 메서드는 `toggle`로 시작합니다.
    * 예: `toggleLikeBookStory`

### 🎁 DTO 네이밍 규칙

* **DTO 클래스명**: 클래스 맨뒤에 `RequestDTO` 또는 `ResponseDTO`를 붙입니다.
    * 예: `BookResponseDTO`, `BookRequestDTO`
* **클래스 중첩 DTO**
    1. `Request` 또는 `Response`를 포함하지 않습니다.
        * ❌ 잘못된 예: `BookCreateRequest`, `BookResponse`
    2. `DTO`,`Dto`를 붙이지 않습니다.
        * ❌ 잘못된 예: `BookCreateDTO`, `MemberProfileDto`

    * 예: `BookCreate`, `MeetingInfo`

---

## 🏗️ 아키텍처 코딩 컨벤션

### 🔧 Service 작성 규칙

1. 불필요한 추상화를 제거하기 위해 **`Service`는 인터페이스를 사용하지 않고 클래스를 사용합니다.**
2. `Service`에는 **순수한 엔티티 조회**와 **비즈니스 로직**만 포함합니다.
   DTO 변환, 페이징 처리 등 부가 로직은 포함하지 않습니다.
3. 하나의 메서드가 **30줄**이 넘어가지 않도록 주의합니다.
4. 간단한 기능이 아닌, 복잡하거나 예외가 존재할 경우 **주석**을 작성하여 유지보수에 용이하도록 합니다.

```java

@Service
@RequiredArgsConstructor
@Transactional
public class ClubBookReviewCommandService {
    private final ClubManagementAPI clubManagementAPI;
    private final ClubMeetingQueryService clubMeetingQueryService;
    private final ClubBookReviewQueryService clubBookReviewQueryService;
    private final BookReviewRepository bookReviewRepository;

    @Retryable(
            retryFor = OptimisticLockingFailureException.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 300)
    )
    public Long updateBookReview(Long meetingId, Long reviewId, String memberId, BookReviewCreate request) {
        Meeting meeting = clubMeetingQueryService.validateMeeting(meetingId);
        Long clubMemberId = clubManagementAPI.fetchActiveClubMemberId(meeting.getClubId(), memberId);

        BookReview bookReview = clubBookReviewQueryService.validateBookReview(reviewId, meeting.getId());
        if (!bookReview.getClubMemberId().equals(clubMemberId)) {
            throw new ClubMeetingException(ClubMeetingErrorStatus.BOOK_REVIEW_FORBIDDEN);
        }

        double oldRate = bookReview.getRate();
        double newRate = request.getRate();

        bookReview.updateBookReview(
                request.getDescription(),
                request.getRate()
        );

        // 별점이 변경된 경우에만 미팅의 별점 합산
        if (oldRate != newRate) {
            meeting.subtractSumRate(oldRate);
            meeting.addSumRate(newRate);
        }

        return bookReview.getId();
    }
}
```

---

### 🪢 Facade 패턴 작성 규칙

`Facade`는 `Service`의 순수 기능을 **조합**하고 **DTO 변환**, **페이징**, **필터링** 등의 부가 로직을 담당합니다.

- 여러 `Service` 조합으로 복합적인 유스케이스 구현합니다.
- 다른 모듈과의 협력을 통해 DTO를 조합하거나 변환합니다.
- `Converter`를 통한 DTO 변환으로 일관된 변환 로직 유지합니다.

```java

@Service
@RequiredArgsConstructor
public class MemberQueryFacade {
    public static final int DEFAULT_PAGE_SIZE = 20;
    private final MemberQueryService memberQueryService;
    private final MemberFollowQueryService memberFollowQueryService;

    public List<BasicInfoWithFollow> retrieveMemberBasicInfoWithFollows(
            List<String> targetMemberIds,
            String currentMemberId
    ) {

        if (targetMemberIds == null || targetMemberIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 1. 회원 기본 정보 배치 조회
        List<MemberBasicInfoProjection> memberInfoList = memberQueryService.retrieveMemberBasicInfos(
                targetMemberIds);

        // 2. 팔로우 상태 배치 조회
        Map<String, Boolean> followStatusMap = memberFollowQueryService
                .checkFollowStatusByMemberId(currentMemberId, targetMemberIds);

        // 3. 내부 DTO로 변환
        Map<String, BasicInfoWithFollow> profileMap = memberInfoList.stream()
                .collect(Collectors.toMap(
                        MemberBasicInfoProjection::getId,
                        projection -> {
                            String memberId = projection.getId();
                            boolean isFollowing = followStatusMap.getOrDefault(memberId, false);
                            return BasicInfoWithFollow.builder()
                                    .nickname(projection.getNickName())
                                    .profileImageUrl(projection.getImgUrl())
                                    .isFollowing(isFollowing)
                                    .build();
                        }
                ));

        // 4. 순서 유지하며 반환
        return targetMemberIds.stream()
                .map(profileMap::get)
                .filter(Objects::nonNull)
                .toList();
    }
}
```

---

### 🔄 모듈 간 통신 규칙

모듈 간 데이터 교환은 반드시 `API` 또는 `이벤트`를 통해 이루어지며, 다른 모듈 `Service`나 `Repository`, `Facade`를 **절대 직접 참조하지 않습니다.**

---

### 🗃️ Entity 설계 규칙

1. 같은 모듈의 엔티티 연관관계는 JPA 매핑을 통해 영속성 관리에 용이하도록 합니다.
2. 다른 모듈의 엔티티 연관관계의 경우 타 엔티티의 **ID 필드**만을 저장하여 이를 참조합니다.

