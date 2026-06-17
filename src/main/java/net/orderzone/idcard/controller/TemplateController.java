package net.orderzone.idcard.controller;

import lombok.RequiredArgsConstructor;
import net.orderzone.idcard.model.Profile;
import net.orderzone.idcard.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/templates")
public class TemplateController {

    private final ProfileService profileService;

    @GetMapping("/preview/{id}")
    public String preview(
            @PathVariable Long id,
            Model model) {

        Profile profile =
                profileService.findById(id);

        model.addAttribute(
                "profile",
                profile);

        model.addAttribute(
                "qrCode",
                "/uploads/qr/profile_" + profile.getId() + ".png");

        return "preview";
    }
}