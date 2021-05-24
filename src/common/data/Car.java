package common.data;

import java.io.Serializable;

/**
 * Класс Car, который говорит о машине человека.
 *
 * @version 1.0
 */

public class Car implements Serializable
{
    /** Поле имя */
    private String name; //Поле не может быть null
    /** Поле крутость */
    private Boolean cool; //Поле не может быть null

    public Car(String name, Boolean cool)
    {
        setName(name);
        setCool(cool);
    }

    /**
     * Установка имени машины.
     * @param name
     */

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Установка крутости машины.
     * @param cool
     */

    public void setCool(Boolean cool)
    {
        this.cool = cool;
    }

    @Override
    public String toString()
    {
        return "Car {" +
                "name='" + name + '\'' +
                ", cool=" + cool +
                '}';
    }
}