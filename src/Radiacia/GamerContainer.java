package Radiacia;

import Radiacia.base.GamerContainerInterface;
import Radiacia.base.GamerInterface;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public class GamerContainer implements GamerContainerInterface {
    private Set<GamerInterface> gamers = new HashSet<>();

    @Override
    public Collection<GamerInterface> asCollection() {
        return gamers;
    }

    @Override
    public boolean add(GamerInterface gamer) {
        return gamers.add(gamer);
    }

    @Override
    public boolean remove(GamerInterface gamer) {
        return gamers.remove(gamer);
    }
}
