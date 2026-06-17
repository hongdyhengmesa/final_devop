package net.orderzone.idcard.service.impl;

import lombok.RequiredArgsConstructor;
import net.orderzone.idcard.model.Profile;
import net.orderzone.idcard.repository.ProfileRepository;
import net.orderzone.idcard.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl
        implements ProfileService {

    private final ProfileRepository repository;

    @Override
    public Profile create(Profile profile) {

        if (profile.getUuid() == null) {
            profile.setUuid(UUID.randomUUID().toString());
        }

        return repository.save(profile);
    }

    @Override
    public Profile update(
            Long id,
            Profile profile) {

        Profile existing =
                repository.findById(id)
                        .orElseThrow();

        existing.setFullName(profile.getFullName());
        existing.setDepartment(profile.getDepartment());
        existing.setEmail(profile.getEmail());
        existing.setPhone(profile.getPhone());

        return repository.save(existing);
    }

    @Override
    public Profile findById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<Profile> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}