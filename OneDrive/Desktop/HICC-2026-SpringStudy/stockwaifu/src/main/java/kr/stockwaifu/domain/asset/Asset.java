package kr.stockwaifu.domain.asset;

import jakarta.persistence.Entity;
import kr.stockwaifu.domain.memeber.Member;
import kr.stockwaifu.domain.stock.Stock;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private Integer quantity;
    private Long averagePrice;

    // 연관관계 편의 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getAssets().add(this);
    }
}
