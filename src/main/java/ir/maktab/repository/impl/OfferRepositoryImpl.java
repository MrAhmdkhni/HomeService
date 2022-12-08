package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entity.offer.Offer;
import ir.maktab.repository.OfferRepository;
import jakarta.persistence.EntityManager;

public class OfferRepositoryImpl extends BaseRepositoryImpl<Offer, Long> implements OfferRepository {

    public OfferRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Offer> getEntityClass() {
        return Offer.class;
    }
}
