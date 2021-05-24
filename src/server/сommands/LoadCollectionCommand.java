package server.сommands;

import common.exceptions.WrongAmountOfParametersException;
import server.utility.CollectionManager;
import server.utility.ReplyManager;

public class LoadCollectionCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public LoadCollectionCommand(CollectionManager collectionManager) {
        super("loadCollection", "загружает коллекцию (эта команда недоступна пользователю)");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument, Object objectArgument) {
        try {
            if (argument.isEmpty() || objectArgument != null) throw new WrongAmountOfParametersException();
            collectionManager.loadCollection();
            return true;
        } catch (WrongAmountOfParametersException exception) {
            ReplyManager.append("Должно быть передано название файла!\n");
        }
        return false;
    }
}
