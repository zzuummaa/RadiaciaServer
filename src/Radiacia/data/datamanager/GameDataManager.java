package Radiacia.data.datamanager;

import Radiacia.client.ClientListenThread;
import Radiacia.data.Data;
import Radiacia.data.GamerData;
import Radiacia.data.ShotData;
import Radiacia.handler.InitShotHandler;
import Radiacia.handler.ShotHitHandler;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Позволяет собирать данные клиентов и преобразовывать их в выходные данные
 *
 * Инструкция по применению:
 * 1. Собираем данные
 * @see #parseFromClient(java.util.Collection)
 * 2. Разбираем свои данные
 * @see #parseSelf()
 * 3. Получаем выходные данные
 * @see #getOutData()
 * 4. Перезапускаем класс
 * @see #reset()
 */
public class GameDataManager extends AbstractDataManager {
    private InitShotHandler ish = new InitShotHandler();
    private ShotHitHandler shh;

    private Collection<GamerData> gamerData = new LinkedList<>();
    private Collection<ShotData> shotData = new LinkedList<>();

    public GameDataManager() {
    }

    public void parseFromClient(ClientListenThread clth) {
        parseFromClient(clth.getData());
    }

    /**
     * Обрабатывает данные хранящиеся в коллекции входящих данных
     *
     * Находит последнего игрока и сохраняет его, снимает выстрелы всех игроков
     *
     * @param dataCollection
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
    public Collection<Data> parse(Collection<Data> dataCollection) {
        return super.parse(dataCollection);
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
    public void reset() {
        gamerData.clear();
        shotData.clear();
        shh = null;
    }
}
