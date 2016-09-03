package Radiacia.base;

import java.util.Collection;

/**
 * Created by Cntgfy on 02.09.2016.
 *
 * Отвечает за подключение клиентов
 */
public interface AccountServiceInterface {
    /**
     * Проверяет, подключен ли клиент с данным id
     */
    public boolean contains(Long id);

    /**
     * @return известные аккаунты
     */
    public Collection<AccountInterface> getAccounts();
}
