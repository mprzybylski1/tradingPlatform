package org.example.config;

import lombok.Data;
import org.example.enums.Mode;
import org.example.enums.PollingType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
@Data
public class Config {

    private String mode;
    private String pollingType;

    @Bean
    public Mode mode() {
        return Mode.valueOf(mode.toUpperCase());
    }

    @Bean
    public PollingType pollingType() {
        if (mode().equals(Mode.LIVE)) {
            return PollingType.BROKER;
        }
        return PollingType.valueOf(pollingType.toUpperCase());
    }
}
