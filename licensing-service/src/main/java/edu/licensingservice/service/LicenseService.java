package edu.licensingservice.service;

import edu.licensingservice.model.License;
import edu.licensingservice.model.Organization;
import edu.licensingservice.repository.LicenseRepository;
import edu.licensingservice.service.client.OrganizationFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;

    private final OrganizationFeignClient organizationFeignClient;

//    private final ServiceConfig config;

    private Organization retrieveOrganizationInfo(String organizationId) {

        return organizationFeignClient.getOrganization(organizationId);
    }

    public License getLicense(String licenseId, String organizationId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        if (license == null) {
            throw new IllegalArgumentException(String.format(
                    "Не удалось получить ленецзию с id: %s для организации с id: %s",
                    licenseId, organizationId));
        }

        Organization organization = retrieveOrganizationInfo(organizationId);

        // TODO: обработать исключения
        if (organization != null) {
            license.setOrganizationName(organization.getName());
            license.setContactName(organization.getContactName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactPhone(organization.getContactPhone());
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
