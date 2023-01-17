package edu.organizationservice.controller;

import edu.organizationservice.model.Organization;
import edu.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/organization")
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService service;

    @GetMapping("/{organizationId}")
    public ResponseEntity<Organization> getOrganization(
            @PathVariable String organizationId) {

        return ResponseEntity.ok(service.getOrganization(organizationId));
    }

    @PostMapping("/")
    public ResponseEntity<Organization> addOrganization(
            @RequestBody Organization request) {

        return ResponseEntity.ok(service.addOrganization(request));
    }


    @PutMapping("/{organizationId}")
    public ResponseEntity<Organization> updateOrganization(
            @PathVariable String organizationId,
            @RequestBody Organization request) {

        return ResponseEntity.ok(service.updateOrganization(request));
    }

    @DeleteMapping("/{organizationId}")
    public void deleteOrganization(
            @PathVariable String organizationId) {

        service.deleteOrganization(organizationId);
    }
}
