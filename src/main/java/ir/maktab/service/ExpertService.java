package ir.maktab.service;

import ir.maktab.base.service.BaseService;
import ir.maktab.entity.offer.Offer;
import ir.maktab.entity.person.Expert;
import ir.maktab.entity.person.ExpertStatus;

import java.io.File;
import java.util.Optional;

public interface ExpertService extends BaseService<Expert, Long> {

    void signUp(Expert expert, File image);

    Optional<Expert> findByEmail(String email);

    void editPassword(Expert expert, String newPassword);

    Optional<Expert> findByPhoneNumber(String phoneNumber);

    void editExpertStatus(ExpertStatus expertStatus, String phoneNumber);

    void addOfferForOrder(Expert expert, Long orderId, Offer offer);
}