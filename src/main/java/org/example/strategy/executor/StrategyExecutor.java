package org.example.strategy.executor;

import org.example.config.Symbols;
import org.example.strategy.StrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategyExecutor {

    @Autowired
    private Symbols symbols;

    @Autowired
    private StrategyFactory strategyFactory;

    public void execute() {
        symbols.getSymbols().stream()
            .map(symbol -> strategyFactory.createStrategy(symbol))
            .forEach(Runnable::run);
    }

}
