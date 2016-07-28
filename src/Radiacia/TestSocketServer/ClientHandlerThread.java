package Radiacia.TestSocketServer;

import Radiacia.handler.ClientHandler;

import java.io.IOException;

/**
 * Created by Cntgfy on 29.07.2016.
 */
class ClientHandlerThread extends Thread {
    ClientHandler clientHandler;

    ClientHandlerThread(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                if (clientHandler.withoutData()) continue;
                else                             clientHandler.handle();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
