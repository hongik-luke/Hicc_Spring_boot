package kr.stockwaifu.domain.characterstatus;

import jakarta.persistence.Entity;
import kr.stockwaifu.domain.characterlog.CharacterLog;
import kr.stockwaifu.domain.stock.Stock;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private Integer affinityLevel = 1;
    private String outfitUrl;
    private String expressionType;

    @OneToMany(mappedBy = "characterStatus")
    private List<CharacterLog> logs = new ArrayList<>();
}