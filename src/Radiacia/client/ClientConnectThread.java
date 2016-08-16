package Radiacia.client;

import Radiacia.data.ClientData;
import Radiacia.data.ConnectData;
import Radiacia.data.Data;

import java.io.IOException;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Пытается договориься с клиентом и в случае успеха сформировать данные о подключении
 */
public class ClientConnectThread extends Thread {
    private ConnectData connectData;
    private long id;
    private Client client;

    public ClientConnectThread(Client client) {
        this(client, 0);
    }

    public ClientConnectThread(Client client, long id) {
        this.client = client;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            tryToConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Обменивается информацией о подключении
     *
     * Пытается выяснить, какую информацию использовать при подключении
     * Использует полученные данные, если их <code>id != 0</code>
     *
     * @throws IOException
     */
    protected void tryToConnect() throws IOException {
        ConnectData outCD = new ConnectData();
        outCD.setId(id);
        outCD.setData(new ClientData(true));

        client.write(outCD);

        ConnectData inData;
        Data tmp = client.read();
        if (tmp instanceof ConnectData) {
            inData = (ConnectData) tmp;
            inData.setOwner(client);
        } else {
            return;
        }

        connectData = inData.getId() == 0 ? outCD : inData;

        client.connect();
    }

    public boolean isSuccess() {
        if (client.isConnected()) return true;
        else                      return false;
    }

    public ConnectData getConnectData() {
        return connectData;
    }
}
