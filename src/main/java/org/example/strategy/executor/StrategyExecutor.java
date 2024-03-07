package org.example.strategy.executor;

import org.example.config.Symbols;
import org.example.strategy.StrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StrategyExecutor {

    @Autowired
    private Symbols symbols;

    @Autowired
    private StrategyFactory strategyFactory;

    public int start() {
        final AtomicInteger strategyCount = new AtomicInteger();

        symbols.getSymbols().stream()
            .map(symbol -> {
                strategyCount.incrementAndGet();
                return strategyFactory.createStrategy(symbol);
            })
            .forEach(Runnable::run);

        return strategyCount.get();
    }

}
