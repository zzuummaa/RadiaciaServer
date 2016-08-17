package Radiacia.client;

import Radiacia.data.ClientData;
import Radiacia.data.ConnectData;
import Radiacia.data.Data;
import Radiacia.server.ClientManager;

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

    private ClientManager clientManager;

    public ClientConnectThread(Client client) {
        this(client, 0);
    }

    public ClientConnectThread(Client client, long id) {
        this.client = client;
        this.id = id;
    }

    public ClientConnectThread(Client client, ClientManager clientManager, long id) {
        this(client, id);
        this.clientManager = clientManager;
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
        ConnectData outCD = writeConnectData();
        ConnectData inCD = readConnectData(client);

        connectData = inCD.getId() == 0 ? outCD : inCD;
        connectData.setOwner(client);

        client.connect();

        if (clientManager != null) {
            clientManager.put(this);

        }
    }

    private ConnectData writeConnectData() throws IOException {
        ConnectData outCD = new ConnectData();
        outCD.setId(id);
        outCD.setData(new ClientData(true));

        client.write(outCD);

        return outCD;
    }

    private ConnectData readConnectData(Client client) throws IOException {
        ConnectData inData = null;
        Data tmp = client.read();

        if (tmp instanceof ConnectData) {
            inData = (ConnectData) tmp;
        }

        return inData;
    }

    public boolean isSuccess() {
        if (client.isConnected()) return true;
        else                      return false;
    }

    public ConnectData getConnectData() {
        return connectData;
    }
}
