package Radiacia.Game;

import Radiacia.handler.GameHandler;

import java.util.Collection;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Отвечает за механику игры
 */
public class GameMachine extends Thread {
    private Collection<Gamer> gamers;
    private int fps;

    public GameMachine() {
        this(null, 0, false);
    }

    public GameMachine(int fps) {
        this(null, fps, false);
    }

    public GameMachine(Collection<Gamer> gamers) {
        this(gamers, 0, false);
    }

    public GameMachine(Collection<Gamer> gamers, int fps, boolean startNow) {
        this.gamers = gamers;
        this.fps = fps;

        if (startNow) start();
    }

    @Override
    public void run() {
        GameFrame gf = new GameFrame();

        try {
            while (!isInterrupted()) {
                Thread.sleep(fps/1000);

                if (gf.isAlive()) continue;

                gf = new GameFrame();
            }
        } catch (InterruptedException e) {
            gf.interrupt();
        }
    }

    private class GameFrame extends Thread {
        public GameFrame() {
            start();
        }

        @Override
        public void run() {
            synchronized (gamers) {
                if (gamers == null) return;

                GameHandler gameHandler = new GameHandler(gamers);
            }
        }
    }

    public void setGamers(Collection<Gamer> gamers) {
        synchronized (gamers) {
            this.gamers = gamers;
        }
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getFps() {
        return fps;
    }
}
