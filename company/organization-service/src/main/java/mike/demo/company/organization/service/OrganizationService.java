package mike.demo.company.organization.service;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mike.demo.company.organization.model.Organization;
import mike.demo.company.organization.model.OrganizationDto;
import mike.demo.company.organization.repository.OrganizationRepository;
import mike.demo.company.organization.web.model.OrganizationModel;

@Singleton
public class OrganizationService {

    private static final Logger log = LoggerFactory.getLogger(OrganizationService.class);
    
    private final OrganizationRepository organizationRepository; 
    
    @Inject
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }
    
    public Collection<Organization> findAll() {
        Collection<Organization> organizations = this.organizationRepository.findAll();
        
        log.info("Organization::findAll: returned entities: {}", organizations.size());
        
        return organizations;
    }
    
    public Optional<Organization> findById(String uuid) {
        return this.organizationRepository.findById(uuid);
    }
    
    public Organization create(OrganizationModel model) {
        var organization = OrganizationDto.createOrgnizationFromModel(model);
        return this.organizationRepository.add(organization);
    }
}
