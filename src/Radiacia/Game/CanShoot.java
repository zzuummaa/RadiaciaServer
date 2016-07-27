package Radiacia.Game;

/**
 * Created by Cntgfy on 04.07.2016.
 */
public interface CanShoot {
    public boolean isShoot();

    public void setIsShoot(boolean isShoot);

    public Shoot getShoot();

    public void shoot();

    public void shoot(GameObject gameObject);
}
