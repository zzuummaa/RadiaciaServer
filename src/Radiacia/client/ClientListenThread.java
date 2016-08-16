package Radiacia.client;

import Radiacia.data.ClientData;
import Radiacia.data.Data;
import Radiacia.handler.CollectionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Слушает клиента и передает данные на обработку
 */
public class ClientListenThread extends Thread {
    private Map<Class, CollectionHandler> handlers = new HashMap<>();
    private Client client;

    public ClientListenThread(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Data data = client.read();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handle() {
        for (CollectionHandler handler: handlers.values()) {
            handler.handle();
        }
    }

    public void update(Data data) {
        if (data instanceof ClientData) {
            ClientData clientData = (ClientData) data;
            clientData.setOwner(client);
        }
    }

    public void addHandler(CollectionHandler collectionHandler) {
        handlers.put(collectionHandler.getClass(), collectionHandler);
    }
}
