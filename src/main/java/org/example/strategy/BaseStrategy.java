package org.example.strategy;

import lombok.extern.log4j.Log4j2;
import org.example.enums.Timeframe;
import org.example.poller.DataPoller;
import org.example.valueobject.Candle;
import org.example.valueobject.Symbol;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;

@Log4j2
public abstract class BaseStrategy implements Strategy {

    protected final Symbol symbol;
    protected final DataPoller dataPoller;

    public BaseStrategy(final Symbol symbol, final DataPoller dataPoller) {
        this.symbol = symbol;
        this.dataPoller = dataPoller;
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public void run() {
        log.info("Running strategy for symbol: {}", symbol.getName());

        Optional<Candle> optionalCandle = dataPoller.poll();
        while (optionalCandle.isPresent()) {
            final Candle candle = optionalCandle.get();
            log.info("Received candle: {}", candle);
            evaluate(candle);

            optionalCandle = dataPoller.poll();
        }

        log.info("No more candles to process, exiting strategy for symbol: {}", symbol.getName());
    }

    @Override
    public void openPosition() {

    }

    @Override
    public void closePosition() {

    }
}
