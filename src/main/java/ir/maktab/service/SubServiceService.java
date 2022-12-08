package ir.maktab.service;

import ir.maktab.base.service.BaseService;
import ir.maktab.entity.service.SubService;

import java.util.Optional;

public interface SubServiceService extends BaseService<SubService, Long> {

    Optional<SubService> findByName(String name);

    void deleteByName(String name);

    void editBasePriceAndDescription(String subServiceName, Long basePrice, String description);
}
