package org.example.strategy;

import lombok.extern.log4j.Log4j2;
import org.example.symbol.Symbol;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class StrategyFactory {

    private final ApplicationContext applicationContext;

    public StrategyFactory(final ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    public Strategy createStrategy(final Symbol symbol) {
        log.info("Creating strategy for symbol: {}", symbol);
        return (Strategy) applicationContext.getBean(symbol.getStrategyName(), symbol);
    }

}
