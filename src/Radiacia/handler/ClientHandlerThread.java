package Radiacia.handler;

/**
 * Created by Cntgfy on 29.07.2016.
 */
public class ClientHandlerThread {
    private ClientHandler clientHandler;

    public ClientHandlerThread(Handler handler, ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }
}
