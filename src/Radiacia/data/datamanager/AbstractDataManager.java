package Radiacia.data.datamanager;

import Radiacia.data.Data;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Разбирает коллекцию данных и передает в данные в handler
 *
 * Инструкция по применению:
 * 1. Собираем коллекции клиентов
 * @see #parseFromClient(java.util.Collection)
 * 2. Разбираем свои данные
 * @see #parseSelf()
 * 3. Перезапускаем класс
 * @see #reset()
 */
public abstract class AbstractDataManager {
    private Collection<Data> parsedData;

    public abstract void parseFromClient(Collection<Data> data);

    public Collection<Data> parse(Collection<Data> dataCollection) {
        parsedData = new LinkedList<>();
        Iterator<Data> iterator = dataCollection.iterator();

        while (iterator.hasNext()) {
            Data data = iterator.next();
            parse(data);
        }

        return parsedData;
    }

    public abstract void parseSelf();

    public abstract void parse(Data data);

    public abstract void reset();
}
