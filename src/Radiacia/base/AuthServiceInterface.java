package Radiacia.base;

/**
 * Created by Cntgfy on 19.10.2016.
 *
 * Хранит подключенных к серверу клиентов
 */
public interface AuthServiceInterface {
    String getUserName(String sessionId);

    void saveUserName(String sessionId, String name);

    boolean containsSession(String sessionId);
}
