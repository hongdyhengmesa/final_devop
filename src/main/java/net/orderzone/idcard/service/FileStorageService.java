package net.orderzone.idcard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.upload-dir}")
    private String uploadDir;

    public String save(MultipartFile file)
            throws IOException {

        String filename =
                UUID.randomUUID()
                + "_"
                + file.getOriginalFilename();

        Path path =
                Paths.get(uploadDir, filename);

        Files.copy(
                file.getInputStream(),
                path,
                StandardCopyOption.REPLACE_EXISTING
        );

        return filename;
    }
}