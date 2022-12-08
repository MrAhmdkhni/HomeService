package ir.maktab.service;

import ir.maktab.base.service.BaseService;
import ir.maktab.entity.service.MainService;

import java.util.Optional;

public interface MainServiceService extends BaseService<MainService, Long> {

    Optional<MainService> findByName(String name);

    void deleteByName(String name);
}
