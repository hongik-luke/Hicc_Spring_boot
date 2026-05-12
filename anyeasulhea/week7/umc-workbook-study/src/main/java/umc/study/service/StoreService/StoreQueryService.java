package umc.study.service.StoreService;

import java.util.List;
import java.util.Optional;

import umc.study.domain.Store;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);

    // List<Store> findStoresByNameAndScore(String name, Float score);
}