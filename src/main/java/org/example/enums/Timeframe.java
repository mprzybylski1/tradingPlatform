package org.example.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Timeframe {
    ONE_MINUTE("1M"), ONE_HOUR("1H"), ONE_DAY("1D");

    private String value;

    Timeframe(String value) {
        this.value = value;
    }

    public static Timeframe fromString(final String value) {
        return Arrays.stream(Timeframe.values())
                .filter(timeframe -> timeframe.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid timeframe value: " + value));
    }

}
