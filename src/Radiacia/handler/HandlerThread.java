package Radiacia.handler;

import sun.security.util.Debug;

/**
 * Created by Cntgfy on 30.07.2016.
 *
 * Позволяет в отлельном потоке обрабатывать данные с помощтю <code>Handler</code>
 */
public class HandlerThread extends Thread {
    public final static String NAME = "Handler thread";

    public HandlerThread(Handler handler) {
        super(NAME);
        this.handler = handler;
    }

    public HandlerThread(Handler handler, String name) {
        super(name);
        this.handler = handler;
    }

    public HandlerThread(Handler handler, Runnable target) {
        super(target, NAME);
        this.handler = handler;
    }

    public HandlerThread(Handler handler, Runnable target, String name) {
        super(target, name);
        this.handler = handler;
    }

    private Handler handler;

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                if (handler.containsNotHandle()) handler.handle();
                else                                     yield();
            } catch (Exception e) {
                Debug.println(getName(), "handle exception");
                e.printStackTrace();
                interrupt();
            }
        }
    }
}
