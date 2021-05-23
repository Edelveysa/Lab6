package сommands;

import managers.CollectionManager;

/**
 * Класс HelpCommand.
 * Команда "help" - показ списка доступных команд.
 *
 * @version 1.2
 */

public class HelpCommand extends AbstractCommand
{
    private CollectionManager collectionManager;
    public HelpCommand(CollectionManager collectionManager)
    {
        super("help", "Отображает список всех доступных команд.");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute()
    {
        return true;
    }
}
