package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseServiceImpl;
import ir.maktab.entity.person.Admin;
import ir.maktab.entity.person.Expert;
import ir.maktab.entity.person.ExpertStatus;
import ir.maktab.entity.service.MainService;
import ir.maktab.entity.service.SubService;
import ir.maktab.exception.*;
import ir.maktab.repository.AdminRepository;
import ir.maktab.service.AdminService;
import ir.maktab.util.AppContext;

import java.util.List;
import java.util.Optional;

public class AdminServiceImpl
        extends BaseServiceImpl<Admin, Long, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    @Override
    public void addMainService(String name) {
        if(AppContext.getMainServiceService().findByName(name).isPresent()) {
            throw new MainServiceIsExistException("this main service already exist!");
        }
        MainService mainService = new MainService(name);
        AppContext.getMainServiceService().saveOrUpdate(mainService);
    }

    @Override
    public void deleteMainService(String name) {
        if(AppContext.getMainServiceService().findByName(name).isEmpty()) {
            throw new MainServiceIsNotExistException("this main service dose not exist!");
        }
        AppContext.getMainServiceService().deleteByName(name);
    }

    @Override
    public void addSubService(String mainServiceName,
                              String subServiceName, Long basePrice, String description) {
        Optional<MainService> mainService = AppContext.getMainServiceService().findByName(mainServiceName);
        if(mainService.isEmpty()) {
            throw new MainServiceIsNotExistException("this main service dose not exist!");
        } else if(AppContext.getSubServiceService().findByName(subServiceName).isPresent()) {
            throw new SubServiceIsExistException("this sub-service already exist!");
        }
        SubService subService = new SubService(subServiceName, basePrice, description, mainService.get());
        AppContext.getSubServiceService().saveOrUpdate(subService);
    }

    @Override
    public void deleteSubService(String name) {
        if(AppContext.getSubServiceService().findByName(name).isEmpty()) {
            throw new SubServiceIsNotExistException("this sub-service dose not exist!");
        }
        AppContext.getSubServiceService().deleteByName(name);
    }

    @Override
    public void addExpertToSubService(String subServiceName, String phoneNumber) {
        Optional<SubService> subService = AppContext.getSubServiceService().findByName(subServiceName);
        if(subService.isEmpty()) {
            throw new SubServiceIsNotExistException("this sub-service dose not exist!");
        }
        Optional<Expert> expert = AppContext.getExpertService().findByPhoneNumber(phoneNumber);
        if(expert.isEmpty()) {
            throw new ExpertIsNotExistException("this expert does not exist!");
        } else if (!expert.get().getExpertStatus().equals(ExpertStatus.CONFIRMED)) {
            throw new ExpertNoAccessException("the status of expert is not \"confirmed\"");
        }
        expert.get().addSubService(subService.get());
        AppContext.getExpertService().saveOrUpdate(expert.get());
    }

    @Override
    public void deleteExpertFromSubService(String subServiceName, String phoneNumber) {
        Optional<SubService> subService = AppContext.getSubServiceService().findByName(subServiceName);
        if(subService.isEmpty()) {
            throw new SubServiceIsNotExistException("this sub-service dose not exist!");
        }
        Optional<Expert> expert = AppContext.getExpertService().findByPhoneNumber(phoneNumber);
        if(expert.isEmpty()) {
            throw new ExpertIsNotExistException("this expert does not exist!");
        }
        expert.get().deleteSubService(subService.get());
        AppContext.getExpertService().saveOrUpdate(expert.get());
    }

    @Override
    public List<MainService> findAllMainService() {
        return AppContext.getMainServiceService().findAll();
    }

    @Override
    public List<SubService> findAllSubService() {
        return AppContext.getSubServiceService().findAll();
    }

    @Override
    public void editBasePriceAndDescription(String subServiceName, Long basePrice, String description) {
        if(AppContext.getSubServiceService().findByName(subServiceName).isEmpty()) {
            throw new SubServiceIsNotExistException("this sub-service dose not exist!");
        }
        AppContext.getSubServiceService().editBasePriceAndDescription(subServiceName, basePrice, description);
    }

    @Override
    public List<Expert> findAllExpert() {
        return AppContext.getExpertService().findAll();
    }

    @Override
    public void changeExpertStatus(String phoneNumber, ExpertStatus expertStatus) {
        Optional<Expert> expert = AppContext.getExpertService().findByPhoneNumber(phoneNumber);
        if(expert.isEmpty()) {
            throw new ExpertIsNotExistException("this expert does not exist!");
        }
        AppContext.getExpertService().editExpertStatus(expertStatus, phoneNumber);
    }


}
