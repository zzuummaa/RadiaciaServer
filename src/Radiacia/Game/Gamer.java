package Radiacia.Game;

/**
 * Created by Cntgfy on 02.07.2016.
 */
public class Gamer extends GameObject implements CanShoot {
    private String name;
    private boolean isALive;


    private boolean isShoot;

    public Gamer() {
        name = "";
    }

    public Gamer(String name) {
        this.name = name;
        this.isALive = true;
    }

    public Gamer(Gamer gamer) {
        this.name = gamer.name;
        this.isALive = gamer.isALive();
        this.latitude = gamer.getLatitude();
        this.longitude = gamer.getLongitude();
        this.direction = gamer.getDirection();

        this.isShoot = gamer.isShoot;
    }

    /*
    * direction в градусах [-180;180]
    * */
    public Gamer(String name, double latitude, double longitude, float direction) {
        this.name = name;
        this.isALive = true;
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
        this.isShoot = false;
    }

    /*
    * Устанавливает значения полей принадлежащих классу Gamer на те, которые содержатся в объекте gamer
    * */
    public void setGamer(Gamer gamer) {
        super.setGameObject(gamer);

        this.isShoot = gamer.isShoot();
        this.isALive = gamer.isALive();
        this.name = gamer.name;
    }

    /*
    * Устанавливает значения всех полей, кроме поля name,
    * принадлежащих классу Gamer на те, которые содержатся в объекте gamer
    * */
    public void setGamerParams(Gamer gamer) {
        super.setGameObject(gamer);

        this.isShoot = gamer.isShoot();
        this.isALive = gamer.isALive();
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

    @Override
    public String toString() {
        return "name="     + name
            + " isAlive="  + isALive
            + " latitude=" + latitude
            + " longitude="+ longitude
            + " direction="+ direction
            + " isShoot="  + isShoot;
    }

    /*
    * Выстрел по игроку
    * вызывает метод попадания у игрока по которому стреляют, если попадание произошло
    * */
    public void shoot(GameObject gameObject) {
        Shoot shoot = new Shoot(latitude, longitude, direction, this);
        if (shoot.isHit(gameObject)) {
            gameObject.hit();
        }
    }

    public void shoot(){
        isShoot = true;
    }

    public boolean isShoot() {
        return isShoot;
    }

    public void setIsShoot(boolean isShot) {
        this.isShoot = isShot;
    }

    public Shoot getShoot() {
        return new Shoot(latitude, longitude, direction, this);
    }

    /*
     * О боже! В нас попали! Мы истекаем кровью! Передайте, что я любил свою жену!
     */
    public void hit() {
        isALive = false;
    }
}
