package mike.demo.company.organization.model;

import mike.demo.company.organization.web.model.OrganizationModel;

public class OrganizationDto {

    private OrganizationDto() {}
    
    public static final Organization createOrgnizationFromModel(OrganizationModel model) {
        var organization = new Organization();
        organization.setName(model.getName().strip());
        organization.setAddress(model.getAddress().strip());
        return organization;
    }
}
