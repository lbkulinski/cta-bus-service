package app.cta4j.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.math.BigDecimal;
import java.util.Objects;

public record Stop(
    @JsonAlias("stpid")
    int id,

    @JsonAlias("stpnm")
    String name,

    @JsonAlias("lat")
    BigDecimal latitude,

    @JsonAlias("lon")
    BigDecimal longitude
) {
    public Stop {
        Objects.requireNonNull(name);

        Objects.requireNonNull(latitude);

        Objects.requireNonNull(longitude);
    }
}
