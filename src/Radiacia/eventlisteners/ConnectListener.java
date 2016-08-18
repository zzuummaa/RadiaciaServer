package Radiacia.eventlisteners;

import Radiacia.data.ClientData;
import Radiacia.data.ConnectData;
import Radiacia.server.AccountService;
import Radiacia.server.GameClient;

import java.io.IOException;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class ConnectListener extends ClientDataListener {
    private GameClient gameClient;
    private AccountService accountService;

    public ConnectListener(GameClient gameClient) {
        this(gameClient, null);
    }

    public ConnectListener(GameClient gameClient, AccountService accountService) {
        super(gameClient.getClient());
        this.gameClient = gameClient;
        this.accountService = accountService;
    }

    @Override
    public void initClientEvent(ClientData cd) {
        if (cd instanceof ConnectData) onConnect((ConnectData) cd);
    }

    public void onConnect(ConnectData cd) {
        try {
            tryToConnect(cd);

            if(accountService != null) accountService.connect(gameClient);

            System.out.println("connect: " + gameClient);
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
        gameClient.setConD(inCD);
    }

    private void setInfo() {

    }
}
