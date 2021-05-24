package server.сommands;

import server.utility.CollectionManager;
import server.utility.ReplyManager;

/**
 * Класс SaveCommand.
 * Команда "save" - сохранение коллекции в файл.
 *
 * @version 1.2
 */

public class SaveCommand extends AbstractCommand
{
    /** Поле объект менеджера коллекции */
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager)
    {
        super("save", "Сохраняет коллекцию в файл.");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute()
    {
        try
        {
            collectionManager.saveCollection();
            return true;
        } catch (NullPointerException e)
        {
            ReplyManager.appendError("Коллекция пустая, увы.");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

}
