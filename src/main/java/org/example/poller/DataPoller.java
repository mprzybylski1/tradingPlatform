package org.example.poller;

import org.example.valueobject.Candle;
import org.example.valueobject.Symbol;

import java.util.Optional;

public interface DataPoller {

    Optional<Candle> poll();

    String getApiClass();
}
