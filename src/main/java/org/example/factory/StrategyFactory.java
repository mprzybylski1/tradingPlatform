package org.example.factory;

import lombok.extern.log4j.Log4j2;
import org.example.poller.DataPoller;
import org.example.strategy.Strategy;
import org.example.valueobject.Symbol;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class StrategyFactory {

    private final ApplicationContext applicationContext;

    public StrategyFactory(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Strategy createStrategy(final Symbol symbol) {
        log.info("Creating strategy for symbol: {}", symbol);

        final String strategyName = symbol.getStrategyName();
        return (Strategy) applicationContext.getBean(strategyName, symbol);
    }
}
