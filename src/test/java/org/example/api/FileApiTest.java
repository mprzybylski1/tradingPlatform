package org.example.api;

import lombok.SneakyThrows;
import org.example.valueobject.Candle;
import org.example.valueobject.Symbol;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileApiTest {

    @Test
    @SneakyThrows
    void givenSymbolHasFilePathParameter_whenFileApiIsCreated_thenObjectIsNotNull() {
        final Symbol mockSymbol = mock(Symbol.class);

        final ClassPathResource classPathResource = new ClassPathResource("test.csv");
        when(mockSymbol.getParameters()).thenReturn(Map.of("filepath", classPathResource.getFile().getAbsolutePath()));

        final FileApi fileApi = new FileApi(mockSymbol);
        assertThat(fileApi).isNotNull();
    }

    @Test
    @SneakyThrows
    void givenFileApiObjectIsCreated_whenGetNextIsInvoked_thenGetNextCandle() {
        final Symbol mockSymbol = mock(Symbol.class);

        final ClassPathResource classPathResource = new ClassPathResource("test.csv");
        when(mockSymbol.getParameters()).thenReturn(Map.of("filepath", classPathResource.getFile().getAbsolutePath()));

        final FileApi fileApi = new FileApi(mockSymbol);
        assertThat(fileApi).isNotNull();

        final Optional<Candle> candle = fileApi.getNext();
        assertCandle(candle);
    }

    @Test
    @SneakyThrows
    void givenFileApiObjectIsCreated_whenGetNextIsInvokedUntilTheEndOfFile_thenAllCandlesAreLoaded() {
        final Symbol mockSymbol = mock(Symbol.class);

        final ClassPathResource classPathResource = new ClassPathResource("test.csv");
        when(mockSymbol.getParameters()).thenReturn(Map.of("filepath", classPathResource.getFile().getAbsolutePath()));

        final FileApi fileApi = new FileApi(mockSymbol);
        assertThat(fileApi).isNotNull();

        final Optional<Candle> candle1 = fileApi.getNext();
        assertCandle(candle1);

        final Optional<Candle> candle2 = fileApi.getNext();
        assertCandle(candle2);

        final Optional<Candle> candle3 = fileApi.getNext();
        assertCandle(candle3);

        final Optional<Candle> candle4 = fileApi.getNext();
        assertThat(candle4).isNotNull();
        assertThat(candle4).isEmpty();
    }

    @Test
    @SneakyThrows
    void givenSymbolHasMissingFilePathParameter_whenFileApiIsCreated_thenIllegalArgumentExceptionIsThrown() {
        final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new FileApi(mock(Symbol.class)));
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Missing filePath parameter");
    }

    private void assertCandle(final Optional<Candle> candle) {
        assertThat(candle).isNotNull();
        assertThat(candle).isNotEmpty();
        assertThat(candle.get().getTimestamp()).isNotNull();
        assertThat(candle.get().getOpen()).isPositive();
        assertThat(candle.get().getClose()).isPositive();
        assertThat(candle.get().getHigh()).isPositive();
        assertThat(candle.get().getLow()).isPositive();
        assertThat(candle.get().getVolume()).isPositive();
    }
}
