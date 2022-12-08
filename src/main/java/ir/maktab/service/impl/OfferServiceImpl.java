package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseServiceImpl;
import ir.maktab.entity.offer.Offer;
import ir.maktab.repository.OfferRepository;
import ir.maktab.service.OfferService;

public class OfferServiceImpl
        extends BaseServiceImpl<Offer, Long, OfferRepository>
        implements OfferService {

    public OfferServiceImpl(OfferRepository repository) {
        super(repository);
    }
}
