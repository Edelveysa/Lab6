package server.сommands;

import common.exceptions.EmptyExecuteArgumentException;
import server.utility.ReplyManager;

/**
 * Класс ExecuteScriptCommand.
 * Команда "execute_script" - исполнение скрипт из указанного файла.
 *
 * @version 1.2
 */

public class ExecuteScriptCommand extends AbstractCommand
{
    public ExecuteScriptCommand() {
        super("execute_script filename", "Исполнение скрипта из указанного файла");
    }

    /**
     * Выполняет скрипт.
     * @return Статус выполнения команды.
     */

    @Override
    public boolean execute(String arg) {
        try {
            if (arg.isEmpty()) throw new EmptyExecuteArgumentException();
            ReplyManager.append("Скрипт выполняется! Возможны технические сообщения.\n");
            return true;
        } catch (EmptyExecuteArgumentException exception) {
            exception.printStackTrace();
            ReplyManager.appendError("У этой команды нет параметров!\n");
        }
        return false;
    }

}
