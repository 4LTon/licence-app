package edu.organizationservice.service;

import edu.organizationservice.model.Organization;
import edu.organizationservice.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrganizationService {

    private final OrganizationRepository repository;

    public Organization getOrganization(String organizationId) {
        Organization organization = repository.findByOrganizationId(organizationId);

        if (organization == null) {
            throw new IllegalArgumentException(String.format(
                    "Не удалось получить организацию по указанному Id: %s",
                    organizationId));
        }

        return organization;
    }

    public Organization addOrganization(Organization organization) {
        organization.setOrganizationId(UUID.randomUUID().toString());

        return repository.save(organization);
    }

    public Organization updateOrganization(Organization organization) {
        return repository.save(organization);
    }

    public void deleteOrganization(String organizationId) {
        repository.deleteById(organizationId);
    }
}
