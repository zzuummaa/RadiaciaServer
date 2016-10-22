package Radiacia.base;

/**
 * Created by Fomenko_S.V. on 3.09.2016.
 *
 * Авторизует и регистрирует пользователей
 */
public interface LoginServiceInterface {
    public boolean signIn(String name, String pass);

    public boolean signUp(String name, String pass);
}
