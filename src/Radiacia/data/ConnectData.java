package Radiacia.data;

import Radiacia.client.Client;

/**
 * Created by Cntgfy on 16.08.2016.
 */
public class ConnectData extends Data<ClientData, Client> {
    private long id;

    public ConnectData() {
        data = new ClientData(true);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setOwner(Client owner) {
        super.setOwner(owner);
        this.owner = owner;
    }
}
