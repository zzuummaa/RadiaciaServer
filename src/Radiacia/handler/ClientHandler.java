package Radiacia.handler;

import Radiacia.data.ClientData;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Позволяет выполнять инструкции, предназначенные клиенту
 *
 */
public class ClientHandler extends Handler<ClientData,ClientData> {
    public static final String NAME = "Client handler";

    public ClientHandler(Collection<ClientData> clientData) {
        handle(clientData);
    }

    @Override
    public ClientData handle(ClientData clientData) {
        if (clientData == null) return null;

        if (clientData.isDisconnect()) {
            try {
                clientData.getOwner().disconnect();
                System.out.println(NAME + ": client disconnected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ClientData(clientData);
    }
}
