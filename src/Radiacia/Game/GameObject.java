package Radiacia.Game;

/**
 * Created by Cntgfy on 04.07.2016.
 */
public class GameObject implements CanHitted {
    protected double latitude;
    protected double longitude;
    protected float direction;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public float getDirection() {
        return direction;
    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public void setGameObject(GameObject gameObject) {
        this.latitude = gameObject.getLatitude();
        this.longitude = gameObject.getLongitude();
        this.direction = gameObject.getDirection();
    }

    @Override
    public void hit() {

    }
}
