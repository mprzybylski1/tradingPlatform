package org.example.strategy.implementation;

import lombok.extern.log4j.Log4j2;
import org.example.strategy.Strategy;
import org.example.symbol.Symbol;
import org.springframework.scheduling.annotation.Async;

@Log4j2
public class StrandardStrategy implements Strategy {

    private final Symbol symbol;

    public StrandardStrategy(final Symbol symbol) {
        this.symbol = symbol;
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
