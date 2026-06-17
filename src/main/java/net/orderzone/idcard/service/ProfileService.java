package net.orderzone.idcard.service;

import net.orderzone.idcard.model.Profile;

import java.util.List;

public interface ProfileService {

    Profile create(Profile profile);

    Profile update(Long id, Profile profile);

    Profile findById(Long id);

    List<Profile> findAll();

    void delete(Long id);
}