package mike.demo.company.test;

import static org.assertj.core.api.Assertions.*;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.core.annotation.Order;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import mike.demo.company.organization.model.Organization;
import mike.demo.company.organization.web.model.OrganizationModel;

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrganizationResourcesTest {

    private static final Logger log = LoggerFactory.getLogger(OrganizationResourcesTest.class);

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    @Order(1)
    void should_return_an_empty_collection_when_get_all_organizations() {
        HttpRequest<String> request = HttpRequest.GET("/organizations");

        Collection<?> body = client.toBlocking().retrieve(request, Collection.class);

        assertThat(body).isNotNull();
    }

    @Test
    @Order(2)
    void should_return_not_found_when_uuid_does_not_exists() {

        String uuid = "123e4567-e89b-12d3-a456-426614174000";

        HttpRequest<String> request = HttpRequest.GET("/organizations/" + uuid);
        BlockingHttpClient blockingClient = client.toBlocking();

        HttpClientResponseException exception = catchThrowableOfType(() -> blockingClient.exchange(request),
                HttpClientResponseException.class);

        assertThat(exception).isNotNull().hasMessageContainingAll("\"status\":404", "\"detail\":\"entity not found");

        HttpResponse<?> response = exception.getResponse();
        assertThat(response.getStatus().getCode()).isEqualTo(404);
    }

    @ParameterizedTest
    @Order(3)
    @ValueSource(strings = { 
            "123e4567-e89b-12d3-A456-426614174000",
            "123e4567-e89b-12d3-a456-42661417",
            "123e4567-e89b-12d3-A456-4266141740001" })
    void should_return_bad_request_when_uuid_malformatted(String uuid) {

        HttpRequest<String> request = HttpRequest.GET("/organizations/" + uuid);
        BlockingHttpClient blockingClient = client.toBlocking();

        HttpClientResponseException exception = 
                catchThrowableOfType(() -> blockingClient.exchange(request), HttpClientResponseException.class);

        assertThat(exception).isNotNull().hasMessageContainingAll("\"status\":400",
                "\"title\":\"Constraint Violation\"", "invalid UUID format");

        HttpResponse<?> response = exception.getResponse();
        assertThat(response.getStatus().getCode()).isEqualTo(400);
    }

    @Test
    @Order(4)
    void should_return_created_organization_when_create_google() {

        OrganizationModel model = new OrganizationModel();
        model.setName("Google");
        model.setAddress("Mountain View, California (United States)");
        HttpRequest<OrganizationModel> request = HttpRequest.POST("/organizations", model);

        HttpResponse<Organization> response = client.toBlocking().exchange(request, Organization.class);

        assertThat(response).isNotNull();

        log.debug("Response: status={}, body={}", response.status(), response.body());

        assertThat(response.getStatus().getCode()).isEqualTo(200);

        assertThat(response.body()).isNotNull().satisfies(o -> {
            assertThat(o.getUuid()).isNotBlank();
            assertThat(o.getName()).isEqualTo("Google");
            assertThat(o.getAddress()).startsWith("Mountain View");
        });
    }
}
