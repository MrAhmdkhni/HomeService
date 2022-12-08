package ir.maktab.base.service.impl;

import ir.maktab.base.entity.BaseEntity;
import ir.maktab.base.repository.BaseRepository;
import ir.maktab.base.service.BaseService;
import ir.maktab.exception.NotFoundClassException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<E extends BaseEntity<ID>, ID extends Serializable, R extends BaseRepository<E, ID>>
        implements BaseService<E, ID> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public void saveOrUpdate(E e) {
        try {
            repository.getEntityManager().getTransaction().begin();
            repository.saveOrUpdate(e);
            repository.getEntityManager().getTransaction().commit();
        } catch (Exception exception) {
            repository.getEntityManager().getTransaction().rollback();
        }
    }

    @Override
    public void delete(E e) {
        try {
            repository.getEntityManager().getTransaction().begin();
            repository.delete(e);
            repository.getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            repository.getEntityManager().getTransaction().rollback();
        }
    }

    @Override
    public Optional<E> findById(ID id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<E> findAll() {
        List<E> eList = repository.findAll();
        if (eList.isEmpty()) {
            throw new NotFoundClassException("there is no results...!!");
        }
        return repository.findAll();
    }
}
