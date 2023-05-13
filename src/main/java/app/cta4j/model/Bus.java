package app.cta4j.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public record Bus(
    @JsonAlias("vid")
    int id,

    @JsonAlias("typ")
    Type type,

    @JsonAlias("stpnm")
    String stop,

    @JsonAlias("rt")
    String route,

    @JsonAlias("des")
    String destination,

    @JsonAlias("dly")
    boolean delayed,

    @JsonAlias("prdctdn")
    int eta
) {
    public enum Type {
        ARRIVAL,

        DEPARTURE;

        @JsonCreator
        public static Type ofString(String string) {
            Objects.requireNonNull(string);

            return switch (string) {
                case "A" -> Type.ARRIVAL;
                case "D" -> Type.DEPARTURE;
                default -> {
                    String message = "\"%s\" is not a valid type".formatted(string);

                    throw new IllegalArgumentException(message);
                }
            };
        }
    }

    public Bus {
        Objects.requireNonNull(type);

        Objects.requireNonNull(stop);

        Objects.requireNonNull(route);

        Objects.requireNonNull(destination);
    }
}
