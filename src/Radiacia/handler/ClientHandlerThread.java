package Radiacia.handler;

/**
 * Created by Cntgfy on 29.07.2016.
 */
public class ClientHandlerThread extends HandlerThread {
    private ClientHandler clientHandler;

    public ClientHandlerThread(Handler handler, ClientHandler clientHandler) {
        super(handler);
        this.clientHandler = clientHandler;
    }
}
