package Radiacia.data.datamanager;

import Radiacia.data.ClientData;
import Radiacia.data.Data;
import Radiacia.handler.ClientHandler;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Передает данные клиенту
 */
public class ClientDataManager extends AbstractDataManager {
    private ClientHandler ch;

    public ClientDataManager() {
        this(new ClientHandler());
    }

    public ClientDataManager(ClientHandler clientHandler) {
        this.ch = clientHandler;
    }

    @Override
    public void parse(Data data) {
        if (data instanceof ClientData) parse((ClientData) data);
    }

    public void parse(ClientData clientData) {
        ch.handle(clientData);
    }
}
