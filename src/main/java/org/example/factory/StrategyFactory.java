package org.example.factory;

import lombok.extern.log4j.Log4j2;
import org.example.strategy.Strategy;
import org.example.symbol.Symbol;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class StrategyFactory {

    private final ApplicationContext applicationContext;
    private final DataPollerFactory dataPollerFactory;

    public StrategyFactory(final ApplicationContext applicationContext, final DataPollerFactory dataPollerFactory) {
        this.applicationContext = applicationContext;
        this.dataPollerFactory = dataPollerFactory;
    }

    public Strategy createStrategy(final Symbol symbol) {
        log.info("Creating strategy for symbol: {}", symbol);

        return (Strategy) applicationContext.getBean(symbol.getStrategyName(),
                symbol,
                dataPollerFactory.createDataPoller());
    }
}
