package org.example.factory;

import lombok.extern.log4j.Log4j2;
import org.example.enums.Mode;
import org.example.enums.PollingType;
import org.example.poller.BrokerPoller;
import org.example.poller.DataPoller;
import org.example.poller.DatabasePoller;
import org.example.poller.FilePoller;
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

    public DataPoller createDataPoller() {
        if (mode.equals(Mode.LIVE)) {
            log.info("Platform set to LIVE mode, creating BrokerPoller");
            return new BrokerPoller();
        } else {
            if (pollingType.equals(PollingType.FILE)) {
                log.info("Platform set to BACK_TEST mode and pollingType set to FILE, creating FilePoller");
                return new FilePoller();
            } else {
                log.info("Platform set to BACK_TEST mode and pollingType set to DATABASE, creating DatabasePoller");
                return new DatabasePoller();
            }
        }
    }
}
