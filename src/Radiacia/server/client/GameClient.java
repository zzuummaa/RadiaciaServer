package Radiacia.server.client;

import Radiacia.data.ClientData;
import Radiacia.data.ConnectData;
import Radiacia.server.eventlisteners.GameClientConnectListener;
import Radiacia.server.eventlisteners.DataListener;
import Radiacia.server.eventlisteners.GameClientListener;
import Radiacia.server.services.AccountService;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Содержит информацию об игроке
 */
public class GameClient {
    private ConnectData conD;;
    private ClientListenThread clth;
    private Set<DataListener> tmpListeners;

    public GameClient(Client client) {
        this(client, null);
    }

    public GameClient(Client client, long id) {
        this(client, null);
        this.conD.setId(id);
    }

    public GameClient(Client client, AccountService accountService) {
        this.conD = new ConnectData();
        this.conD.setOwner(client);

        if (accountService != null) {
            this.clth = new ClientListenThread(client, new GameClientConnectListener(this, accountService));
        } else {
            this.clth = new ClientListenThread(client, new GameClientConnectListener(this));
        }
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

    public void setConD(ConnectData conD) {
        this.conD = conD;
    }

    public void connect() throws IOException {
        conD.getOwner().write(conD);
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

        if (tmpListeners == null) {
            clth.addListener(new GameClientListener());
        } else {
            clth.setListeners(tmpListeners);
            tmpListeners = null;
        }
    }

    public void close() throws IOException {
        clth.interrupt();
        conD.getOwner().disconnect();

        clth = null;
    }
}
