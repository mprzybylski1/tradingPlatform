package org.example.factory;

import lombok.SneakyThrows;
import org.example.enums.Mode;
import org.example.enums.PollingType;
import org.example.poller.DataPoller;
import org.example.valueobject.Symbol;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataPollerFactoryTest {

    @Mock
    private Symbol symbol;

    @Test
    void givenModeIsSetToLIVE_whenCreateDataPollerIsInvoked_thenCreateBrokerPoller() {
        final DataPollerFactory dataPollerFactory = new DataPollerFactory(Mode.LIVE, PollingType.BROKER);

        final DataPoller dataPoller = dataPollerFactory.createDataPoller(symbol);
        assertThat(dataPoller).isNotNull();
        assertThat(dataPoller.getApiClass()).isEqualTo("BrokerApi");
    }

    @Test
    void givenModeIsSetToLIVE_andPollingTypeIsNull_whenCreateDataPollerIsInvoked_thenCreateBrokerPoller() {
        final DataPollerFactory dataPollerFactory = new DataPollerFactory(Mode.LIVE, null);

        final DataPoller dataPoller = dataPollerFactory.createDataPoller(symbol);
        assertThat(dataPoller).isNotNull();
        assertThat(dataPoller.getApiClass()).isEqualTo("BrokerApi");
    }

    @Test
    @SneakyThrows
    void givenModeIsSetToBACK_TEST_andPollingTypeIsSetToFile_whenCreateDataPollerIsInvoked_thenCreateFilePoller() {
        final DataPollerFactory dataPollerFactory = new DataPollerFactory(Mode.BACK_TEST, PollingType.FILE);

        final ClassPathResource classPathResource = new ClassPathResource("test.csv");
        when(symbol.getParameters()).thenReturn(Map.of("filepath", classPathResource.getFile().getAbsolutePath()));

        final DataPoller dataPoller = dataPollerFactory.createDataPoller(symbol);
        assertThat(dataPoller).isNotNull();
        assertThat(dataPoller.getApiClass()).isEqualTo("FileApi");
    }

    @Test
    void givenModeIsSetToBACK_TEST_andPollingTypeIsSetToDatabase_whenCreateDataPollerIsInvoked_thenCreateDatabasePoller() {
        final DataPollerFactory dataPollerFactory = new DataPollerFactory(Mode.BACK_TEST, PollingType.DATABASE);

        final DataPoller dataPoller = dataPollerFactory.createDataPoller(symbol);
        assertThat(dataPoller).isNotNull();
        assertThat(dataPoller.getApiClass()).isEqualTo("DatabaseApi");
    }
}