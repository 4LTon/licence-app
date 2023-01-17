package edu.licensingservice.controller;

import edu.licensingservice.model.License;
import edu.licensingservice.service.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/organization/{organizationId}/license")
@AllArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable String licenseId,
            @PathVariable String organizationId) {

        License license = licenseService.getLicense(licenseId, organizationId);

        return ResponseEntity.ok(license);
    }

    @PostMapping("/")
    public ResponseEntity<License> createLicense(
            @RequestBody License request) {

        return ResponseEntity.ok(licenseService.createLicense(request));
    }

    @PutMapping("/")
    public ResponseEntity<License> updateLicense(
            @RequestBody License request) {

        return ResponseEntity.ok(licenseService.updateLicense(request));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable String licenseId) {

        return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
    }
}
