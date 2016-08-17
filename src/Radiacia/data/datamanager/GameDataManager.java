package Radiacia.data.datamanager;

import Radiacia.client.ClientListenThread;
import Radiacia.data.Data;
import Radiacia.data.GamerData;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Позволяет собирать данные клиентов и преобразовывать их в выходные данные
 *
 */
public class GameDataManager extends AbstractDataManager {
    private GameInDataManager gidm;
    private GameOutDataManager godm;

    //private Collection<GamerData> gamerData;
    //private Collection<ShotData> shotData;

    public GameDataManager() {
        gidm = new GameInDataManager();
        godm = new GameOutDataManager();
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
        gidm.parseFromClient(dataCollection);
    }

    public void parseSelf() {
        gidm.parseSelf();
        Collection<GamerData> gamerData = gidm.getOutData();

        Iterator<GamerData> iterator = gamerData.iterator();
        while (iterator.hasNext()) godm.parse(iterator.next());
    }

    @Override
    public void parse(Data data) {
        gidm.parse(data);
    }

    /**
     * Сбросить данные
     */
    public void reset() {
        gidm.reset();
    }
}
