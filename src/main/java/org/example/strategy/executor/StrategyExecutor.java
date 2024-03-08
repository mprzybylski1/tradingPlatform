package org.example.strategy.executor;

import org.example.config.Symbols;
import org.example.factory.StrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StrategyExecutor {

    @Autowired
    private Symbols symbols;

    @Autowired
    private StrategyFactory strategyFactory;

    //TODO add data poller to get the data from the exchange
    //TODO add a method to stop the strategies and close the positions
    //TODO add database to store the positions and the strategies and the data

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
