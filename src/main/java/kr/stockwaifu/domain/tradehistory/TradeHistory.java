package kr.stockwaifu.domain.tradehistory;

import jakarta.persistence.Entity;
import kr.stockwaifu.domain.order.Order;
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
public class TradeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long executionPrice;
    private Integer executionQuantity;

    @CreatedDate
    private LocalDateTime executionDate;
}