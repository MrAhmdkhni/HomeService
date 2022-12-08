package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entity.person.Expert;
import ir.maktab.entity.person.ExpertStatus;

import java.util.Optional;

public interface ExpertRepository extends BaseRepository<Expert, Long> {

    Optional<Expert> findByEmail(String email);

    void editPassword(Expert expert, String newPassword);

    Optional<Expert> findByPhoneNumber(String phoneNumber);

    void editExpertStatus(ExpertStatus expertStatus, String phoneNumber);
}
