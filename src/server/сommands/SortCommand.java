package server.сommands;

import server.utility.CollectionManager;
import server.utility.ReplyManager;

/**
 * Класс SortCommand.
 * Команда "sort" - сортировка коллекции.
 *
 * @version 1.2
 */

public class SortCommand extends AbstractCommand
{
    private CollectionManager collectionManager;
    public SortCommand(CollectionManager collectionManager)
    {
        super("sort", "Сортировка коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute()
    {
        try {
            collectionManager.sortCollection();
            ReplyManager.append("Коллекция отсортирована.");
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
