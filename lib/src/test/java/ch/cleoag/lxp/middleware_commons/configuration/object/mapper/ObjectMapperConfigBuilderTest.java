package ch.cleoag.lxp.middleware_commons.configuration.object.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectMapperConfigBuilderTest {

    @Test
    public void build_returnMapper() {
        ObjectMapper result = new ObjectMapperConfigBuilder().build();
        Assertions.assertNotNull(result);
    }
}
