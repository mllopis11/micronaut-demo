package mike.demo.company.organization.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Singleton;

import mike.demo.company.organization.commons.exception.EntityAlreadyExistException;
import mike.demo.company.organization.model.Organization;

/**
 * @author Mike (2021-07)
 *
 */
@Singleton
class OrganizationRepositoryImpl implements OrganizationRepository {

    private final Map<String, Organization> organizations = Collections.synchronizedMap(new HashMap<>());
    
    @Override
    public Collection<Organization> findAll() {
        return organizations.values();
    }

    @Override
    public Optional<Organization> findById(String uuid) {
        return Optional.ofNullable(this.organizations.get(uuid));
    }

    /**
     * @param organization organization to create
     * @throws EntityAlreadyExistException in case of the name of the entity already exists
     */
    @Override
    public Organization add(final Organization organization) {
        
        this.findByName(organization.getName())
            .ifPresent( o -> new EntityAlreadyExistException("organization already exists"));
        
        String uuid = UUID.randomUUID().toString();
        
        organization.setUuid(uuid);
        
        this.organizations.put(organization.getUuid(), organization);
        
        return organization;
    }
}
