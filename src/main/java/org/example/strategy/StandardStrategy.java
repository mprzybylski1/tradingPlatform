package org.example.strategy;

import lombok.extern.log4j.Log4j2;
import org.example.poller.DataPoller;
import org.example.valueobject.Symbol;

@Log4j2
public class StandardStrategy extends BaseStrategy {

    public StandardStrategy(final Symbol symbol, final DataPoller dataPoller) {
        super(symbol, dataPoller);
    }

    @Override
    public void evaluate() {
        log.info("Evaluating strategy for symbol: {}", symbol.getName());

    }
}
