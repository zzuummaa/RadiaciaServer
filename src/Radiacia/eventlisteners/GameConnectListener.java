package Radiacia.eventlisteners;

import Radiacia.client.Client;
import Radiacia.data.ClientData;
import Radiacia.data.ConnectData;
import Radiacia.data.Data;
import Radiacia.server.GameClient;
import Radiacia.server.IDManager;

import java.io.IOException;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class GameConnectListener implements DataListener {
    private IDManager idManager;
    private GameClient gameClient;
    private Client client;

    public GameConnectListener(GameClient gameClient, IDManager idManager) {
        this.gameClient = gameClient;
        this.client = gameClient.getClient();
    }

    @Override
    public void initEvent(Data data) {
        if (data instanceof ConnectData) onConnect((ConnectData) data);
    }

    public void onConnect(ConnectData cd) {
        try {
            tryToConnect(cd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Обменивается информацией о подключении
     *
     * Пытается выяснить, какую информацию использовать при подключении
     * Использует полученные данные, если их <code>id != 0</code>
     *
     * @throws java.io.IOException
     */
    protected void tryToConnect(ConnectData inCD) throws IOException {
        ConnectData outCD = new ConnectData();
        outCD.setData(new ClientData(true));

        Long id = idManager.getNextID();
        outCD.setId(id);

        ConnectData connectData;
        if (inCD.getId() != 0 && idManager.contains(inCD.getId())) {
            connectData = inCD;
        } else {
            connectData = outCD;
        }

        connectData.setOwner(client);

        gameClient.connect(connectData);
    }

    private void setInfo() {

    }
}
