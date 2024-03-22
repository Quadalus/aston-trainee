package hw2.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class ObjectMapperUtil {
    private final static ObjectMapper INSTANCE = new ObjectMapper();

    private ObjectMapperUtil() {
    }

    static {
        INSTANCE.registerModule(new JavaTimeModule());
        INSTANCE.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static ObjectMapper getInstance() {
        return INSTANCE;
    }
}
