package Radiacia.server.eventlisteners;

import Radiacia.data.ConnectData;
import Radiacia.server.client.GameClient;
import Radiacia.server.services.AccountService;

import java.io.IOException;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class GameClientConnectListener extends ConnectListener {
    private GameClient gameClient;
    private AccountService accountService;

    public GameClientConnectListener(GameClient gameClient) {
        this(gameClient, null);
    }

    public GameClientConnectListener(GameClient gameClient, AccountService accountService) {
        super(gameClient.getClient());
        this.gameClient = gameClient;
        this.accountService = accountService;
    }

    @Override
    public void onConnect(ConnectData cd) {
        try {
            gameClient.setConD(cd);

            if(accountService != null) accountService.connect(gameClient);
            else                       gameClient.connect(cd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setInfo() {

    }
}
