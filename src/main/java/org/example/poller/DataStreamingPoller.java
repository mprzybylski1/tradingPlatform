package org.example.poller;

import org.example.api.DataStreamingApi;
import org.example.valueobject.Candle;

import java.util.Optional;

public class DataStreamingPoller implements DataPoller {

    private final DataStreamingApi dataStreamingApi;

    public DataStreamingPoller(final DataStreamingApi dataStreamingApi) {
        this.dataStreamingApi = dataStreamingApi;
    }

    @Override
    public Optional<Candle> poll() {
        return dataStreamingApi.getNext();
    }

    @Override
    public String getApiClass() {
        return dataStreamingApi.getClass().getSimpleName();
    }
}
