package Radiacia.TestSocketServer;

import Radiacia.Client;
import Radiacia.data.ClientData;
import Radiacia.data.Data;
import Radiacia.handler.ClientHandler;

import java.io.IOException;

/**
 * Created by Cntgfy on 29.07.2016.
 */
class ListenThread extends Thread {
    ClientHandler clientHandler;
    Client client;

    ListenThread(ClientHandler clientHandler, Client client) {
        super("Listen thread");
        this.clientHandler = clientHandler;
        this.client = client;
        start();
    }

    @Override
    public void run() {
        try {
            Data data = client.read();
            if (data instanceof ClientData) {
                ClientData clientData = (ClientData) data;
                clientData.setOwner(client);
                clientHandler.add( (ClientData) data );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
