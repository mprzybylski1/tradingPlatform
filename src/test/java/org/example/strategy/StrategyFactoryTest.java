package org.example.strategy;

import org.example.strategy.implementation.StrandardStrategy;
import org.example.symbol.Symbol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class StrategyFactoryTest {

    @Autowired
    private StrategyFactory strategyFactory;

    @Test
    void givenStrategyNameExists_whenCreateStrategyIsInvoked_thenNewInstanceIsCreated() {
        final Symbol mockSymbol = mock(Symbol.class);
        when(mockSymbol.getStrategyName()).thenReturn("StandardStrategy");

        final Strategy strategy = strategyFactory.createStrategy(mockSymbol);

        assertThat(strategy).isNotNull();
        assertThat(strategy).isInstanceOf(StrandardStrategy.class);

        final Strategy strategy2 = strategyFactory.createStrategy(mockSymbol);

        assertThat(strategy).isNotNull();
        assertThat(strategy).isInstanceOf(StrandardStrategy.class);

        assertThat(strategy).isNotEqualTo(strategy2);
    }

    @Test
    void givenStrategyNameDoesNotExist_whenCreateStrategyIsInvoked_thenNoSuchBeanDefinitionExceptionIsThrown() {
        final Symbol mockSymbol = mock(Symbol.class);
        when(mockSymbol.getStrategyName()).thenReturn("Test");

        assertThrows(NoSuchBeanDefinitionException.class, () -> strategyFactory.createStrategy(mockSymbol));
    }
}