package managers;

import exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс Console, отвечающий за работу с введенными командами.
 *
 * @version 1.1
 */

public class Console
{
    /** Поле объект менеджера комманд*/
    private CommandManager commandManager;
    /** Поле объект строителя человека*/
    private HumanBeingBuilder builder;
    /** Поле объект сканнера*/
    private Scanner scanner;
    /** Поле коллекция имен исполяемых скриптов*/
    private List<String> scriptFileNames = new ArrayList<>();

    public Console(CommandManager commandManager, Scanner scanner, HumanBeingBuilder builder)
    {
        this.commandManager = commandManager;
        this.scanner = scanner;
        this.builder = builder;
    }

    /**
     * Интерактивный режим работы приложения.
     */

    public void interactiveMode()
    {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                System.out.print("~ ");
                userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandStatus = executeCommand(userCommand);
            } while (commandStatus != 2);
        } catch (Exception e) {}
    }

    /**
     * Режим работы приложения - скрипт.
     * @param fileName
     * @return Статуса выполения.
     */

    public int scriptMode(String fileName)
    {
        String[] userCommand = {"", ""};
        scriptFileNames.add(fileName);
        try (Scanner scriptScanner = new Scanner(new File(fileName))){
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner oldScanner = builder.getScanner();
            builder.setScanner(scriptScanner);
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                System.out.println("Текущая команда: " + userCommand[0]);
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptFileNames) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                executeCommand(userCommand);
            } while (scriptScanner.hasNextLine());
            builder.setScanner(oldScanner);
        } catch (FileNotFoundException e) {
            System.out.println("Файл со скриптом не найден!");
        } catch (NoSuchElementException e) {
            System.out.println("Файл со скриптом пуст!");
        } catch (ScriptRecursionException e) {
            System.out.println("Скрипты не могут вызываться рекурсивно");
        }
        return 0;
    }

    /**
     * Анализ введенной команды.
     * @param userCommand
     * @return Статус выполнения команды.
     */

    private int executeCommand(String[] userCommand)
    {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.help()) return 1;
                break;
            case "info":
                if (!commandManager.info()) return 1;
                break;
            case "show":
                if (!commandManager.show()) return 1;
                break;
            case "add":
                if (!commandManager.add()) return 1;
                break;
            case "update_by_id":
                if (!commandManager.updateById(userCommand[1])) return 1;
                break;
            case "count_less_than_mood":
                if (!commandManager.countLessMood(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clear()) return 1;
                break;
            case "save":
                if (!commandManager.save()) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "add_if_max":
                if (!commandManager.addIfMax(userCommand[1])) return 1;
                break;
            case "filter_starts_with_soundtrack_name":
                if (!commandManager.filterSound(userCommand[1])) return 1;
                break;
            case "print_field_descending_impact_speed":
                if (!commandManager.printDescendingImpact()) return 1;
                break;
            case "remove_first":
                if (!commandManager.removeFirst()) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(userCommand[1])) return 1;
                break;
            case "sort":
                if (!commandManager.sort()) return 1;
                break;
            case "exit":
                if (!commandManager.exit()) return 1;
                else return 2;
            default:
                System.out.println("Команда не найдена. Наберите 'help' для справки.");
                return 1;
        }
        return 0;
    }

}
