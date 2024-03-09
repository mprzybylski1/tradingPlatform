package org.example.api;

import org.example.valueobject.Candle;

import java.util.Optional;

public class BrokerApi implements DataStreamingApi {
    @Override
    public Optional<Candle> getNext() {
        return Optional.empty();
    }
}
