package Radiacia.tests;

import Radiacia.game.Gamer;
import Radiacia.server.GameServer;
import Radiacia.server.client.ClientGamer;
import Radiacia.server.client.ClientsGamers;
import Radiacia.server.client.GameClient;
import Radiacia.server.client.SocketClient;
import Radiacia.server.services.AccountService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ClientGamerTest {
    PrintStream error;
    PrintStream out;

    Thread mainGUI;
    Thread handler;

    /**
     * Классы сервера
     */
    GameServer gs;
    ClientsGamers csgs;
    GameClient sgc;
    ClientGamer scg;

    /**
     * Клиентские классы
     */
    volatile ClientGamer ccg;
    GameClient cgc;

    @Before
    public void setUp() throws IOException, InterruptedException {
        error = System.err;
        out = System.out;

        System.setErr(new PrintStream(new ByteArrayOutputStream()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));

        gs = new GameServer();
        AccountService as = gs.getSlth().getAccountService();
        csgs = new ClientsGamers(as);

        cgc = new GameClient(new SocketClient(new Socket("localHost", 9090)));
        cgc.connect();

        ccg = new ClientGamer(cgc);

        Thread.sleep(10);
        //sgc = as.getClients().values().iterator().next();
        scg = (ClientGamer) csgs.iterator().next();

        mainGUI = new Thread(new Runnable() {
            boolean isInterrupted = false;
            @Override
            public void run() {
                while (!isInterrupted) {
                    synchronized (ccg) {
                        ccg.writeSelf();
                    }
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        handler = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ccg) {
                    ccg.setIsShoot(true);
                }
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        cgc.close();
        gs.close();
    }

    @Test
    public void testConstructor() {
        ClientGamer ccg = new ClientGamer(cgc);
        ccg.close();
    }

    @Test
    public void testSetIsShoot() throws Exception {
        ccg.setIsShoot(true);
        Thread.sleep(10);
        Assert.assertEquals(ccg.isShoot(), scg.isShoot());

        ccg.setIsShoot(false);
        Thread.sleep(10);
        Assert.assertEquals(ccg.isShoot(), scg.isShoot());
    }

    @Test
    public void testWriteSelf() throws InterruptedException {
        ccg.setName("test");
        ccg.writeSelf();

        Thread.sleep(10);
        Assert.assertEquals(ccg.getName(), scg.getName());
    }

    /**
     * Тестирование периодического пересылания данных в двух потоках
     */
    @Test
    public void asyncAndroidTest() throws IOException, InterruptedException {
        mainGUI.start();
        Thread.sleep(100);

        handler.start();
        Thread.sleep(20);

        mainGUI.interrupt();
        handler.interrupt();
        Thread.sleep(10);

        Gamer excepted = csgs.iterator().next();
        Gamer actual = ccg;
        System.setOut(out);
        Assert.assertEquals("Client gamer value invalid", true, actual.isShoot());
        Assert.assertEquals("Server gamer value invalid", true, excepted.isShoot());
    }
}