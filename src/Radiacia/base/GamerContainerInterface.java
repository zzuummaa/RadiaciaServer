package Radiacia.base;

import java.util.Collection;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public interface GamerContainerInterface {
    Collection<GamerInterface> asCollection();

    boolean add(GamerInterface gamer);

    boolean remove(GamerInterface gamer);
}
