package org.example.factory;

import org.example.strategy.Strategy;
import org.example.strategy.implementation.StandardStrategy;
import org.example.symbol.Symbol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class StrategyFactoryTest {

    @Autowired
    private StrategyFactory strategyFactory;

    @Test
    void givenStrategyNameExists_whenCreateStrategyIsInvoked_thenNewInstanceIsCreated() {
        final Symbol symbol = new Symbol("Test", "StandardStrategy", null);
        final Strategy strategy = strategyFactory.createStrategy(symbol);

        assertThat(strategy).isNotNull();
        assertThat(strategy.getStrategyName()).isEqualTo("StandardStrategy");

        final Strategy strategy2 = strategyFactory.createStrategy(symbol);

        assertThat(strategy).isNotNull();
        assertThat(strategy.getStrategyName()).isEqualTo("StandardStrategy");

        assertThat(strategy).isNotEqualTo(strategy2);
    }

    @Test
    void givenStrategyNameDoesNotExist_whenCreateStrategyIsInvoked_thenNoSuchBeanDefinitionExceptionIsThrown() {
        final Symbol mockSymbol = mock(Symbol.class);
        when(mockSymbol.getStrategyName()).thenReturn("Test");

        assertThrows(NoSuchBeanDefinitionException.class, () -> strategyFactory.createStrategy(mockSymbol));
    }
}