package Radiacia.server.client;

import Radiacia.game.Gamer;
import Radiacia.data.GamerData;
import Radiacia.server.eventlisteners.GamerListener;

import java.io.IOException;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Переходник слиента к геймеру
 *
 * Обновляет состояние игрока
 */
public class ClientGamer extends Gamer {
    private transient volatile GameClient gc;
    private transient GamerListener gl;

    public ClientGamer(GameClient gc) {
        this.gc = gc;

        this.gl = new GamerListener(this);
        this.gc.getListenerThread().addListener(gl);
    }

    @Override
    public void setIsShoot(boolean isShoot) {
        super.setIsShoot(isShoot);
        writeSelf();
    }

    @Override
    public void hit() {
        super.hit();
        writeSelf();
    }

    private boolean isCatchIO = false;

    /**
     * Записывает свое состояние
     */
    public void writeSelf() {
        if (isCatchIO) return;

        try {
            Gamer gamer = new Gamer(this);
            gc.getClient().write(new GamerData(gamer));
        } catch (IOException e) {
            e.printStackTrace();
            isCatchIO = true;
        }
    }

    public void close() {
        gc.getListenerThread().removeListener(gl);
    }
}
