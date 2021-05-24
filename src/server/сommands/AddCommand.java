package server.сommands;

import common.HumanBeingLite;
import common.data.HumanBeing;
import common.exceptions.EmptyExecuteArgumentException;
import server.utility.CollectionManager;
import server.utility.ReplyManager;

/**
 * Класс AddCommand.
 * Команда "add" - добавление нового элемента в коллекцию.
 *
 * @version 1.2
 */

public class AddCommand extends AbstractCommand
{
    /** Поле объект менеджера коллекции */
    private CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager)
    {
        super("add ", "Добавление нового элемента в коллекцию.");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute(String arg, Object obj)
    {
        try{
            if (arg.isEmpty() || obj == null) throw new EmptyExecuteArgumentException();
            HumanBeingLite humanLite = (HumanBeingLite) obj;
            collectionManager.addToCollection(
                    new HumanBeing(
                            collectionManager.generateID(),
                            humanLite.getName(),
                            humanLite.getCoordinates(),
                            humanLite.getRealHero(),
                            humanLite.getHasToothpick(),
                            humanLite.getImpactSpeed(),
                            humanLite.getSoundtrackName(),
                            humanLite.getMinutesOfWaiting(),
                            humanLite.getMood(),
                            humanLite.getCar()
                    )
            );
            ReplyManager.append("Элемент добавлен в коллекцию.\n");
            return true;
        }catch (Exception e)
        {
            ReplyManager.append("У команды нет аргументов.");
        }
        return false;
    }
}
