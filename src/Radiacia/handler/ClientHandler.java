package Radiacia.handler;

import Radiacia.data.ClientData;
import Radiacia.data.GamerData;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Позволяет выполнять инструкции, предназначенные клиенту
 *
 */
public class ClientHandler extends CollectionHandler<ClientData,ClientData> {
    public static final String NAME = "Client handler";

    public ClientHandler() {
    }

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

    /**
     * Записывает состояние игрока, если он умер
     *
     * @param gamerData
     * @return
     */
    public GamerData handle(GamerData gamerData) {
        if (gamerData == null) return null;

        if (!gamerData.getData().isALive()) {
            try {
                gamerData.getOwner().write(gamerData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new GamerData(gamerData.getData(), gamerData.getOwner());
    }
}
