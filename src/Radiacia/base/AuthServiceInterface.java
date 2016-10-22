package Radiacia.base;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 *
 * Хранит подключенных к серверу клиентов
 */
public interface AuthServiceInterface {
    String getUserName(String sessionId);

    void saveUserName(String sessionId, String name);

    boolean containsSession(String sessionId);
}
