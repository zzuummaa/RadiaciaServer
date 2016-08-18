package Radiacia;

import Radiacia.Game.Gamer;
import Radiacia.server.GameClient;
import Radiacia.server.GameServerListenThread;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Адаптер для преобразования клиентов в геймеров
 *
 * Работайте только со следующими методами:
 * @see #iterator()
 */
public class ClientsGamers implements Collection<Gamer> {
    private GameServerListenThread slth;
    private Collection<GameClient> gcc;

    /**
     * Менеджит клиентов с нити и преобразует их в геймеров.
     * Реализует связь клиента с его геймером
     */
    private Map<GameClient, Gamer> cgm;
    private Collection<Gamer> cgc;

    public ClientsGamers(GameServerListenThread slth) {
        this.slth = slth;
        this.gcc = slth.getAccountService().getClients().values();

        this.cgm = new HashMap<>();
        this.cgc = cgm.values();
    }

    /**
     * Добавляет клиентов с нити к коллекции, если их нету в коллекции.
     */
    private void addFromThread() {
        Iterator<GameClient> gci = gcc.iterator();

        while (gci.hasNext()) {
            GameClient gc = gci.next();

            if (!cgm.containsKey(gci.next())) {
                cgm.put(gc, new ClientGamer(gc));
            }
        }
    }

    @Override
    public int size() {
        addFromThread();
        return cgc.size();
    }

    @Override
    public boolean isEmpty() {
        addFromThread();
        return cgc.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        addFromThread();
        return cgc.contains(o);
    }

    @Override
    public Iterator<Gamer> iterator() {
        addFromThread();
        return cgc.iterator();
    }

    @Override
    public Object[] toArray() {
        addFromThread();
        return cgc.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        addFromThread();
        return cgc.toArray(a);
    }

    @Override
    public boolean add(Gamer gamer) {
        addFromThread();
        return cgc.add(gamer);
    }

    /**
     * Не работает с серверными объектами
     */
    @Override
    public boolean remove(Object o) {
        return cgc.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        addFromThread();
        return cgc.containsAll(c);
    }

    /**
     * Не работает с серверными объектами
     */
    @Override
    public boolean addAll(Collection<? extends Gamer> c) {
        addFromThread();
        return cgc.addAll(c);
    }

    /**
     * Не работает с серверными объектами
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return cgc.removeAll(c);
    }

    /**
     * Не работает с серверными объектами
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return cgc.retainAll(c);
    }

    @Override
    public void clear() {
        addFromThread();
        cgc.clear();
    }

    @Override
    public boolean equals(Object o) {
        addFromThread();
        return cgc.equals(o);
    }

    @Override
    public int hashCode() {
        addFromThread();
        return cgc.hashCode();
    }
}
