package server.сommands;

import server.utility.CollectionManager;
import server.utility.ReplyManager;

/**
 * Класс RemoveFirstCommand.
 * Команда "remove_first" - удаление первого элемента в коллекции.
 *
 * @version 1.2
 */

public class RemoveFirstCommand extends AbstractCommand
{
    /** Поле объект менеджера коллекции */
    private CollectionManager collectionManager;

    public RemoveFirstCommand(CollectionManager collectionManager)
    {
        super("remove_first", "Удалить первый элемент в коллекции.");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute() {
        try{
            collectionManager.removeFirstInCollection();
            ReplyManager.append("Удаление прошло успешно.");
            return true;
        }catch (Exception e)
        {
           e.printStackTrace();
        }
        return false;
    }
}
