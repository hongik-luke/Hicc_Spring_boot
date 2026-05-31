package kr.stockwaifu.domain.characterlog;

import jakarta.persistence.Entity;
import kr.stockwaifu.domain.characterstatus.CharacterStatus;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_status_id")
    private CharacterStatus characterStatus;

    private String changeReason;
    private String prevStatus;
    private String newStatus;

    @CreatedDate
    private LocalDateTime createdAt;
}