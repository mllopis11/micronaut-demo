package mike.demo.company.organization.repository;

import java.util.Collection;
import java.util.Optional;

import mike.demo.company.organization.model.Organization;

public interface OrganizationRepository {

    Collection<Organization> findAll();
    
    Optional<Organization> findById(String uuid);
    
    default Optional<Organization> findByName(String name) {
        return this.findAll().stream()
                .filter( o -> o.getName().equalsIgnoreCase(name))
                .findFirst();
    }
    
    Organization add(Organization organization);
    
}
