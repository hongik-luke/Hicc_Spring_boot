package kr.stockwaifu.domain.asset;

import java.util.List;

public interface AssetRepositoryCustom {
    // 사용자의 자산을 동적으로 검색 (종목명 필터, 수익률 필터 등)
    // List<Asset> findUserAssetsWithFilters(Long memberId, String stockName,
    // Boolean onlyProfitable);
}