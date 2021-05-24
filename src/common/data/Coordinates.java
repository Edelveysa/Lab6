package common.data;

/**
 * Класс Coordinates, класс, который дает координаты (X; Y) человеку.
 *
 * @version 1.0
 */

public class Coordinates
{
    /**Поле координата Х*/
    private Integer x; //Максимальное значение поля: 841, Поле не может быть null
    /**Поле координата Y*/
    private Double y; //Поле не может быть null

    public Coordinates(Integer x, Double y)
    {
        setX(x);
        setY(y);
    }

    /**
     * Установка координаты Х.
     * @param x
     */

    public void setX(Integer x)
    {
        this.x = x;
    }

    /**
     * Установка координаты Y.
     * @param y
     */

    public void setY(Double y)
    {
        this.y = y;
    }

    /**
     * @return Координата Х.
     */

    public Integer getX()
    {
        return x;
    }

    /**
     * @return Координата Y.
     */

    public Double getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return "Coordinates {" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


}
