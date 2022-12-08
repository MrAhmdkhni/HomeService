package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseServiceImpl;
import ir.maktab.entity.offer.Offer;
import ir.maktab.entity.person.Expert;
import ir.maktab.entity.person.ExpertStatus;
import ir.maktab.exception.*;
import ir.maktab.repository.ExpertRepository;
import ir.maktab.service.ExpertService;
import ir.maktab.util.AppContext;
import ir.maktab.util.Validation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class ExpertServiceImpl
        extends BaseServiceImpl<Expert, Long, ExpertRepository>
        implements ExpertService {

    public ExpertServiceImpl(ExpertRepository repository) {
        super(repository);
    }

    @Override
    public void signUp(Expert expert, File image) {
        if (findByPhoneNumber(expert.getPhoneNumber()).isPresent())
            throw new DuplicateMobileException("this mobile number already exist!");
        else if (findByEmail(expert.getEmail()).isPresent())
            throw new DuplicateEmailException("this email number already exist!");
        Validation.checkPhoneNumberAndEmailAndPassword(expert);

        byte[] byteFile = new byte[(int) image.length()];
        String [] path = image.getPath().split("\\.");
        if (!path[path.length - 1].equalsIgnoreCase("JPG"))
            throw new ImageFormatException("the format of the image is incorrect!");
        try {
            if (Files.size(Paths.get(image.getPath())) > 300000)
                throw new ImageSizeException("the size of the image is bigger than 300kilo byte!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        expert.setImage(byteFile);
        expert.setScore(0);
        expert.setExpertStatus(ExpertStatus.NEW);
        expert.setCredit(0L);
        saveOrUpdate(expert);
    }

    @Override
    public Optional<Expert> findByEmail(String email) {
        try {
            return repository.findByEmail(email);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void editPassword(Expert expert, String newPassword) {
        try {
            repository.getEntityManager().getTransaction().begin();
            repository.editPassword(expert, newPassword);
            repository.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            repository.getEntityManager().getTransaction().rollback();
        }
    }

    @Override
    public Optional<Expert> findByPhoneNumber(String phoneNumber) {
        try {
            return repository.findByPhoneNumber(phoneNumber);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void editExpertStatus(ExpertStatus expertStatus, String phoneNumber) {
        try {
            repository.getEntityManager().getTransaction().begin();
            repository.editExpertStatus(expertStatus, phoneNumber);
            repository.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            repository.getEntityManager().getTransaction().rollback();
        }
    }

    @Override
    public void addOfferForOrder(Expert expert, Long orderId, Offer offer) {
        if (!expert.getExpertStatus().equals(ExpertStatus.CONFIRMED)) {
            throw new ExpertNoAccessException("the status of expert is not \"confirmed\"");
        }else if (AppContext.getExpertService().findByPhoneNumber(expert.getPhoneNumber()).isEmpty()) {
            throw new ExpertIsNotExistException("this expert does not exist!");
        } else if (AppContext.getOrderService().findById(orderId).isEmpty()) {
            throw new OrderIsNotExistException("there is no order!");
        }
        offer.setIsAccept(false);
        AppContext.getOfferService().saveOrUpdate(offer);
    }


}
