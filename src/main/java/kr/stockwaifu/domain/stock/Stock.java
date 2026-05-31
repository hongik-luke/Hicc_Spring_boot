package kr.stockwaifu.domain.stock;

import jakarta.persistence.Entity;
import kr.stockwaifu.domain.characterstatus.CharacterStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String tickerCode;

    private String name;
    private Long marketCap;
    private Long basePrice;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(mappedBy = "stock", cascade = CascadeType.ALL)
    private CharacterStatus characterStatus;

    @Builder
    public Stock(String tickerCode, String name, Long basePrice) {
        this.tickerCode = tickerCode;
        this.name = name;
        this.basePrice = basePrice;
    }
}