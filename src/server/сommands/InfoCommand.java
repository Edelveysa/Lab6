package server.сommands;

import server.utility.CollectionManager;
import server.utility.ReplyManager;

/**
 * Класс InfoCommand.
 * Команда "info" - вывод информации о коллекции.
 *
 * @version 1.2
 */

public class InfoCommand extends AbstractCommand
{
    /** Поле объект менеджера коллекции */
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager)
    {
        super("info", "Вывод информации о коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute()
    {
        ReplyManager.append("Тип коллекции:" + collectionManager.typeOfCollection() +"\n Дата инициализации: "
                + collectionManager.getTime() + "\nКоличество элементов: " + collectionManager.getHumans().stream().count());
        return true;
    }
}
