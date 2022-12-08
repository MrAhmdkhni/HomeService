package ir.maktab.service;

import ir.maktab.base.service.BaseService;
import ir.maktab.entity.person.Admin;
import ir.maktab.entity.person.Expert;
import ir.maktab.entity.person.ExpertStatus;
import ir.maktab.entity.service.MainService;
import ir.maktab.entity.service.SubService;

import java.util.List;

public interface AdminService extends BaseService<Admin,Long> {

    void addMainService(String name);

    void deleteMainService(String name);

    void addSubService(String mainServiceName, String subServiceName, Long basePrice, String description);

    void deleteSubService(String name);

    void addExpertToSubService(String subServiceName, String phoneNumber);

    void deleteExpertFromSubService(String subServiceName, String phoneNumber);

    List<MainService> findAllMainService();

    List<SubService> findAllSubService();

    void editBasePriceAndDescription(String subServiceName, Long basePrice, String description);

    List<Expert> findAllExpert();

    void changeExpertStatus(String phoneNumber, ExpertStatus expertStatus);
}
