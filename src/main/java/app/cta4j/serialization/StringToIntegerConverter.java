package app.cta4j.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;
import java.util.Objects;

public final class StringToIntegerConverter extends StdConverter<String, Integer> {
    @Override
    public Integer convert(String string) {
        Objects.requireNonNull(string);

        String due = "DUE";

        if (string.equalsIgnoreCase(due)) {
            return 0;
        }

        return Integer.parseInt(string);
    }
}
