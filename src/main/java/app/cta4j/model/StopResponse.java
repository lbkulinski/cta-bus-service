package app.cta4j.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record StopResponse(@JsonAlias("bustime-response") StopBody body) {
}
