package Radiacia.tests;

import Radiacia.data.ClientData;
import Radiacia.data.Data;
import Radiacia.data.GamerData;
import Radiacia.server.client.Client;
import Radiacia.server.eventlisteners.ClientDataListener;
import Radiacia.server.eventlisteners.GameClientEventGenerator;
import Radiacia.server.eventlisteners.GameDataListener;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class GameClientEventGeneratorTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    private GameClientEventGenerator init() {
        return new GameClientEventGenerator(new Client() {
            @Override
            public void write(Data data) throws IOException {

            }

            @Override
            public Data read() throws IOException {
                try {
                    wait(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return new ClientData();
            }

            @Override
            public void connect() throws IOException {

            }

            @Override
            public void disconnect() throws IOException {

            }

            @Override
            public boolean isConnected() {
                return false;
            }
        });
    }

    /**
     * Тестирует добавление и удаление слушателей
     */
    @Test
    public void testListeners() throws Exception {
        GameClientEventGenerator gceg = init();

        gceg.addListener(new ClientDataListener(gceg.getClient()) {
            @Override
            public void initClientEvent(ClientData cd) {

            }
        });
        gceg.addListener(new GameDataListener() {
            @Override
            public void onGamerData(GamerData gamerData) {

            }
        });

        int listenersCount = gceg.removeAllListeners().size();
        Assert.assertEquals(2, listenersCount);
    }
}