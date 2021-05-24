package server.utility;

import java.util.ArrayList;
import java.util.List;
import server.сommands.*;

/**
 * Класс CommandManager, отвечающий за работу с командами.
 *
 * @version 1.1
 */

public class CommandManager
{
    /** Поле список команд */
    private static List<AbstractCommand> commands = new ArrayList<>();
    /** Поле команда add*/
    private AddCommand addCommand;
    /** Поле команда add_if_max*/
    private AddIfMaxCommand addIfMaxCommand;
    /** Поле команда clear*/
    private ClearCommand clearCommand;
    /** Поле команда count_less_than_mood*/
    private CountLessThanMoodCommand countLessThanMoodCommand;
    /** Поле команда execute_script*/
    private ExecuteScriptCommand executeScriptCommand;
    /** Поле команда exit*/
    private ExitCommand exitCommand;
    /** Поле команда filter_starts_with_soundtrack_name*/
    private FilterStartsWithSoundNameCommand filterStartWithSoundNameCommand;
    /** Поле команда help*/
    private HelpCommand helpCommand;
    /** Поле команда info*/
    private InfoCommand infoCommand;
    /** Поле команда print_field_descending_impact_speed*/
    private PrintFieldDescendingImpactSpeedCommand printFieldDescendingImpactSpeedCommand;
    /** Поле команда remove_by_id*/
    private RemoveByIdCommand removeByIdCommand;
    /** Поле команда remove_first*/
    private RemoveFirstCommand removeFirstCommand;
    /** Поле команда save*/
    private SaveCommand saveCommand;
    /** Поле команда show*/
    private ShowCommand showCommand;
    /** Поле команда sort*/
    private SortCommand sortCommand;
    /** Поле команда update_by_id*/
    private UpdateIdCommand updateIdCommand;

    public CommandManager( AddCommand addCommand, AddIfMaxCommand addIfMaxCommand, ClearCommand clearCommand, CountLessThanMoodCommand countLessThanMoodCommand,
                                ExecuteScriptCommand executeScriptCommand, ExitCommand exitCommand, FilterStartsWithSoundNameCommand filterStartWithSoundNameCommand,
                                HelpCommand helpCommand, InfoCommand infoCommand, PrintFieldDescendingImpactSpeedCommand printFieldDescendingImpactSpeedCommand,
                                RemoveByIdCommand removeByIdCommand, RemoveFirstCommand removeFirstCommand, SaveCommand saveCommand, ShowCommand showCommand,
                                SortCommand sortCommand, UpdateIdCommand updateIdCommand)
    {
        this.addCommand = addCommand;
        this.addIfMaxCommand = addIfMaxCommand;
        this.clearCommand = clearCommand;
        this.countLessThanMoodCommand = countLessThanMoodCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.filterStartWithSoundNameCommand = filterStartWithSoundNameCommand;
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.printFieldDescendingImpactSpeedCommand = printFieldDescendingImpactSpeedCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.removeFirstCommand = removeFirstCommand;
        this.saveCommand = saveCommand;
        this.showCommand = showCommand;
        this.sortCommand = sortCommand;
        this.updateIdCommand = updateIdCommand;

        commands.add(addCommand);
        commands.add(addIfMaxCommand);
        commands.add(clearCommand);
        commands.add(countLessThanMoodCommand);
        commands.add(executeScriptCommand);
        commands.add(exitCommand);
        commands.add(filterStartWithSoundNameCommand);
        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(printFieldDescendingImpactSpeedCommand);
        commands.add(removeByIdCommand);
        commands.add(removeFirstCommand);
        commands.add(saveCommand);
        commands.add(showCommand);
        commands.add(sortCommand);
        commands.add(updateIdCommand);

    }

    /**
     * Выполняет команду help.
     * @return Статус выполнения команды.
     */
    public boolean help()
    {
        if (helpCommand.execute()) {
            for (AbstractCommand command : commands) {
                ReplyManager.appendTable(command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Исполнение команды info.
     * @return Статус выполнения команды.
     */
    public boolean info()
    {
        return infoCommand.execute();
    }

    /**
     * Исполнение команды show.
     * @return Статус выполнения команды.
     */
    public boolean show()
    {
        return showCommand.execute();
    }

    /**
     * Исполнение команды save.
     * @return Статус выполнения команды.
     */
    public boolean save()
    {
        return saveCommand.execute();
    }

    /**
     * Исполнение команды clear.
     * @return Статус выполнения команды.
     */
    public boolean clear()
    {
        return clearCommand.execute();
    }

    /**
     * Исполнение команды add.
     * @return Статус выполнения команды.
     */
    public boolean add()
    {
        return addCommand.execute();
    }

    /**
     * Исполнение команды update_by_id.
     * @return Статус выполнения команды.
     */
    public boolean updateById(String arg)
    {
        return updateIdCommand.execute(arg);
    }

    /**
     * Исполнение команды count_less_than_mood.
     * @return Статус выполнения команды.
     */
    public boolean countLessMood(String arg)
    {
        return countLessThanMoodCommand.execute(arg);
    }

    /**
     * Исполнение команды exit.
     * @return Статус выполнения команды.
     */
    public boolean exit()
    {
        return exitCommand.execute();
    }

    /**
     * Исполнение команды add_if_max.
     * @return Статус выполнения команды.
     */
    public boolean addIfMax(String arg)
    {
        return addIfMaxCommand.execute(arg);
    }

    /**
     * Исполнение команды filter_starts_with_soundtrack_name.
     * @return Статус выполнения команды.
     */
    public boolean filterSound(String arg)
    {
        return filterStartWithSoundNameCommand.execute(arg);
    }

    /**
     * Исполнение команды print_field_descending_impact_speed.
     * @return Статус выполнения команды.
     */
    public boolean printDescendingImpact()
    {
        return printFieldDescendingImpactSpeedCommand.execute();
    }

    /**
     * Исполнение команды remove_first.
     * @return Статус выполнения команды.
     */
    public boolean removeFirst()
    {
        return removeFirstCommand.execute();
    }

    /**
     * Исполнение команды remove_by_id.
     * @return Статус выполнения команды.
     */
    public boolean removeById(String arg)
    {
        return removeByIdCommand.execute(arg);
    }

    /**
     * Исполнение команды sort.
     * @return Статус выполнения команды.
     */
    public boolean sort()
    {
        return sortCommand.execute();
    }

    /**
     * Исполнение команды execute_script.
     * @return Статус выполнения команды.
     */
    public boolean executeScript(String arg)
    {
        return executeScriptCommand.execute(arg);
    }
}
