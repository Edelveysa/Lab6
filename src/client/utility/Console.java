package client.utility;

import common.*;
import common.data.*;
import common.exceptions.*;
import server.utility.CommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
    private Stack<File> scriptFileNames = new Stack<>();
    private Stack<Scanner> scannerStack = new Stack<>();

    public Console(CommandManager commandManager, Scanner scanner, HumanBeingBuilder builder)
    {
        this.commandManager = commandManager;
        this.scanner = scanner;
        this.builder = builder;
    }

    public Console(Scanner scanner)
    {
        this.scanner = scanner;
    }

    /**
     * Интерактивный режим работы приложения.
     */

    public Request interactiveMode(CheckCode serverCode)
    {
        String userInput;
        String[] userCommand = {"", ""};
        ProcessCode processCode = null;
        try {
            do {
                try {
                    if (fileMode() && (serverCode == CheckCode.CLOSE_SERVER || serverCode == CheckCode.ERROR)) {
                        throw new IncorrectInputInScriptException();
                    }
                    while (fileMode() && !scanner.hasNextLine()) {
                        scanner.close();
                        scanner = scannerStack.pop();
                        System.out.println("Скрипт '" + scriptFileNames.pop().getName() + "' завершен.");
                    }
                    if (fileMode()) {
                        userInput = scanner.nextLine();
                        if (!userInput.isEmpty()) {
                            System.out.print("~ ");
                            System.out.println(userInput);
                        }
                    } else {
                        System.out.print("$ ");
                        userInput = scanner.nextLine();
                    }
                    userCommand = (userInput.trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                } catch (NoSuchElementException | IllegalStateException exception) {
                    System.out.println("Произошла ошибка при вводе команды!");
                    userCommand = new String[]{"", ""};
                }
                processCode = checkCommand(userCommand[0], userCommand[1]);
            } while (processCode == ProcessCode.ERROR && !fileMode() || userCommand[0].isEmpty());
            try {
                switch (processCode) {
                    case OBJECT:
                        HumanBeingLite insertHuman = generateAddHuman();
                        return new Request(userCommand[0], userCommand[1], insertHuman);
                    case UPDATE:
                        HumanBeingLite updateHuman = generateUpdateHuman();
                        return new Request(userCommand[0], userCommand[1], updateHuman);
                    case SCRIPT_MODE:
                        File scriptFile = new File(userCommand[1]);
                        if (!scriptFile.exists()) throw new FileNotFoundException();
                        if (!scriptFileNames.isEmpty() && scriptFileNames.search(scriptFile) != -1) {
                            throw new ScriptRecursionException();
                        }
                        scannerStack.push(scanner);
                        scriptFileNames.push(scriptFile);
                        scanner = new Scanner(scriptFile);
                        System.out.println("Выполняю скрипт '" + scriptFile.getName() + "'!");
                        break;
                }
            } catch (FileNotFoundException exception) {
                System.out.println("Файл со скриптом не найден!");
            } catch (ScriptRecursionException exception) {
                System.out.println("Скрипты не могут вызываться рекурсивно!");
                throw new IncorrectInputInScriptException();
            }
        } catch (IncorrectInputInScriptException exception) {
            System.out.println("Выполнение скрипта прервано!");
            while (!scannerStack.isEmpty()) {
                scanner.close();
                scanner = scannerStack.pop();
            }
        }
        return new Request(userCommand[0], userCommand[1]);
    }

    private boolean fileMode()
    {
        return !scannerStack.isEmpty();
    }


    /**
     * Анализ введенной команды.
     * @params command,argument
     * @return Статус выполнения команды.
     */

    private ProcessCode checkCommand(String command, String argument)
    {
        try
        {
            switch (command)
            {
                case "":
                    return ProcessCode.ERROR;
                case "help":
                case "show":
                case "info":
                case "clear":
                case "save":
                case "sort":
                case "print_field_descending_impact_speed":
                case "exit":
                    if (!argument.isEmpty()) throw new EmptyExecuteArgumentException();
                    return ProcessCode.OK;
                case "add":
                case "add_if_max":
                case "filter_starts_with_soundtrack_name":
                case "remove_by_id":
                case "count_less_than_mood":
                    if (argument.isEmpty()) throw new EmptyExecuteArgumentException();
                    return ProcessCode.OBJECT;
                case "remove_first":
                    if (!argument.isEmpty()) throw new EmptyExecuteArgumentException();
                    return ProcessCode.OBJECT;
                case "update_by_id":
                    if (argument.isEmpty()) throw new EmptyExecuteArgumentException();
                    return ProcessCode.UPDATE;
                case "execute_script":
                    if (argument.isEmpty()) throw new EmptyExecuteArgumentException();
                    return ProcessCode.SCRIPT_MODE;
                default:
                    System.out.println("Такой команды нет. Попробуйте 'help'.");
                    return ProcessCode.ERROR;
            }
        } catch (EmptyExecuteArgumentException e) {
            System.out.println("Проверьте правильность ввода аргументов!");
        }
        return ProcessCode.OK;
    }

    private HumanBeingLite generateAddHuman()
    {
        HumanBeingBuilder builder = new HumanBeingBuilder(scanner);
        if (fileMode()) {
            builder.setFileMode();
        } else {
            builder.setUserMode();
        }
        return new HumanBeingLite(
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
    }

    private HumanBeingLite generateUpdateHuman()
    {
        HumanBeingBuilder builder = new HumanBeingBuilder(scanner);
        if (fileMode()) {
            builder.setFileMode();
        } else {
            builder.setUserMode();
        }
        String name = builder.askAboutChangingField("Изменить имя человека?") ?
                builder.scanName() : null;
        Coordinates coordinates = builder.askAboutChangingField("Изменить координаты человека?") ?
                builder.scanCoordinates() : null;
        Boolean realHero = builder.askAboutChangingField("Человек - герой?") ?
                builder.scanRealHero() : null;
        Boolean hasToothpick = builder.askAboutChangingField("Имеется зубочистка?") ?
                builder.scanHasToothPick() : null;
        long impactSpeed = builder.askAboutChangingField("Какая скорость удара?") ?
                builder.scanImpactSpeed() : null;
        String soundtrackName = builder.askAboutChangingField("Какой саундтрек у человека?") ?
                builder.scanSoundtrackName() : null;
        Integer minutesOfWaiting = builder.askAboutChangingField("Сколько времени ожидания?") ?
                builder.scanMinutesOfWaiting() : null;
        Mood mood = builder.askAboutChangingField("Какое настроение? Варианты: апатия, горе, мрак, гнев.") ?
                builder.scanMood() : null;
        Car car = builder.askAboutChangingField("Как дела с машиной?") ?
                builder.scanCar() : null;

        return new HumanBeingLite(
                name,
                coordinates,
                realHero,
                hasToothpick,
                impactSpeed,
                soundtrackName,
                minutesOfWaiting,
                mood,
                car
        );
    }


}
