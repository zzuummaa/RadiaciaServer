package Radiacia;

import Radiacia.data.Data;
import Radiacia.data.MultiData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Контейнер исполнителей, позволяющий управлять сразу несколькими
 */
public class MultiHandler implements Handler<MultiData> {
    private final List<Handler> handlers = new ArrayList<>();

    public MultiHandler() {
    }

    /**
     * Служит для редактирования списка исполнителей
     *
     * @return лист исполнителей.
     */
    public List<Handler> handlers() {
        return handlers;
    }

    /**
     * @param data
     */
    @Override
    public void handle(Collection<MultiData> data) throws Exception{
        Iterator<MultiData> iterator = data.iterator();

        while (iterator.hasNext()) handle(iterator.next());
    }

    @Override
    public void handle(MultiData data) throws Exception{
        for (int i = 0; i < handlers.size(); i++) {
            handlers.get(i).handle(data.data[i]);
        }
    }

    /**
     * Вытаскивает из коллекции MultiData данные с заданным номером и составляет из них коллекцию
     *
     * @param multiData коллекция данных
     * @param number заданный номер
     * @return коллекция данных с заданным номером
     */
    private Collection<Data> singleData(Collection<MultiData> multiData, int number) {
        Collection<Data> data = new ArrayList<>(multiData.size());
        Iterator<MultiData> iterator = multiData.iterator();

        for (int i = 0; i < multiData.size(); i++) {
            data.add(iterator.next().data[number]);
        }

        return data;
    }
}
