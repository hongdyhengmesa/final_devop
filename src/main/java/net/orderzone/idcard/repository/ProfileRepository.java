package net.orderzone.idcard.repository;

import net.orderzone.idcard.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository
        extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUuid(String uuid);

    Optional<Profile> findByRegistrationNumber(
            String registrationNumber);

    boolean existsByUuid(String uuid);

    boolean existsByRegistrationNumber(
            String registrationNumber);
}