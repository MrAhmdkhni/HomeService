package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entity.service.SubService;

import java.util.Optional;

public interface SubServiceRepository extends BaseRepository<SubService, Long> {

    Optional<SubService> findByName(String name);

    void deleteByName(String name);

    void editBasePriceAndDescription(String subServiceName, Long basePrice, String description);
}
