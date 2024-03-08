package org.example.strategy.executor;

import org.example.config.Symbols;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class StrategyExecutorTest {

    @Autowired
    private StrategyExecutor strategyExecutor;

    @Autowired
    private Symbols symbols;

    @Test
    void givenDefinedSymbolsInConfiguration_whenStrategyExecutorStarts_thenStrategyShouldBeCreatedForEachSymbol() {
        assertThat(strategyExecutor.start()).isEqualTo(symbols.getSymbols().size());
    }
}
