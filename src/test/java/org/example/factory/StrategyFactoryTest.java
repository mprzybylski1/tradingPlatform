package org.example.factory;

import org.example.poller.DataPoller;
import org.example.strategy.Strategy;
import org.example.valueobject.Symbol;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class StrategyFactoryTest {

    @MockBean
    private DataPollerFactory mockDataPollerFactory;

    @Mock
    private DataPoller mockDataPoller;

    @Autowired
    private StrategyFactory strategyFactory;

    @Test
    void givenStrategyNameExists_whenCreateStrategyIsInvoked_thenNewInstanceIsCreated() {
        final Symbol symbol = new Symbol("Test", "StandardStrategy", null);
        final Symbol symbol2 = new Symbol("Test2", "StandardStrategy", null);

        when(mockDataPollerFactory.createDataPoller(symbol)).thenReturn(mockDataPoller);

        final Strategy strategy = strategyFactory.createStrategy(symbol);

        assertThat(strategy).isNotNull();
        assertThat(strategy.getStrategyName()).isEqualTo("StandardStrategy");

        final Strategy strategy2 = strategyFactory.createStrategy(symbol2);

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