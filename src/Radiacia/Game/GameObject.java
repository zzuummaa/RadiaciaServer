package Radiacia.Game;

import java.io.Serializable;

/**
 * Created by Cntgfy on 04.07.2016.
 */
public class GameObject implements MayBeHit, Serializable {
    protected double latitude;
    protected double longitude;
    protected float direction;

    public GameObject() {
    }

    /*
    * direction в градусах [-180;180]
    * */
    public GameObject(double latitude, double longitude, float direction) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
    }

    public GameObject(GameObject gameObject) {
        this.latitude = gameObject.latitude;
        this.longitude = gameObject.longitude;
        this.direction = gameObject.direction;
    }

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

    @Override
    public String toString() {
        return "latitude="  + latitude
       + " " + "longitude=" + longitude
       + " " + "direction=" + direction;
    }
}
