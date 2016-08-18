package Radiacia.eventlisteners;

import Radiacia.client.Client;
import Radiacia.data.ClientData;
import Radiacia.data.ConnectData;
import Radiacia.data.Data;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public abstract class ClientDataListener implements DataListener {
    protected Client client;

    protected ClientDataListener(Client client) {
        this.client = client;
    }

    @Override
    public void initEvent(Data data) {
        if (data instanceof ClientData) {
            data.setOwner(client);
            onClientData((ClientData) data);
        }
        if (data instanceof ConnectData) {
            data.setOwner(client);
            onConnect((ConnectData) data);
        }
    }

    /**
     * Приходит, когда клиент хочет подключиться
     */
    public abstract void onConnect(ConnectData cd);

    public abstract void onClientData(ClientData clientData);
}
