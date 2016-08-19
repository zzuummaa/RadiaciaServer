package Radiacia.eventlisteners;

import Radiacia.client.Client;
import Radiacia.data.ClientData;
import Radiacia.data.ConnectData;

/**
 * Created by Cntgfy on 19.08.2016.
 */
public abstract class ConnectListener extends ClientDataListener {
    public ConnectListener(Client client) {
        super(client);
    }

    @Override
    public void initClientEvent(ClientData cd) {
        if (cd instanceof ConnectData) initConnectEvent((ConnectData) cd);
    }

    public void initConnectEvent(ConnectData cd) {
        onConnect(cd);
    }

    public abstract void onConnect(ConnectData cd);
}
