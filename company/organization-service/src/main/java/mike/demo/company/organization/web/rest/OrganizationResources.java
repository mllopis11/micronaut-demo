package mike.demo.company.organization.web.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import mike.demo.company.organization.commons.exception.EntityNotFoundProblem;
import mike.demo.company.organization.model.Organization;
import mike.demo.company.organization.service.OrganizationService;
import mike.demo.company.organization.web.model.OrganizationModel;

@Controller("/organizations")
public class OrganizationResources {

    private static final Logger log = LoggerFactory.getLogger(OrganizationResources.class);
    
    private static final String UUID_REGEXP = "[0-9a-z\\-]{36}";
    private static final String UUID_MESSAGE = "invalid UUID format";
    
    private final OrganizationService service;
    
    @Inject
    public OrganizationResources(OrganizationService service) {
        this.service = service;
    }
    
    @Get
    public Collection<Organization> getAll() {
        log.info("Organization::find: all");
        return this.service.findAll();
    }
    
    @Get("/{uuid}")
    public Organization getById(@Pattern(regexp = UUID_REGEXP, message = UUID_MESSAGE) String uuid) {
        
        log.info("Organization::findById: uuid={}", uuid);
        return this.service.findById(uuid).orElseThrow( () -> new EntityNotFoundProblem(uuid));
    }
    
    @Post
    public Organization add(@Valid @Body OrganizationModel organizationModel) {
        log.info("Organization::create: {}", organizationModel);
        return this.service.create(organizationModel);
    }

    /*
    @Get("/{uuid}/departments")
    public Organization getByIdWithDepartments(
            @Pattern(regexp = "[0-9a-z\\-]{36}", message = "invalid UUID format") 
            String uuid) {
        log.info("Organization::findWithDepartements: uuid={}", uuid);
        Organization organization = repository.findById(id);
        organization.setDepartments(departmentClient.findByOrganization(organization.getId()));
        return organization;
    }
    
    @Get("/{uuid}/departments/employees")
    public Organization getByIdWithDepartmentsAndEmployees(
            @Pattern(regexp = "[0-9a-z\\-]{36}", message = "invalid UUID format") 
            String uuid) {
        log.info("Organizatio::findWithDepartementsAndEmployees: uuid={}", uuid);
        Organization organization = repository.findById(id);
        organization.setDepartments(departmentClient.findByOrganizationWithEmployees(organization.getId()));
        return organization;
    }
    
    @Get("/{uuid}/employees")
    public Organization getByIdWithEmployees(
            @Pattern(regexp = "[0-9a-z\\-]{36}", message = "invalid UUID format") 
            String uuid) {
        log.info("Organization::findWithEmployees: uuid={}", uuid);
        Organization organization = repository.findById(id);
        organization.setEmployees(employeeClient.findByOrganization(organization.getId()));
        return organization;
    }
    */
}
