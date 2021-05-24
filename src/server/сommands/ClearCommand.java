package server.сommands;


import common.Reply;
import common.exceptions.EmptyCollectionException;
import server.utility.CollectionManager;
import server.utility.ReplyManager;

/**
 * Класс ClearCommand.
 * Команда "clear" - очищает коллекцию.
 *
 * @version 1.2
 */

public class ClearCommand extends AbstractCommand
{
    /** Поле объект менеджера коллекции */
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager)
    {
        super("clear", "Очищает коллекцию.");
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
            if(collectionManager.getHumans().isEmpty()) throw new EmptyCollectionException();
            collectionManager.clearCollection();
            ReplyManager.append("Коллекция успешно очищена!\n");
            return true;
        } catch (EmptyCollectionException exception) {
            ReplyManager.appendError("Коллекция пустая.");
        }
        return false;

    }
}
