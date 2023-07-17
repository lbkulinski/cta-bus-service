package app.cta4j.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DirectionResponse(@JsonAlias("bustime-response") DirectionBody body) {
}
