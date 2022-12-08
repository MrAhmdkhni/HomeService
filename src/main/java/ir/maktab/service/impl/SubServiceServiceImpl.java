package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseServiceImpl;
import ir.maktab.entity.service.SubService;
import ir.maktab.repository.SubServiceRepository;
import ir.maktab.service.SubServiceService;

import java.util.Optional;

public class SubServiceServiceImpl
        extends BaseServiceImpl<SubService, Long, SubServiceRepository>
        implements SubServiceService {

    public SubServiceServiceImpl(SubServiceRepository repository) {
        super(repository);
    }

    @Override
    public Optional<SubService> findByName(String name) {
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

    @Override
    public void editBasePriceAndDescription(String subServiceName, Long basePrice, String description) {
        try {
            repository.getEntityManager().getTransaction().begin();
            repository.editBasePriceAndDescription(subServiceName, basePrice, description);
            repository.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            repository.getEntityManager().getTransaction().rollback();
        }
    }
}
