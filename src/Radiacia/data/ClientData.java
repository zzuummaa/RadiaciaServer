package Radiacia.data;

import Radiacia.server.client.Client;

/**
 * Created by Cntgfy on 24.07.2016.
 *
 * Данные, придназначеные для управления состоянием клиента
 *
 */
public class ClientData extends Data<Boolean, Client> {
    {
        data = true;
    }

    public ClientData() {
    }

    public ClientData(boolean isConnected) {
        data = isConnected;
    }

    public ClientData(ClientData clientData) {
        super(clientData);
    }

    /**
     * @param data true - если клиент подключен
     *             false- если клиента следует отключить
     */
    @Override
    public void setData(Boolean data) {
        super.setData(data);
    }

    /**
     * @return значение, передаваемое методом <code>setData()</code>
     * @see #setData(Boolean)
     */
    @Override
    public Boolean getData() {
        return super.getData();
    }

    public boolean isDisconnect() {
        return !data;
    }
}
