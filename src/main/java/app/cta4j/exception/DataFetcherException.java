package app.cta4j.exception;

import graphql.GraphQLError;
import graphql.ErrorClassification;
import java.util.Objects;
import java.util.List;
import graphql.language.SourceLocation;

public class DataFetcherException extends RuntimeException implements GraphQLError {
    private final ErrorClassification errorType;

    public DataFetcherException(String message, ErrorClassification errorType) {
        super(message);

        Objects.requireNonNull(errorType);

        this.errorType = errorType;
    }

    public DataFetcherException(ErrorClassification errorType) {
        this("An error occurred when trying to fetch data from the CTA's API", errorType);
    }

    @Override
    public ErrorClassification getErrorType() {
        return this.errorType;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }
}
