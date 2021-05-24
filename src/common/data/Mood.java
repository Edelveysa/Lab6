package common.data;

/**
 * Enum Mood, отвечающий за настроение человека.
 *
 * @version 1.0
 */

public enum Mood
{
    SORROW("sorrow", 1),
    GLOOM("gloom", 2),
    APATHY("apathy", 3),
    RAGE("rage", 0);

    /** Поле настроение */
    private String mood;
    /** Поле число */
    private int number;

    Mood(String mood, int number)
    {
        this.number = number;
        this.mood = mood;
    }

    /**
     * @return Настроения человека.
     */
    public String getMood()
    {
        return mood;
    }

    /**
     * @return Число настроения.
     */

    public int getNumber()
    {
        return number;
    }

    /**
     * Поиск числа, согласно данному настроению.
     * @param mood
     * @return Число настроения.
     */

    public static int findNumber(String mood)
    {
        int num = -1;
        if((mood == "sorrow")||(mood == "SORROW")){
            num = 1;
        }
        if((mood == "gloom")||(mood == "GLOOM")){
            num = 2;
        }
        if((mood == "apathy")||(mood == "APATHY")){
            num = 3;
        }
        if((mood == "rage")||(mood == "RAGE")){
            num = 0;
        }
        return num;
    }


}
