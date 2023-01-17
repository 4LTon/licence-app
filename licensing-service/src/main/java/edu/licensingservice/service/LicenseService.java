package edu.licensingservice.service;

import edu.licensingservice.model.License;
import edu.licensingservice.repository.LicenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;

//    private final ServiceConfig config;

    public License getLicense(String licenseId, String organizationId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        if (license == null) {
            throw new IllegalArgumentException(String.format(
                    "Не удалось получить ленецзию с id: %s для организации с id: %s",
                    licenseId, organizationId));
        }

        return license;
    }

    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);

        return license;
    }

    public License updateLicense(License license) {
        licenseRepository.save(license);

        return license;
    }

    public String deleteLicense(String licenseId) {
        License license = new License();
        license.setLicenseId(licenseId);

        licenseRepository.delete(license);

        return String.format(
                "Deleting license with id %s",
                licenseId);
    }
}
