package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entity.service.MainService;

import java.util.Optional;

public interface MainServiceRepository extends BaseRepository<MainService, Long> {

    Optional<MainService> findByName(String name);

    void deleteByName(String name);
}
