package ch.cleoag.lxp.middleware_commons.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Api response to wrap returned message into a JSON object
 */
@SuperBuilder
@Data
public class ApiResponse {
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
}
