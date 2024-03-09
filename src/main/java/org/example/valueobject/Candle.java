package org.example.valueobject;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "timestamp", "open", "close", "high", "low", "volume"})
public class Candle {

    private LocalDateTime timestamp;
    private double open;
    private double close;
    private double high;
    private double low;
    private double volume;
}
