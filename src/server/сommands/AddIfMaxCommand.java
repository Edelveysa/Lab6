package server.сommands;

import common.HumanBeingLite;
import common.data.HumanBeing;
import common.exceptions.EmptyExecuteArgumentException;
import server.utility.CollectionManager;
import server.utility.ReplyManager;

/**
 * Класс AddIfMaxCommand.
 * Команда "add_if_max" - добавляет новый элемент в коллекцию, если скорость удара наибольшая.
 *
 * @version 1.2
 */

public class AddIfMaxCommand extends AbstractCommand
{
    /** Поле объект менеджера коллекции */
    private CollectionManager collectionManager;

    public AddIfMaxCommand(CollectionManager collectionManager)
    {
        super("add_if_max", "Добавляет новый элемент в коллекцию, если скорость удара наибольшая.");
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
            collectionManager.addMaxToCollection(
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
            ReplyManager.append("Элемент добавлен в коллекцию!");
            return true;
        }catch(EmptyExecuteArgumentException e)
        {
            ReplyManager.appendError("У команды нет аргументов.");
        }
        return false;
    }
}
