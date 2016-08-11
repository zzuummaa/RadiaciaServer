package Radiacia.Game;

import java.io.Serializable;

/**
 * Created by Cntgfy on 02.07.2016.
 */
public class Gamer extends GameObject implements MayShoot, MayBeHit, OnSurfaceOfEarth, Serializable {
    private String name;
    private boolean isALive = true;
    private boolean isShoot = false;

    //Точность определения местоположения игрока в метрах
    private float accuracy = 9f;

    public Gamer() {
        name = "";
    }

    public Gamer(String name) {
        this.name = name;
    }

    public Gamer(Gamer gamer) {
        super(gamer);
        this.name = gamer.name;
        this.isALive = gamer.isALive;
        this.isShoot = gamer.isShoot;
        this.accuracy = gamer.accuracy;
    }

    public Gamer(String name, double latitude, double longitude, float direction) {
        super(latitude, longitude, direction);
        this.name = name;
    }

    /*
    * Устанавливает значения полей принадлежащих этому объекту на значения полей объекта gamer
    * */
    public void setGamer(Gamer gamer) {
        super.setGameObject(gamer);

        this.isShoot = gamer.isShoot();
        this.isALive = gamer.isALive();
        this.name = gamer.getName();
    }

    /*
    * Устанавливает значения всех полей, кроме поля name,
    * принадлежащих классу Gamer на те, которые содержатся в объекте gamer
    * */
    public void setGamerParams(Gamer gamer) {
        super.setGameObject(gamer);

        this.isShoot = gamer.isShoot();
        this.isALive = gamer.isALive();
        this.accuracy = gamer.getAccuracy();
    }

    public void setALive(boolean isALive) {
        this.isALive = isALive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isALive() {
        return isALive;
    }

    /*
    * Выстрел по игроку
    * вызывает метод попадания у игрока по которому стреляют, если попадание произошло
    * */
    @Override
    public void shoot(GameObject gameObject) {
        Shot shoot = new Shot(this);
        if (shoot.isHit(gameObject)) {
            gameObject.hit();
        }
    }

    @Override
    public void shoot(){
        isShoot = true;
    }

    @Override
    public boolean isShoot() {
        return isShoot;
    }

    @Override
    public void setIsShoot(boolean isShoot) {
        this.isShoot = isShoot;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    @Override
    public float getAccuracy() {
        return accuracy;
    }

    @Override
    public Shot getShot() {
        return new Shot(this);
    }

    /*
     * О боже! В нас попали! Мы истекаем кровью! Передайте, что я любил свою жену!
     */
    @Override
    public void hit() {
        isALive = false;
    }

    @Override
    public String toString() {
        return "name="      + name
       + " " + "isAlive="  + isALive
       + " " + "isShoot="  + isShoot
       + " " + "accuracy="  + accuracy
       + " " + super.toString();
    }
}
