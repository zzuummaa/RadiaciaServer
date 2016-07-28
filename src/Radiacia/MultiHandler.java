package Radiacia;

import Radiacia.data.Data;
import Radiacia.handler.Handler;

import java.util.Collection;
import java.util.List;

/**
 * Created by Cntgfy on 28.07.2016.
 */
public class MultiHandler implements Handler {
    public List<Handler> handlers() {
        return null;
    }

    @Override
    public void add(Data data) {

    }

    @Override
    public void handle(Collection data) throws Exception {

    }

    @Override
    public void handle() throws Exception {

    }
}
