package Radiacia.eventlisteners;

import Radiacia.data.ClientData;
import Radiacia.data.Data;
import Radiacia.handler.ClientHandler;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class GameClientListener implements DataListener {
    @Override
    public void initEvent(Data data) {
        if (data instanceof ClientData) onClientData((ClientData) data);
    }

    public void onClientData(ClientData clientData) {
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.handle(clientData);
    }
}
