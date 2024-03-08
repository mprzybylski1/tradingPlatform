package org.example.strategy.implementation;

import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.example.poller.DataPoller;
import org.example.strategy.Strategy;
import org.example.symbol.Symbol;
import org.springframework.scheduling.annotation.Async;

@Log4j2
@EqualsAndHashCode
public class StandardStrategy implements Strategy {

    private final Symbol symbol;
    private final DataPoller dataPoller;

    public StandardStrategy(final Symbol symbol, final DataPoller dataPoller) {
        this.symbol = symbol;
        this.dataPoller = dataPoller;
    }

    @Override
    public void evaluate() {

    }

    @Override
    public void openPosition() {

    }

    @Override
    public void closePosition() {

    }

    @Override
    @Async("threadPoolTaskExecutor")
    public void run() {
        log.info("Running strategy for symbol: {}", symbol.getName());

    }
}
