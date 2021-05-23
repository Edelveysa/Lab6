package сommands;


import exceptions.EmptyCollectionException;
import managers.CollectionManager;

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
            return true;
        } catch (EmptyCollectionException exception) {
            System.out.println("Коллекция пустая.");
        }
        return false;

    }
}
