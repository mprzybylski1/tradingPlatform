package org.example.factory;

import org.example.enums.Mode;
import org.example.enums.PollingType;
import org.example.poller.BrokerPoller;
import org.example.poller.DataPoller;
import org.example.poller.DatabasePoller;
import org.example.poller.FilePoller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DataPollerFactoryTest {

    @Test
    void givenModeIsSetToLIVE_whenCreateDataPollerIsInvoked_thenCreateBrokerPoller() {
        final DataPollerFactory dataPollerFactory = new DataPollerFactory(Mode.LIVE, PollingType.BROKER);

        final DataPoller dataPoller = dataPollerFactory.createDataPoller();
        assertThat(dataPoller).isNotNull();
        assertThat(dataPoller).isInstanceOf(BrokerPoller.class);
    }

    @Test
    void givenModeIsSetToLIVE_andPollingTypeIsNull_whenCreateDataPollerIsInvoked_thenCreateBrokerPoller() {
        final DataPollerFactory dataPollerFactory = new DataPollerFactory(Mode.LIVE, null);

        final DataPoller dataPoller = dataPollerFactory.createDataPoller();
        assertThat(dataPoller).isNotNull();
        assertThat(dataPoller).isInstanceOf(BrokerPoller.class);
    }

    @Test
    void givenModeIsSetToBACK_TEST_andPollingTypeIsSetToFile_whenCreateDataPollerIsInvoked_thenCreateFilePoller() {
        final DataPollerFactory dataPollerFactory = new DataPollerFactory(Mode.BACK_TEST, PollingType.FILE);


        final DataPoller dataPoller = dataPollerFactory.createDataPoller();
        assertThat(dataPoller).isNotNull();
        assertThat(dataPoller).isInstanceOf(FilePoller.class);
    }

    @Test
    void givenModeIsSetToBACK_TEST_andPollingTypeIsSetToDatabase_whenCreateDataPollerIsInvoked_thenCreateDatabasePoller() {
        final DataPollerFactory dataPollerFactory = new DataPollerFactory(Mode.BACK_TEST, PollingType.DATABASE);


        final DataPoller dataPoller = dataPollerFactory.createDataPoller();
        assertThat(dataPoller).isNotNull();
        assertThat(dataPoller).isInstanceOf(DatabasePoller.class);
    }
}