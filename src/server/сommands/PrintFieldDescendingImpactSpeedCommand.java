package server.сommands;

import common.exceptions.EmptyCollectionException;
import server.utility.CollectionManager;
import server.utility.ReplyManager;

/**
 * Класс PrintFieldDescendingImpactSpeedCommand.
 * Команда "print_field_descending_impact_speed" - вывод значений всех элементов в порядке убывания скорости удара.
 *
 * @version 1.2
 */

public class PrintFieldDescendingImpactSpeedCommand extends AbstractCommand
{
    private CollectionManager collectionManager;
    public PrintFieldDescendingImpactSpeedCommand(CollectionManager collectionManager){
        super("print_field_descending_impact_speed", "Выводит значения всех элементов в порядке убывания скорости удара.");
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
            collectionManager.printLessImpactSpeedCollection();
            return true;
        }
        catch (EmptyCollectionException e)
        {
            ReplyManager.appendError("Коллекция пустая.");
        }
        return false;
    }
}
