package kr.stockwaifu.domain.asset;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import java.util.List;

//AssetRepository 구현체
@RequiredArgsConstructor
public class AssetRepositoryImpl implements AssetRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final Asset asset;

    /*
     * @Override
     * public List<Asset> findUserAssetsWithFilters(Long memberId, String stockName,
     * Boolean onlyProfitable) {
     * return queryFactory
     * .selectFrom(asset)
     * .join(asset.stock, stock).fetchJoin() // N+1 예방
     * .where(
     * asset.member.id.eq(memberId),
     * stockNameEq(stockName),
     * isProfitable(onlyProfitable))
     * .fetch();
     * }
     */

    // 동적 조건: 종목명 검색
    /*
     * private BooleanExpression stockNameEq(String stockName) {
     * return stockName != null ? stock.name.contains(stockName) : null;
     * }
     */

    // 동적 조건: 수익 중인 종목만 필터링 (현재가 > 평단가 가정)
    /*
     * private BooleanExpression isProfitable(Boolean onlyProfitable) {
     * if (onlyProfitable == null || !onlyProfitable)
     * return null;
     * // 실제로는 시세 테이블 조인이 필요하지만, 예시로 basePrice와 비교
     * return asset.averagePrice.lt(stock.basePrice);
     * }
     */
}