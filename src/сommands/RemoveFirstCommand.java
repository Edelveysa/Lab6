package сommands;

import managers.CollectionManager;

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
            return true;
        }catch (Exception e){
            System.out.println();
        }
        return false;
    }
}
