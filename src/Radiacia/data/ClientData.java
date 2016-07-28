package Radiacia.data;

/**
 * Created by Cntgfy on 24.07.2016.
 *
 * Данные, придназначеные для управления состоянием клиента
 *
 * version 0.5
 */
public class ClientData implements Data {
    private boolean disconnect = false;

    /**
     * @return инструкцию
     *         @see #disconnect(boolean)
     */
    public boolean disconnect() {
        return disconnect;
    }

    /**
     * @param disconnect инструкция для клиента
     *                   true - отключить
     *                   false - не отключать
     */
    public void disconnect(boolean disconnect) {
        this.disconnect = disconnect;
    }

    //Данные, необходимые для отключения клиента
    transient public final static ClientData DISCONNECT_DATA;
    static {
        DISCONNECT_DATA = new ClientData();
        DISCONNECT_DATA.disconnect(true);
    }
}
