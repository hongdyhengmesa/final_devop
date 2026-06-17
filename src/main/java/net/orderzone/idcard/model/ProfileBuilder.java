package net.orderzone.idcard.model;

import java.time.LocalDate;
import java.util.UUID;

public class ProfileBuilder {

    private ProfileBuilder() {
    }

    public static Profile buildDefault(
            String fullName,
            ProfileType type) {

        return Profile.builder()
                .uuid(UUID.randomUUID().toString())
                .fullName(fullName)
                .type(type)
                .issueDate(LocalDate.now())
                .barcodeType(BarcodeType.CODE_128)
                .build();
    }
}