package mike.demo.company.organization.commons.exception;

import java.net.URI;

import org.zalando.problem.AbstractThrowableProblem;

import io.micronaut.http.HttpStatus;
import io.micronaut.problem.HttpStatusType;

public class EntityNotFoundProblem extends AbstractThrowableProblem  {

    private static final long serialVersionUID = 1723760131503620026L;

    private static final URI TYPE = URI.create("about:blank");
    public EntityNotFoundProblem(String uuid) {
        super(TYPE, "Not Found", new HttpStatusType(HttpStatus.NOT_FOUND), String.format("entity not found: %s", uuid));
    }
}
