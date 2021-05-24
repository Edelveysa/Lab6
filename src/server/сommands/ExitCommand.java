package server.сommands;

import server.utility.ReplyManager;

/**
 * Класс ExitCommand.
 * Команда "exit" - завершение программы без сохранения в файл
 *
 * @version 1.2
 */

public class ExitCommand extends AbstractCommand
{
    public ExitCommand()
    {
        super("exit", "Завершение программы без сохранения в файл.");
    }

    @Override
    public boolean execute()
    {
        ReplyManager.append("Мы были рады работать с вами :)");
        System.exit(0);
        return true;
    }
}
