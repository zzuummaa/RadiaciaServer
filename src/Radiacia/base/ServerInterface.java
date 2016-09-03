package Radiacia.base;

/**
 * Created by Cntgfy on 03.09.2016.
 */
public interface ServerInterface {
    public EventGeneratorInterface getEventGenerator();

    /**
     * Закрывает сервер
     *
     * @return true  - если закрытие удачно
     *         false - если произошли ошибки
     */
    public boolean close();
}
