package org.example.strategy.executor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StrategyExecutorTest {

    @Autowired
    private StrategyExecutor strategyExecutor;

    @Test
    void name() {
        strategyExecutor.execute();
    }
}
