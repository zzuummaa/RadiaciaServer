package Radiacia.handler;

import Radiacia.data.ClientData;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Позволяет выполнять инструкции, предназначенные клиенту
 *
 * Сломано
 */
public class ClientHandler implements Handler<ClientData> {
    public static final String NAME = "Client handler";

    /**
     * Отключает клиента, если в его данных содержится эта инструкция
     *
     * @param data
     * @throws IOException
     */
    @Override
    public synchronized void handle(Collection<ClientData> data) {
        Iterator<ClientData> iterator = data.iterator();

        while (iterator.hasNext()) {
            try {
                handle(iterator.next());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handle(ClientData clientData) throws IOException{
        if (clientData.isDisconnect()) {
            clientData.getOwner().disconnect();
            System.out.println(NAME + ": client disconnected");
        }
    }
}
