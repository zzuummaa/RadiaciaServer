package Radiacia.handler;

import Radiacia.data.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cntgfy on 28.07.2016.
 *
 * Не доработан
 */
public class MultiHandler implements Handler<Data, Data> {
    private Map<Class, CollectionHandler> handlers = new HashMap<>();

    @Override
    public Data handle(Data data) {
        if (handlers.containsKey(data.getClass())) {
            return handlers.get(data.getClass()).handle(data);
        } else {
            return null;
        }
    }

    @Override
    public void addInData(Data data) {
        if (handlers.containsKey(data.getClass())) {
            handlers.get(data.getClass()).addInData(data);
        }
    }

    @Override
    public Data getOutData() {
        return null;
    }

    @Override
    public void handle() {
        for (CollectionHandler handler: handlers.values()) {
            handler.handle();
        }
    }

    public void addHandler(CollectionHandler collectionHandler, Class dataClass) {
        handlers.put(dataClass, collectionHandler);
    }

    public Handler getHandler(int i) {
        return handlers.get(i);
    }
}
