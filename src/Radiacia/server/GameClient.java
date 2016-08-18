package Radiacia.server;

import Radiacia.client.Client;
import Radiacia.client.ClientListenThread;
import Radiacia.data.ClientData;
import Radiacia.data.ConnectData;
import Radiacia.eventlisteners.DataListener;

import java.io.IOException;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Содержит информацию об игроке
 */
public class GameClient {
    private ConnectData conD;;
    private ClientListenThread clth;

    public GameClient(Client client) {
        this.conD = new ConnectData();

        this.conD.setOwner(client);
        this.clth = new ClientListenThread(client);
    }

    public void addListener(DataListener listener) {
        this.clth.addListener(listener);
    }

    public ClientListenThread getListenerThread() {
        return clth;
    }

    public long getId() {
        return conD.getId();
    }

    public void setId(long id) {
        this.conD.setId(id);
    }

    public Client getClient() {
        return conD.getOwner();
    }

    public ConnectData getConD() {
        return new ConnectData(conD);
    }

    public ClientData getClD() {
        return new ClientData(conD.getData());
    }

    /**
     * Соединяет клиента, когда договор об используемом id
     * уже составлен
     *
     * Пока считается, что параметр пренадлежит клиенту этого объекта
     *
     * @param cd
     * @throws IOException
     */
    public synchronized void connect(ConnectData cd) throws IOException {
        if (this.conD.getOwner() != cd.getOwner()) return;

        this.conD = new ConnectData(cd);

        conD.getOwner().connect();
    }
}
