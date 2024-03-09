package org.example.factory;

import lombok.extern.log4j.Log4j2;
import org.example.api.BrokerApi;
import org.example.api.DatabaseApi;
import org.example.api.FileApi;
import org.example.enums.Mode;
import org.example.enums.PollingType;
import org.example.poller.DataPoller;
import org.example.poller.DataStreamingPoller;
import org.example.valueobject.Symbol;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DataPollerFactory {

    private final Mode mode;
    private final PollingType pollingType;

    public DataPollerFactory(final Mode mode, final PollingType pollingType) {
        this.mode = mode;
        this.pollingType = pollingType;
    }

    public DataPoller createDataPoller(final Symbol symbol) {
        if (mode.equals(Mode.LIVE)) {
            log.info("Platform set to LIVE mode, creating BrokerPoller");
            return new DataStreamingPoller(new BrokerApi());
        } else {
            if (pollingType.equals(PollingType.FILE)) {
                log.info("Platform set to BACK_TEST mode and pollingType set to FILE, creating FilePoller");
                return new DataStreamingPoller(new FileApi(symbol));
            } else {
                log.info("Platform set to BACK_TEST mode and pollingType set to DATABASE, creating DatabasePoller");
                return new DataStreamingPoller(new DatabaseApi());
            }
        }
    }
}
