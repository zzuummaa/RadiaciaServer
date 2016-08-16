package Radiacia.data.datamanager;

import Radiacia.data.Data;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Разбирает коллекцию данных и передает в данные в handler
 */
public abstract class AbstractDataManager {
    private Collection<Data> parsedData;

    public Collection<Data> parse(Collection<Data> dataCollection) {
        parsedData = new LinkedList<>();
        Iterator<Data> iterator = dataCollection.iterator();

        while (iterator.hasNext()) {
            Data data = iterator.next();
            parse(data);
        }

        return parsedData;
    }

    public abstract void parse(Data data);
}
