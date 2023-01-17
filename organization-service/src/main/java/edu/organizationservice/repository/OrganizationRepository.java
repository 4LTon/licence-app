package edu.organizationservice.repository;

import edu.organizationservice.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {
    Organization findByOrganizationId(String organizationId);
}
