package org.example.strategy.executor;

import org.example.config.Symbols;
import org.example.executor.StrategyExecutor;
import org.example.factory.DataPollerFactory;
import org.example.poller.DataPoller;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class StrategyExecutorTest {

    @MockBean
    private DataPollerFactory mockDataPollerFactory;

    @Mock
    private DataPoller mockDataPoller;

    @Autowired
    private StrategyExecutor strategyExecutor;

    @Autowired
    private Symbols symbols;

    @Test
    void givenDefinedSymbolsInConfiguration_whenStrategyExecutorStarts_thenStrategyShouldBeCreatedForEachSymbol() {
        when(mockDataPollerFactory.createDataPoller(any())).thenReturn(mockDataPoller);

        assertThat(strategyExecutor.start()).isEqualTo(symbols.getSymbols().size());
    }
}
