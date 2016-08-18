package Radiacia.data.datamanager;

import Radiacia.client.ClientListenThread;
import Radiacia.data.Data;
import Radiacia.data.GamerData;
import Radiacia.data.ShotData;
import Radiacia.handler.old.InitShotHandler;
import Radiacia.handler.old.ShotHitHandler;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Cntgfy on 17.08.2016.
 */
public class GameInDataManager extends AbstractDataManager {
    private InitShotHandler ish = new InitShotHandler();
    private ShotHitHandler shh;

    private Collection<GamerData> gamerData = new LinkedList<>();
    private Collection<ShotData> shotData = new LinkedList<>();

    public GameInDataManager() {
    }

    /**
     * Обрабатывает данные клиентской нити
     */
    public void parseFromClient(ClientListenThread clth) {
        parseFromClient(clth.getData());
    }

    /**
     * Обрабатывает данные хранящиеся в коллекции входящих данных
     *
     * Находит последнего игрока и сохраняет его, снимает выстрелы всех игроков
     *
     * @param dataCollection коллекция входных данных одного клиента
     */
    public void parseFromClient(Collection<Data> dataCollection) {
        if (dataCollection == null) return;

        GamerData lastData = null;

        Iterator<Data> iterator = dataCollection.iterator();
        while (iterator.hasNext()) {
            Data data = iterator.next();
            if (data instanceof GamerData) {
                GamerData gd = (GamerData) data;

                parse(gd);
                lastData = gd;
            }
        }

        if (lastData != null) gamerData.add(lastData);
    }

    public void parseSelf() {
        shh = new ShotHitHandler(gamerData);
        shh.handle(shotData);
    }

    @Override
    public void parse(Data data) {
        if (data instanceof GamerData) parse((GamerData) data);
    }

    public void parse(GamerData gamerData) {
        ShotData sd = ish.handle(gamerData);
        if (sd != null) shotData.add(sd);
    }

    public Collection<GamerData> getOutData() {
        return shh == null ? new LinkedList<>() : shh.getAllOutData();
    }

    /**
     * Сбросить данные
     */
    @Override
    public void reset() {
        gamerData.clear();
        shotData.clear();
        shh = null;
    }
}
