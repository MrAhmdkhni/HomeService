package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseServiceImpl;
import ir.maktab.entity.service.MainService;
import ir.maktab.repository.MainServiceRepository;
import ir.maktab.service.MainServiceService;

import java.util.Optional;

public class MainServiceServiceImpl
        extends BaseServiceImpl<MainService, Long, MainServiceRepository>
        implements MainServiceService {

    public MainServiceServiceImpl(MainServiceRepository repository) {
        super(repository);
    }

    @Override
    public Optional<MainService> findByName(String name) {
        try {
            return repository.findByName(name);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByName(String name) {
        try {
            repository.getEntityManager().getTransaction().begin();
            repository.deleteByName(name);
            repository.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            repository.getEntityManager().getTransaction().rollback();
        }
    }
}
