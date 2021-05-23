package сommands;

import exceptions.EmptyExecuteArgumentException;
import managers.CollectionManager;
import data.HumanBeing;
import managers.HumanBeingBuilder;

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
    /** Поле объект-строитель человека */
    private HumanBeingBuilder builder;

    public UpdateIdCommand(CollectionManager collectionManager, HumanBeingBuilder builder)
    {
        super("update_by_id", "Обновляет значение элемента по заданному id.");
        this.collectionManager = collectionManager;
        this.builder = builder;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute(String arg)
    {
        try{
            if(arg.isEmpty()) throw new EmptyExecuteArgumentException();
            HumanBeing humanBeing = new HumanBeing(
                    Long.valueOf(arg),
                    builder.scanName(),
                    builder.scanCoordinates(),
                    builder.scanRealHero(),
                    builder.scanHasToothPick(),
                    builder.scanImpactSpeed(),
                    builder.scanSoundtrackName(),
                    builder.scanMinutesOfWaiting(),
                    builder.scanMood(),
                    builder.scanCar()
            );
            collectionManager.updateIdInCollection(Integer.valueOf(arg), humanBeing);
            return true;
        }catch (EmptyExecuteArgumentException e){
            System.out.println("Введите id!");
        }catch (NumberFormatException e){
            System.out.println("Введите числовой id!");
        }
        return false;
    }
}
