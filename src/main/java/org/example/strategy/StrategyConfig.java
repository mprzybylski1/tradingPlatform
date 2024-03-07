package org.example.strategy;

import org.example.strategy.implementation.StrandardStrategy;
import org.example.symbol.Symbol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class StrategyConfig {

    @Bean(name = "StandardStrategy")
    @Scope("prototype")
    public Strategy standardStrategy(final Symbol symbol) {
        return new StrandardStrategy(symbol);
    }
}
