package сommands;

import exceptions.EmptyExecuteArgumentException;
import managers.CollectionManager;

/**
 * Класс FilterStartsWithSoundNameCommand.
 * Команда "filter_starts_with_soundtrack_name" - вывод элементов, где название саундрека начинается с заданной подстроки.
 *
 * @version 1.2
 */

public class FilterStartsWithSoundNameCommand extends AbstractCommand
{
    /** Поле объект менеджера коллекции */
    private CollectionManager collectionManager;

    public FilterStartsWithSoundNameCommand(CollectionManager collectionManager)
    {
        super("filter_starts_with_soundtrack_name name", "Вывод элементов, где название саундрека начинается с заданной подстроки.");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute(String arg) {
        try {
            if(arg.isEmpty()) throw new EmptyExecuteArgumentException();
            if(arg.equals(" ")) throw new EmptyExecuteArgumentException();
            collectionManager.filterSoundCollection(arg);
            return true;
        }catch (EmptyExecuteArgumentException e){
            System.out.println("Введите подстроку!");
        }
        return false;
    }
}
