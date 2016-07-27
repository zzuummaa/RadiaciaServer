package Radiacia;

import Radiacia.data.ClientData;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Cntgfy on 27.07.2016.
 */
public class ClientHandler implements Handler<ClientData> {

    /**
     * @param data
     * @throws IOException
     *         @see #handle(Radiacia.data.ClientData)
     */
    @Override
    public void handle(Collection<ClientData> data) throws IOException{
        Iterator<ClientData> iterator = data.iterator();

        while (iterator.hasNext()) handle(iterator.next());
    }

    /**
     * @param data
     * @throws IOException при неудачном отключении клиента
     */
    @Override
    public void handle(ClientData data) throws IOException{
        if (!data.isConnected) data.owner.disconnect();
    }
}
