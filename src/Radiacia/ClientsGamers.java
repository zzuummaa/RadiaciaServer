package Radiacia;

import Radiacia.Game.Gamer;
import Radiacia.server.AccountService;
import Radiacia.server.GameClient;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Адаптер для преобразования клиентов в геймеров
 *
 * Работать только со следующими методами:
 * @see #iterator()
 */
public class ClientsGamers extends HashSet<Gamer> {
    private AccountService as;
    private Set<GameClient> scg;

    public ClientsGamers(AccountService as) {
        this.as = as;
        this.scg = new HashSet<>();
    }

    public void add(GameClient gameClient) {
        add(new ClientGamer(gameClient));
    }

    public void closeAll() {
        Iterator<Gamer> iterator = iterator();

        while (iterator.hasNext()) {
            Gamer gamer = iterator.next();

            if (gamer instanceof ClientGamer) {
                ((ClientGamer) gamer).close();
            }
        }
    }

    private void addFromAccountService() {
        Iterator<GameClient> gci = as.getClients().values().iterator();

        while (gci.hasNext()) {
            GameClient gc = gci.next();
            if (!scg.contains(gc)) {
                ClientGamer clientGamer = new ClientGamer(gc);
                scg.add(gc);
                add(clientGamer);
            }
        }
    }

    @Override
    public Iterator<Gamer> iterator() {
        addFromAccountService();
        return super.iterator();
    }
}
