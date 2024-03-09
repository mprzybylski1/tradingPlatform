package org.example.strategy;

import lombok.extern.log4j.Log4j2;
import org.example.poller.DataPoller;
import org.example.valueobject.Candle;
import org.example.valueobject.Symbol;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Optional;

@Log4j2
public abstract class BaseStrategy implements Strategy {

    protected final Symbol symbol;
    protected final DataPoller dataPoller;
    protected final Deque<Candle> historicCandles;

    public BaseStrategy(final Symbol symbol, final DataPoller dataPoller) {
        this.symbol = symbol;
        this.dataPoller = dataPoller;
        this.historicCandles = new ArrayDeque<>(getHistoricDataSize(symbol));
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
            addHistoricData(candle);

            optionalCandle = dataPoller.poll();
        }

        log.info("No more candles to process, exiting strategy for symbol: {}", symbol.getName());
    }

    private void addHistoricData(final Candle candle) {
        if (!historicCandles.offer(candle)) {
            historicCandles.poll();
            historicCandles.offer(candle);
        }
    }

    @Override
    public void openPosition() {

    }

    @Override
    public void closePosition() {

    }

    private int getHistoricDataSize(final Symbol symbol) {
        final Map<String, String> parameters = symbol.getParameters();

        if (parameters.containsKey("historicDataSize")) {
            return Integer.parseInt(parameters.get("historicDataSize"));
        }

        return 100;
    }
}
