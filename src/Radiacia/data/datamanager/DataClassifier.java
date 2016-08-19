package Radiacia.data.datamanager;

import Radiacia.data.ClientData;
import Radiacia.data.Data;
import Radiacia.data.GamerData;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Классифицирует данные
 */
public class DataClassifier {
    private List<Data> outData;

    public DataClassifier() {
        this.outData = new LinkedList<>();
    }

    public Collection<Data> parseFromClient(Collection<Data> inData) {
        Iterator<Data> iterator = inData.iterator();

        while (iterator.hasNext()) {
            Data data = iterator.next();

            parseFromClient(data);
        }

        if (gamerData != null) {
            outData.add(0, gamerData);
        }

        return outData;
    }

    public void parseFromClient(Data data) {
        if (data instanceof ClientData) outData.add(data);
        else if (data instanceof GamerData) add((GamerData) data);
        else outData.add(data);
    }

    private GamerData gamerData;

    public void add(GamerData gamerData) {
        if (gamerData.getData().isShoot()) {
            outData.add(gamerData);
            this.gamerData = null;
        } else {
            this.gamerData = gamerData;
        }
    }

    public Collection<Data> getOutData() {
        return new LinkedList<>(outData);
    }

    public void reset() {
        outData.clear();
    }
}
