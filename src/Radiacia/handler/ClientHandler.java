package Radiacia.handler;

import Radiacia.data.ClientData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Позволяет выполнять инструкции, предназначенные клиенту
 */
public class ClientHandler implements Handler<ClientData> {

    /**
     * Отключает клиента, если в его данных содержится эта инструкция
     *
     * @param data
     * @throws IOException
     */
    @Override
    public synchronized void handle(Collection<ClientData> data) throws IOException{
        Iterator<ClientData> iterator = data.iterator();

        while (iterator.hasNext()) {
            ClientData clientData = iterator.next();

            if (clientData.isDisconnect()) clientData.getOwner().disconnect();
        }
    }

    /**
     * @throws IOException при неудачном отключении клиента
     */
    @Override
    public void handle() throws IOException{
        synchronized (dataList) {
            handle(dataList);
            dataList.clear();
        }
    }

    private ArrayList<ClientData> dataList = new ArrayList<>();

    @Override
    public void add(ClientData clientData) {
        dataList.add(clientData);
    }

    public boolean withoutData() {
        return dataList.isEmpty();
    }
}
