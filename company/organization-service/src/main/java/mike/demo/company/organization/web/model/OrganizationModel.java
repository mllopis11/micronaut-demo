package mike.demo.company.organization.web.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;

@Introspected
@Schema(description = "Oragnization payload")
public class OrganizationModel {

    @NotBlank(message = "no such organization name provided")
    @Size(min = 5, max = 20, message = "invalid length (5 to 20 characters)")
    private String name;
    
    @NotBlank(message = "no such organization address provided")
    @Size(min = 5, max = 50, message = "invalid length (5 to 50 characters)")
    private String address;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("OrganizationModel [");

        // @formatter:off
        builder.append(", name=").append(name)
                .append(", address=").append(address);
        // @formatter:on

        return builder.append("]").toString();
    }
}
