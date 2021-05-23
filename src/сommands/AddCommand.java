package сommands;

import data.HumanBeing;
import managers.CollectionManager;
import managers.HumanBeingBuilder;

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
    /** Поле объект-строитель человека */
    private HumanBeingBuilder builder;
    
    public AddCommand(CollectionManager collectionManager, HumanBeingBuilder builder)
    {
        super("add ", "Добавление нового элемента в коллекцию.");
        this.builder = builder;
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute()
    {
        try{
            HumanBeing humanBeing = new HumanBeing(
                    collectionManager.generateID(),
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
            collectionManager.addToCollection(humanBeing);
            return true;
        }catch (Exception e)
        {
            System.out.println("У команды нет аргументов.");
        }
        return false;
    }
}
