package kr.stockwaifu.repository;

import kr.stockwaifu.domain.stock.StockCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockCategoryRepository extends JpaRepository<StockCategory, Long> {
}