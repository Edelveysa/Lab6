package server.сommands;

import common.HumanBeingLite;
import common.Reply;
import common.data.*;
import common.exceptions.*;
import server.utility.CollectionManager;
import server.utility.ReplyManager;

/**
 * Класс UpdateIdCommand.
 * Команда "update_by_id" - обновления значения элемента по заданному id..
 *
 * @version 1.2
 */

public class UpdateIdCommand extends AbstractCommand
{
    /** Поле объект менеджера коллекции */
    private CollectionManager collectionManager;

    public UpdateIdCommand(CollectionManager collectionManager)
    {
        super("update_by_id", "Обновляет значение элемента по заданному id.");
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
            if(arg.isEmpty()) throw new EmptyExecuteArgumentException();
            if (collectionManager.getHumans().size() == 0) throw new EmptyCollectionException();
            HumanBeingLite newHuman = (HumanBeingLite) obj;
            HumanBeing oldHuman = collectionManager.getHumans().get(Integer.parseInt(arg));
            String name = newHuman.getName() == null ? oldHuman.getName() : newHuman.getName();
            Coordinates coordinates = newHuman.getCoordinates() == null ? oldHuman.getCoordinates() : newHuman.getCoordinates();
            Boolean realHero = newHuman.getRealHero() == null ? oldHuman.getRealHero() : newHuman.getRealHero();
            Boolean hasToothpick = newHuman.getHasToothpick() == null ? oldHuman.getHasToothpick() : newHuman.getHasToothpick();
            long impactSpeed = newHuman.getImpactSpeed() == -1 ? oldHuman.getImpactSpeed() : newHuman.getImpactSpeed();
            String soundtrackName = newHuman.getSoundtrackName() == null ? oldHuman.getSoundtrackName() : newHuman.getSoundtrackName();
            Integer minutesOfWaiting = newHuman.getMinutesOfWaiting() == -1 ? oldHuman.getMinutesOfWaiting() : newHuman.getMinutesOfWaiting();
            Mood mood = newHuman.getMood() == null ? oldHuman.getMood() : newHuman.getMood();
            Car car = newHuman.getCar() == null ? oldHuman.getCar() : newHuman.getCar();

            collectionManager.updateIdInCollection(Integer.valueOf(arg), new HumanBeing(
                    Long.valueOf(arg),
                    name,
                    coordinates,
                    realHero,
                    hasToothpick,
                    impactSpeed,
                    soundtrackName,
                    minutesOfWaiting,
                    mood,
                    car
                    )
            );
            ReplyManager.append("Элемент обновлен.\n");
            return true;
        }
        catch (EmptyExecuteArgumentException e)
        {
            ReplyManager.appendError("Введите id!");
        }
        catch (NumberFormatException e)
        {
            ReplyManager.appendError("Введите числовой id!");
        }
        return false;
    }
}
