package Radiacia.server.eventlisteners;

import Radiacia.server.client.Client;

/**
 * Created by Cntgfy on 20.08.2016.
 *
 * Генератор событий с возможностью переиспользования
 */
public class ReusableEventGenerator extends GameClientEventGenerator {
    public ReusableEventGenerator(Client client) {
        super(client);
    }

    public ReusableEventGenerator(Client client, DataListener... dls) {
        super(client, dls);
    }

    public void reconnect(Client client) {
        if (client == this.client) return;

        this.client = client;
        clth.interrupt();
        clth = new ClientListenThread();
        clth.start();
    }
}
