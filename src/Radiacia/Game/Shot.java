package Radiacia.game;

import Radiacia.math.CoordinateConversion2D;
import Radiacia.math.CoordinateConversion3D;

/**
 * Created by Cntgfy on 02.07.2016.
 *
 * Создается, когда игрок делает выстрел.
 *
 */
public class Shot extends GameObject {
    //Радиус попадания
    private float size = 9f;

    CoordinateConversion3D cc3 = new CoordinateConversion3D();
    CoordinateConversion2D cc2 = new CoordinateConversion2D();

    /**
     * Положение данного объекта в с.к., связанной с Землей. Центр с.к. в центре Земли.
     */
    private double[] position;

    public Shot(GameObject gameObject) {
        super(gameObject);
        this.position = cc3.position(latitude, longitude);
    }

    public Shot(Gamer gamer) {
        super(gamer);
        this.size = gamer.getAccuracy();
        this.position = cc3.position(latitude, longitude);
    }

    /*
     * direction в градусах [-180; 180]
     * */
    public Shot(double latitude, double longitude, float direction) {
        super(latitude, longitude, direction);
        this.position = cc3.position(latitude, longitude);
    }

    /**
     * @param direction в градусах [-180; 180]
     * @param size радиус попадания
     */
    public Shot(double latitude, double longitude, float direction, float size) {
        super(latitude, longitude, direction);
        this.size = size;
        this.position = cc3.position(latitude, longitude);
    }

    /**
     * Проверяем, попал ли выстрел в игрока gamer
     *
     * Метод еще не работает
     *
     * @param gameObject объект, в который пытаемся попасть
     * @param accuracy точность определения положения объекта
     * @return true - попал
     *         false- не попал
     */
     public boolean isHit(GameObject gameObject, float accuracy) {
        //Угол поворота выстрела
        float direction1 = direction;

        double[] gameObjectPosition = cc3.position(gameObject.getLatitude(), gameObject.getLongitude());

        //Угол поворота вектора между выстрелом и gamerObject
        float direction2 = cc3.angleOnPlane(position, gameObjectPosition);

        //Расстояние между выстрелом и gameObject
        float distance = (float) cc3.abs(cc3.vectorsDifference(gameObjectPosition, position));

        float deltaAngle = cc2.deltaAngle2(distance, size);

        return Math.abs(direction1 - direction2) < deltaAngle;
    }

    public boolean isHit(GameObject gameObject) {
        return isHit(gameObject, size);
    }

    public boolean isHit(Gamer gamer) {
        return isHit(gamer, gamer.getAccuracy());
    }

    public double[] getPosition() {
        return position;
    }
}
