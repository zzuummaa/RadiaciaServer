package Radiacia.client;

import Radiacia.data.Data;
import Radiacia.handler.Handler;

import java.io.IOException;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Слушает клиента и передает данные на обработку
 */
public class ClientListenThread extends Thread {
    private Handler handler;
    private Client client;

    public ClientListenThread(Client client, Handler handler) {
        this.client = client;
        this.handler = handler;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Data data = client.read();

                if (isInterrupted()) break;

                handler.addInData(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
