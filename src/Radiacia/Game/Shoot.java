package Radiacia.Game;

/**
 * Created by Cntgfy on 02.07.2016.
 *
 * Создается, когда игрок делает выстрел.
 */
public class Shoot extends GameObject {
    //Радиус размера тела
    private final double DELTA_LOCATION;

    //Хозяин выстрела
    private final GameObject owner;

    /*
    * direction в градусах [-180; 180]
    * */
    public Shoot(double latitude, double longitude, float direction, GameObject owner) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
        this.DELTA_LOCATION = 8d;
        this.owner = owner;
    }

    /*
    * direction в градусах [-180; 180]
    * */
    public Shoot(double latitude, double longitude, float direction, GameObject owner, double DELTA_LOCATION) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.direction = direction;
        this.DELTA_LOCATION = DELTA_LOCATION;
        this.owner = owner;
    }

    /*
    * Проверяем, попал ли выстрел в игрока gamer
    * */
    public boolean isHit(GameObject gameObject) {
        //Если gameObject стреляет сам в себя
        if (gameObject == owner) return false;

        double direction = Math.toRadians(this.direction);

        //Единичный вектор направления
        double onceX = Math.cos(direction);
        double onceY = Math.sin(direction);

        //Вектор от источника выстрела к проверяемому игроку
        double lat = gameObject.getLatitude() - latitude;
        double lon = gameObject.getLongitude() - longitude;

        double angle = Math.asin(sin(onceX, onceY, lat, lon));
        double deltaAngle = deltaAngle(lat, lon);

        return Math.abs(angle) < deltaAngle;
    }

    /*
    * Вычисление sin угла между двухмерными векторами а и b
    * */
    private double sin(double aX, double aY, double bX, double bY) {
        return vectorProduct(aX, aY, bX, bY)/( abs(bX, bY)*abs(aX, aY) );
    }

    /*
    * вычисление модуля векторного произведения двухмерных векторов а и b
    * */
    private double vectorProduct(double aX, double aY, double bX, double bY) {
        return aX*bY - bX*aY;
    }

    /*
    * Вычисление длины двухмерного вектора
    * */
    private double abs(double x, double y) {
        return Math.sqrt(x*x + y*y);
    }

    /*
    * Находит угол поворота одной точки относительно другой
    * */
    private float angle(double lat1, double lon1, double lat2, double lon2) {
        double lat = lat2 - lat1;
        double lon = lon2 - lon1;

        return (float) Math.toDegrees(Math.atan(lon / lat));
    }

    /*
    * Находит отклонение угла с учетом DELTA_LOCATION размера тела
    * возвращает значение в радианах
    * */
    private double deltaAngle(double x, double y) {
        double length = Math.sqrt(x*x + y*y);

        return Math.abs(Math.asin(DELTA_LOCATION / length));
    }
}
