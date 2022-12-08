package ir.maktab.base.repository.impl;

import ir.maktab.base.entity.BaseEntity;
import ir.maktab.base.repository.BaseRepository;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable> implements BaseRepository<E, ID> {

    protected final EntityManager em;

    public BaseRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public abstract Class<E> getEntityClass();

    @Override
    public void saveOrUpdate(E entity) {
        if (entity.isNew()) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    @Override
    public void delete(E e) {
        em.remove(e);
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.ofNullable(em.find(getEntityClass(), id));
    }

    @Override
    public List<E> findAll() {
        return em
                .createQuery("from " + getEntityClass().getSimpleName(), getEntityClass())
                .getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
