package jill.common.model;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author Jill W
 * @date 2020/12/08
 */
public abstract class Criteria {
    protected static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(Include.NON_EMPTY);
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.registerModule(new GuavaModule());
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    protected Criteria() {
    }

    public Map toMap() {
        return MAPPER.convertValue(this, Map.class);
    }
}
