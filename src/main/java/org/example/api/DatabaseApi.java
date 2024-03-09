package org.example.api;

import org.example.valueobject.Candle;

import java.util.Optional;

public class DatabaseApi implements DataStreamingApi {
    @Override
    public Optional<Candle> getNext() {
        return Optional.empty();
    }
}
