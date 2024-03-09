package org.example.strategy;

import org.example.api.FileApi;
import org.example.config.Symbols;
import org.example.poller.DataPoller;
import org.example.poller.DataStreamingPoller;
import org.example.valueobject.Candle;
import org.example.valueobject.Symbol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class StandardStrategyTest {

    @Autowired
    private Symbols symbols;

    @Test
    void givenRunningStrategy_whenThereAreThreeCandlesPresent_thenVerifyPollMethodIsCalledCorrectNumberOfTimes() {
        final Symbol symbol = symbols.getSymbols().get(0);
        final DataPoller mockDataPoller = mock(DataPoller.class);
        final Candle mockCandle = mock(Candle.class);
        final Optional<Candle> optionalCandle = Optional.of(mockCandle);
        final Optional<Candle> empty = Optional.empty();

        when(mockDataPoller.poll(symbol)).thenReturn(optionalCandle,
                                                    optionalCandle,
                                                    optionalCandle,
                                                    empty);

        final StandardStrategy standardStrategy = new StandardStrategy(symbol, mockDataPoller);
        standardStrategy.run();

        verify(mockDataPoller, times(4)).poll(symbol);
    }
}