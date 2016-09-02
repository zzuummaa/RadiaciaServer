package Radiacia.server.eventlisteners;

import Radiacia.base.DataListenerInterface;
import Radiacia.server.client.Client;
import Radiacia.data.ClientData;
import Radiacia.data.Data;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public abstract class ClientDataListener implements DataListenerInterface {
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
