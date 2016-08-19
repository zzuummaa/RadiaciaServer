package Radiacia.server;

import java.io.IOException;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class GameServerListenThread extends Thread {
    private Server server;
    private AccountService accountService;

    public GameServerListenThread(Server server) {
        this(server, false);
    }

    public GameServerListenThread(Server server, boolean startNow) {
        super("GameServerListenThread");
        this.server = server;
        this.accountService = new AccountService();

        if (startNow) start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                GameClient gameClient = new GameClient(server.accept(), accountService);
            } catch (IOException e) {
                if (!isInterrupted()) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    public AccountService getAccountService() {
        return accountService;
    }
}
