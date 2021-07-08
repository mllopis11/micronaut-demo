package mike.demo.company.organization;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Organizations Management",
                version = "1.0",
                description = "Organization API",
                contact = @Contact(url = "https://github.com/mllopis11", name = "Mike", email = "mike@email.com")
        )
)
public class OrganizationApplication {

    public static void main(String[] args) {
        Micronaut.run(OrganizationApplication.class);
    }
}
