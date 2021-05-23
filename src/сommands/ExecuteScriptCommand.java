package сommands;

import exceptions.EmptyExecuteArgumentException;

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
            System.out.println("Скрипт выполняется! Возможны технические сообщения.");
            return true;
        } catch (EmptyExecuteArgumentException exception) {
            System.out.println("У этой команды нет параметров!");
        }
        return false;
    }

}
