package app.cta4j.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record RouteResponse(@JsonAlias("bustime-response") RouteBody body) {
}
