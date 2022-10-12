package ch.cleoag.lxp.middleware_commons.configuration.security;

import ch.cleoag.lxp.middleware_commons.configuration.object.mapper.ObjectMapperConfigBuilder;
import ch.cleoag.lxp.middleware_commons.dto.jwt.TokenInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
public class TokenParser {

    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final ObjectMapper MAPPER = new ObjectMapperConfigBuilder().build();

    /**
     * Get token fromm Authorization header (Beaer ed...)
     *
     * @param authorizationHeader authroization header value
     */
    public static Optional<String> getTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null) {
            String[] authorizationArray = authorizationHeader.split(" ");
            if (authorizationArray.length == 2) {
                return Optional.of(authorizationArray[1]);
            }
        }
        return Optional.empty();
    }

    public static Optional<Object> readTokenInformationAs(String token, String... keys) {
        List<String> validKeys = null;
        if (keys != null && keys.length > 0) {
            validKeys = Stream.of(keys).filter(StringUtils::isNotBlank).toList();
        }
        if (token != null && validKeys != null) {
            try {
                // parse realm information from base-64 token
                String[] chunks = token.split("\\.");
                String payload = new String(decoder.decode(chunks[1]));
                JSONObject payloadObject = new JSONObject(payload);
                for (String key : validKeys) {
                    if (payloadObject.has(key)) {
                        return Optional.of(payloadObject.get(key));
                    }
                }
            } catch (ArrayIndexOutOfBoundsException | JSONException exception) {
                log.warn(exception.getLocalizedMessage(), exception);
            }
        }
        return Optional.empty();
    }

    /**
     * Get the value you needed from token by provide the keys
     */
    public static Optional<String> readTokenInformation(String token, String... keys) {
        return readTokenInformationAs(token, keys).map(String.class::cast);
    }

    public static Optional<List<Object>> readTokenInformationAsList(String token, String... keys) {
        return readTokenInformationAs(token, keys)
                .map(JSONArray.class::cast)
                .map(JSONArray::toList);
    }

    /**
     * Parse the token string to the object {@link TokenInformation}
     */
    public static Optional<TokenInformation> readTokenInformation(String token) {
        if (StringUtils.isNotBlank(token)) {
            try {
                String payload = new String(decoder.decode(token.split("\\.")[1]));
                return Optional.of(MAPPER.readValue(payload, TokenInformation.class));
            } catch (ArrayIndexOutOfBoundsException | JsonProcessingException exception) {
                log.warn(exception.getLocalizedMessage(), exception);
            }
        }
        return Optional.empty();
    }
}
