package Radiacia.server;

import Radiacia.base.Acceptable;
import Radiacia.server.client.GameClient;
import Radiacia.server.services.AccountService;

import java.io.IOException;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class GameServerListenThread extends Thread {
    private Acceptable acceptable;
    private AccountService accountService;

    public GameServerListenThread(Acceptable acceptable) {
        this(acceptable, false);
    }

    public GameServerListenThread(Acceptable acceptable, boolean startNow) {
        super("GameServerListenThread");
        this.acceptable = acceptable;
        this.accountService = new AccountService();

        if (startNow) start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                GameClient gameClient = new GameClient(acceptable.accept(), accountService);
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
