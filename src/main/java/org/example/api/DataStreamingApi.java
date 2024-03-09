package org.example.api;

import org.example.valueobject.Candle;

import java.util.Optional;

public interface DataStreamingApi {

    Optional<Candle> getNext();
}
