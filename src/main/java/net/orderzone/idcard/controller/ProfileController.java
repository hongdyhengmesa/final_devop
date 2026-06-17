package net.orderzone.idcard.controller;

import lombok.RequiredArgsConstructor;
import net.orderzone.idcard.model.Profile;
import net.orderzone.idcard.repository.ProfileRepository;
import net.orderzone.idcard.service.FileStorageService;
import net.orderzone.idcard.service.ProfileService;
import net.orderzone.idcard.util.BarcodeUtil;
import net.orderzone.idcard.util.PDFUtil;
import net.orderzone.idcard.util.QRCodeUtil;

import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final FileStorageService fileStorageService;
    private final ProfileRepository profileRepository;

    @GetMapping
    public List<Profile> getAll() {
        return profileService.findAll();
    }

    @GetMapping("/{id}")
    public Profile getById(@PathVariable Long id) {
        return profileService.findById(id);
    }

    @PostMapping
    public Profile create(@RequestBody Profile profile) {
        return profileService.create(profile);
    }

    @PutMapping("/{id}")
    public Profile update(
            @PathVariable Long id,
            @RequestBody Profile profile) {

        return profileService.update(id, profile);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        profileService.delete(id);
    }

    @PostMapping("/{id}/photo")
public Profile uploadPhoto(
        @PathVariable Long id,
        @RequestParam("file")
        MultipartFile file)
        throws Exception {

    Profile profile =
            profileRepository
            .findById(id)
            .orElseThrow();

    String fileName =
            fileStorageService.save(file);

    profile.setPhotoFileName(fileName);
    profile.setPhotoContentType(
            file.getContentType());

    return profileRepository.save(profile);
    }

    @GetMapping("/{id}/qrcode")
public String generateQR(
        @PathVariable Long id)
        throws Exception {

    Profile profile =
            profileService.findById(id);

    String content =
            profile.getUuid()
            + "\n"
            + profile.getRegistrationNumber()
            + "\n"
            + profile.getFullName();

    return QRCodeUtil.generateQRCode(
            content,
            "profile_" + id + ".png");
}

@GetMapping("/{id}/barcode")
@ResponseBody
public String barcode(
        @PathVariable Long id)
        throws Exception {

    Profile profile =
            profileService.findById(id);

    return BarcodeUtil.generateBarcode(
            profile.getRegistrationNumber(),
            "profile_" + id + ".png");
}

@GetMapping("/{id}/pdf")
public ResponseEntity<Resource> pdf(
        @PathVariable Long id)
        throws Exception {

    Profile profile =
            profileService.findById(id);

    String fileName =
            PDFUtil.generate(profile);

    Path path =
            Paths.get("uploads", fileName);

    Resource resource =
            new UrlResource(path.toUri());

    return ResponseEntity.ok()
            .header(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=" + fileName)
            .contentType(
                    MediaType.APPLICATION_PDF)
            .body(resource);
}


}