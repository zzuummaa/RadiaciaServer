package Radiacia.base;

/**
 * Created by Cntgfy on 03.09.2016.
 *
 * Авторизует и регистрирует пользователей
 */
public interface LoginServiceInterface {
    public boolean signIn(String name, String pass);

    public boolean signUp(String name, String pass);
}
