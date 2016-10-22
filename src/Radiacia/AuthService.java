package Radiacia;

import Radiacia.base.AuthServiceInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
public class AuthService implements AuthServiceInterface {
    private Map<String, String> users = new HashMap<>();

    @Override
    public String getUserName(String sessionId) {
        return users.get(sessionId);
    }

    @Override
    public void saveUserName(String sessionId, String name) {
        users.put(sessionId, name);
    }

    @Override
    public boolean containsSession(String sessionId) {
        return users.containsKey(sessionId);
    }
}
