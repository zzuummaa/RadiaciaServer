package Radiacia.eventlisteners;

import Radiacia.client.Client;
import Radiacia.data.ClientData;
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
            initClientEvent((ClientData) data);
        }
    }

    public abstract void initClientEvent(ClientData cd);
}
