package server.сommands;


import common.exceptions.EmptyCollectionException;
import server.utility.CollectionManager;
import server.utility.ReplyManager;

/**
 * Класс ShowCommand.
 * Команда "show" - вывод всех элементов коллекции.
 *
 * @version 1.2
 */

public class ShowCommand extends AbstractCommand
{
    /** Поле объект менеджера коллекции */
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager)
    {
        super("show", "Выводит все элементы коллекции.");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute() {
        try
        {
            if (collectionManager.getHumans().isEmpty()) throw new EmptyCollectionException();
            ReplyManager.append("Все элементы коллекции:");
            collectionManager.showCollection();
        return true;
        }
        catch (EmptyCollectionException e)
        {
            ReplyManager.appendError("Коллекция пустая.");
        }
        return false;
    }
}
