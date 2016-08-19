package Radiacia.data.datamanager;

import Radiacia.data.ClientData;
import Radiacia.data.Data;
import Radiacia.handler.ClientHandler;

import java.util.Collection;

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
    public void parseFromClient(Collection<Data> data) {
        parse(data);
    }

    @Override
    public void parse(Data data) {
        if (data instanceof ClientData) parse((ClientData) data);
    }

    public void parse(ClientData clientData) {
        ch.handle(clientData);
    }

    @Override
    public void reset() {

    }

    @Override
    public void parseSelf() {

    }
}
