package Radiacia.server.client;

import Radiacia.data.ConnectData;
import Radiacia.game.Gamer;
import Radiacia.server.eventlisteners.ConnectListener;
import Radiacia.server.services.AccountService;

import java.util.*;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Адаптер для преобразования клиентов в геймеров
 *
 * Работать только со следующими методами:
 * @see #iterator()
 */
public class ClientsGamers implements Set<Gamer> {
    private AccountService as;
    private Map<GameClient, ClientGamer> mgccg;
    private HashSet<Gamer> hs;
    private ConnectListener cl;

    public ClientsGamers(AccountService as) {
        this.hs = new HashSet<>();
        this.mgccg = new HashMap<>();

        this.as = as;
        this.cl = new ClientsGamersListener();
        as.addConnectListener(this.cl);
    }

    /**
     * Создает выходное множество на основе игроков,
     * которые добавляются с помощью методов множества
     * и игроков, созданных на основе клиентов AccountService
     */
    private HashSet<Gamer> buildOut() {
        HashSet<Gamer> out = new HashSet<>(hs);
        out.addAll(mgccg.values());

        return out;
    }

    public void add(GameClient gameClient) {
        add(new ClientGamer(gameClient));
    }

    @Override
    public Iterator<Gamer> iterator() {
        return buildOut().iterator();
    }

    /**
     * Добавляет GameClient к себе в карту, если к
     * AccountService подключился новый GameClient
     */
    private class ClientsGamersListener extends ConnectListener {

        private ClientsGamersListener() {
            super(null);
        }

        @Override
        public void onConnect(ConnectData cd) {
            GameClient gc = as.getClients().get(cd.getId());

            if (mgccg.containsKey(gc)) mgccg.remove(gc).close();
            mgccg.put(gc, new ClientGamer(gc));
        }
    }

    public void closeAll() {
        as.removeConnectListener(cl);
        this.cl = null;

        Iterator<Gamer> iterator = iterator();

        while (iterator.hasNext()) {
            Gamer gamer = iterator.next();

            if (gamer instanceof ClientGamer) {
                ((ClientGamer) gamer).close();
            }
        }
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Gamer gamer) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Gamer> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int size() {
        return buildOut().size();
    }

    @Override
    public boolean isEmpty() {
        return buildOut().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }
}
