package Radiacia.base;

import java.util.Collection;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
public interface GamerContainerInterface {
    Collection<GamerInterface> asCollection();

    boolean add(GamerInterface gamer);

    boolean remove(GamerInterface gamer);
}
