package org.example.strategy.config;

import org.example.factory.DataPollerFactory;
import org.example.poller.DataPoller;
import org.example.strategy.StandardStrategy;
import org.example.strategy.Strategy;
import org.example.valueobject.Symbol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class StrategyConfig {

    private final DataPollerFactory dataPollerFactory;

    public StrategyConfig(final DataPollerFactory dataPollerFactory) {
        this.dataPollerFactory = dataPollerFactory;
    }

    @Bean(name = "StandardStrategy")
    @Scope("prototype")
    public Strategy standardStrategy(final Symbol symbol) {
        final DataPoller dataPoller = dataPollerFactory.createDataPoller(symbol);
        return new StandardStrategy(symbol, dataPoller);
    }
}
