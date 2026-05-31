package kr.stockwaifu.domain.order;

import jakarta.persistence.Entity;
import kr.stockwaifu.domain.memeber.Member;
import kr.stockwaifu.domain.stock.Stock;
import lombok.Builder;
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
@Table(name = "orders") // order는 예약어인 경우가 많음
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Enumerated(EnumType.STRING)
    private OrderType orderType; // BUY, SELL

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, COMPLETED, CANCELLED

    private Long price;
    private Integer quantity;

    @CreatedDate
    private LocalDateTime createdAt;
}

// enum들 정의
enum OrderType {
    BUY, SELL
}

enum OrderStatus {
    PENDING, COMPLETED, CANCELLED
}