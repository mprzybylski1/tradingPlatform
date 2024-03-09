package org.example.poller;

import org.example.api.BrokerApi;
import org.example.api.DataStreamingApi;
import org.example.api.DatabaseApi;
import org.example.api.FileApi;
import org.example.valueobject.Candle;
import org.example.valueobject.Symbol;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataStreamingPollerTest {

    @Test
    void whenPollMethodIsInvoked_thenCandleDataIsReturned() {
        final DataStreamingApi mockApi = mock(DataStreamingApi.class);
        final Symbol mockSymbol = mock(Symbol.class);
        final Candle mockCandle = mock(Candle.class);

        when(mockApi.getNext()).thenReturn(Optional.of(mockCandle));

        final DataPoller dataPoller = new DataStreamingPoller(mockApi);
        final Optional<Candle> optionalCandle = dataPoller.poll(mockSymbol);

        assertThat(optionalCandle).isPresent();
        verify(mockApi, times(1)).getNext();
    }

    @Test
    void givenBrokerApiIsUsed_whenGetApiClassIsInvoked_thenBrokerApiNameIsReturned() {
        final DataStreamingApi mockApi = mock(BrokerApi.class);

        final DataPoller dataPoller = new DataStreamingPoller(mockApi);
        assertThat(dataPoller.getApiClass()).isEqualTo("BrokerApi");
    }

    @Test
    void givenBrokerApiIsUsed_whenGetApiClassIsInvoked_thenFileApiNameIsReturned() {
        final DataStreamingApi mockApi = mock(FileApi.class);

        final DataPoller dataPoller = new DataStreamingPoller(mockApi);
        assertThat(dataPoller.getApiClass()).isEqualTo("FileApi");
    }

    @Test
    void givenBrokerApiIsUsed_whenGetApiClassIsInvoked_thenDatabaseApiNameIsReturned() {
        final DataStreamingApi mockApi = mock(DatabaseApi.class);

        final DataPoller dataPoller = new DataStreamingPoller(mockApi);
        assertThat(dataPoller.getApiClass()).isEqualTo("DatabaseApi");
    }


}
