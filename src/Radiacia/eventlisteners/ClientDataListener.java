package Radiacia.eventlisteners;

import Radiacia.data.ClientData;
import Radiacia.data.ConnectData;
import Radiacia.data.Data;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public abstract class ClientDataListener implements DataListener {
    @Override
    public void initEvent(Data data) {
        if (data instanceof ClientData) onClientData((ClientData) data);
        if (data instanceof ConnectData) onConnect((ConnectData) data);
    }

    /**
     * Приходит, когда клиент хочет подключиться
     */
    public abstract void onConnect(ConnectData cd);

    public abstract void onClientData(ClientData clientData);
}
