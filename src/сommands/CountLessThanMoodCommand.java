package сommands;

import exceptions.EmptyExecuteArgumentException;
import managers.CollectionManager;
import data.Mood;

/**
 * Класс CountLessThanMoodCommand.
 * Команда "count_less_than_mood" - вывод количества элементов, значение поля mood которых меньше заданного.
 *
 * @version 1.2
 */

public class CountLessThanMoodCommand extends AbstractCommand
{
    /** Поле объект менеджера коллекции */
    private CollectionManager collectionManager;

    public CountLessThanMoodCommand(CollectionManager collectionManager)
    {
        super("count_less_than_mood mood", "Вывод количества элементов, значение поля mood которых меньше заданного.");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute(String arg)
    {
        Mood mood;
        try {
            switch (arg) {
                case "APATHY":
                case "Апатия":
                case "апатия":
                    mood = Mood.APATHY;
                    break;
                case "SORROW":
                case "Горе":
                case "горе":
                    mood = Mood.SORROW;
                    break;
                case "GLOOM":
                case "мрак":
                case "Мрак":
                    mood = Mood.GLOOM;
                    break;
                case "RAGE":
                case "гнев":
                case "Гнев":
                    mood = Mood.RAGE;
                    break;
                default:
                    throw new EmptyExecuteArgumentException();
            }
            collectionManager.countLessMoodInCollection(mood);
            return true;
        } catch (EmptyExecuteArgumentException e){
            System.out.println("Вы ввели \"пустое\" настроение. \nНапомню возможные настроения: горе, мрак, апатия и гнев." );
        }
        return false;
    }
}
