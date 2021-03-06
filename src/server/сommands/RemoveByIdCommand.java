package server.сommands;

import common.exceptions.EmptyExecuteArgumentException;
import server.utility.CollectionManager;
import server.utility.ReplyManager;

/**
 * Класс RemoveByIdCommand.
 * Команда "remove_by_id" - удаляет объект из коллекции по заданному id.
 *
 * @version 1.2
 */

public class RemoveByIdCommand extends AbstractCommand
{
    /** Поле объект менеджера коллекции */
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager)
    {
        super("remove_by_id id", "Удаляет объект из коллекции по заданному id.");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute(String arg)
    {
        try {
            if(arg.isEmpty()) throw new EmptyExecuteArgumentException();
            if(arg.equals(" ")) throw new EmptyExecuteArgumentException();
            collectionManager.removeByIdInCollection(Integer.valueOf(arg));
            ReplyManager.append("Удаление прошло успешно.");
            return true;
        }  catch (EmptyExecuteArgumentException e){
            ReplyManager.appendError("Введите id!");
        } catch (NumberFormatException e){
            ReplyManager.appendError("...это точно было число?");
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
